package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.algorithm.GeneticAlgorithm;
import com.courseschedule.common.exception.CourseArrangeException;
import com.courseschedule.common.lang.ConstantInfo;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.entity.*;
import com.courseschedule.mapper.*;
import com.courseschedule.service.TaskService;
import com.courseschedule.utils.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * [input]教学任务表(包括教学大纲，教师选课) 服务实现类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@Slf4j
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Autowired
    private SemesterMapper semesterMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private TimetableMapper timetableMapper;
    @Autowired
    private GeneticAlgorithm geneticAlgorithm;
    @Autowired
    private ExclusionRuleMapper exclusionRuleMapper;

    /**
     * <h1>排课算法<h1/>
     * @param id 学期id
     */
    @Transactional(rollbackFor = Exception.class)//该方法中抛出Exception及其子类时，当前事务回滚
    @Override
    public Result courseScheduling(Long id,int ifScheduledInNight,int ifExpInNight) {
        try {
            Semester semester = semesterMapper.selectById(id);
            log.info("学期【" + semester.getSemesterName()
                    + "】，共" + semester.getSemesterWeeksSum() + "周");
            long start = System.currentTimeMillis();
            log.info("开始自动排课,时间:" + start);
            // 1. 获得排课任务表
            List<Task> taskList = taskMapper.selectList(new LambdaQueryWrapper<Task>()
                    .eq(Task::isIfScheduled, true));
            if (null == taskList || taskList.isEmpty()) {
                return Result.error("自动排课失败,查询不到排课任务!请导入排课任务再进行排课");
            }
            // 校验学时是否超过课表的容纳值
            checkWeeksNumber(taskList);

            // 2.3. 将开课任务的各项信息进行编码成染色体 - 同时随机分配时间
            List<String> resultGeneList = coding(taskList,ifScheduledInNight,ifExpInNight);

            // 4. 将分配好时间的基因编码以班级分类成为以班级的个体，得到班级的不冲突时间初始编码
            Map<String, List<String>> individualMap = geneticAlgorithm.transformIndividual(resultGeneList);

            // 5.将分配好时间的基因编码以教师分类成为以教师的个体，得到各教师各个时间段的上课节数
            Map<String,List<Integer>> teacherTimeslotNum = geneticAlgorithm.transformIndividualByTeacherNo(resultGeneList);

            // 6. 遗传进化(这里面已经将上课时间编入编码中)
            individualMap = geneticAlgorithm.geneticEvolution(individualMap,teacherTimeslotNum,ifScheduledInNight,ifExpInNight);

            // 7. 分配教室并做教室冲突检测
            List<String> resultList = assignRoom(individualMap);
            // 8.添加连排课程进去
            List<String> afterAddresultGeneList = addDurationCourse(resultList);
            // 9. 解码
            List<Timetable> timetableList = decoding(afterAddresultGeneList);
            // 10. 课程表写入数据库
            timetableMapper.deleteAll(semester.getId());
            for (Timetable timetable : timetableList) {
                timetable.setSemesterId(semester.getId());
                timetableMapper.insert(timetable);
            }
            long timeConsume = System.currentTimeMillis() - start;
            log.info("完成自动排课,耗时:" + timeConsume);
            semester.setSemesterStatus(1);
            semesterMapper.updateById(semester);
            return Result.success(String.format("自动排课成功,耗时:%sms", timeConsume));
        } catch (Exception e) {
            return Result.error("自动排课失败,出现异常!");
        }
    }

    @Override
    public Result getList() {
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<Task>()
                .eq(Task::getIsDeleted, BaseVo.NOT_DELETED);
        List<Task> list = super.list(wrapper);
        return Result.success("成功返回排课任务表", list);
    }

    @Override
    public void ifScheduled(Task task) {
        taskMapper.ifScheduled(task);
    }

    @Override
    public void ifFixRoom(Task task) {
        taskMapper.ifFixRoom(task);
    }

    @Override
    public Result getListByClassNo(String classNo) {
        List<Task> list = taskMapper.getListByClassNo(classNo);
        return Result.success("查询成功",list);
    }

    @Override
    public void updateAll(boolean check) {
        taskMapper.updateAll(check);
    }

    @Override
    public void updateAllByClassNo(boolean check,String classNo) {
        taskMapper.updateAllByClassNo(check,classNo);
    }

    @Override
    public void updateFixAll(boolean check) {
        taskMapper.updateFixAll(check);

    }

    @Override
    public void updateFixAllByClassNo(boolean check, String classNo) {
        taskMapper.updateFixAllByClassNo(check,classNo);

    }

    private List<Timetable> decoding(List<String> resultList) {
        List<Timetable> timetableList = new ArrayList<>();
        for (String gene : resultList) {
            Timetable timetable = new Timetable();
            timetable.setClassNo(ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene));
            timetable.setCourseNo(ClassUtil.cutGene(ConstantInfo.COURSE_NO, gene));
            timetable.setTeacherNo(ClassUtil.cutGene(ConstantInfo.TEACHER_NO, gene));
            timetable.setRoomNo(ClassUtil.cutGene(ConstantInfo.ROOM_NO, gene));
            timetable.setTimeslot(Integer.parseInt(ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene)));
            timetable.setStartWeek(Integer.parseInt(ClassUtil.cutGene(ConstantInfo.START_WEEK, gene)));
            timetable.setEndWeek(Integer.parseInt(ClassUtil.cutGene(ConstantInfo.END_WEEK, gene)));
            timetable.setBiweekly(Integer.parseInt(ClassUtil.cutGene(ConstantInfo.BIWEEKLY, gene)));
            timetable.setDuration(ClassUtil.cutGene(ConstantInfo.DURATION, gene));
            timetableList.add(timetable);
        }
        return timetableList;
    }

    /**
     * 方式 开始给进化完的基因编码分配教室
     * method 在原来的编码中加上教室编号
     */
    private List<String> assignRoom(Map<String, List<String>> individualMap) {
        List<String> resultList = new ArrayList<>();
        List<String> resultGeneList = geneticAlgorithm.collectGene(individualMap);
        String RoomNo = "";
        for (String gene : resultGeneList) {
            String ifFixRoom = ClassUtil.cutGene(ConstantInfo.IFFIXROOM, gene);
            if(ifFixRoom.equals("1")){
                String classNo = ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene);
                RoomNo = classesMapper.selectFixRoomNo(classNo);
                if(RoomNo != null){
                    gene = gene + RoomNo;
                    resultList.add(gene);
                    continue;
                }
            }
            LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Room::getAreaNo, ClassUtil.cutGene(ConstantInfo.AREA_NO, gene));
            wrapper.eq(Room::getAttr, ClassUtil.cutGene(ConstantInfo.COURSE_ATTR, gene));
            List<Room> roomList = roomMapper.selectList(wrapper);
            // roomList - 在限制了areaNo和attr后的roomList
            RoomNo = issueRoom(gene, roomList, resultList);
            gene = gene + RoomNo;
            resultList.add(gene);
        }
        return resultList;
    }

    /**
     * 编码规则为：
     * +班级编号(8)
     * +教师编号(8)
     * +课程编号(8)
     * +课程属性(2)
     * +startWeek(2)
     * +endWeek(2)
     * +biweekly(1)
     * +areaNo(2)
     * +classCount(1)
     * +isFix(1)
     * +ifFixRoom(1)
     * +courseAttr(2)
     * +taskAttr(2)
     * +courseDepartmentNo(2)
     * +连排节次(1)
     * +上课时间(2)     isFix=0时随机分配上课时间
     * [+教室编号(20)]
     * 最新规则可见ConstantInfo.RULES
     */
    private List<String> coding(List<Task> taskList,int ifScheduledInNight,int ifExpInNight) {
        List<String> resultGeneList = new ArrayList<>();
        String isFix = "0"; // 之后有空再搞自定义固定上课时间
        for (Task task : taskList) {
            // 1，不固定上课时间，默认不再填充 00
            // 得到每周上课的节数，因为设定两学时为一节课
            int size = task.getWeeksNumber() / 2;
            for (int i = 0; i < size; i++) {
                boolean ifFixRoom = task.isIfFixRoom();
                String ifFixRoomS = ifFixRoom ? "1" : "0";
                String gene = task.getClassNo()     // 8
                            + task.getTeacherNo()   // 8
                            + task.getCourseNo()    // 8
                            + task.getCourseAttr()  // 2
                            + task.getStartWeek()   // 2
                            + task.getEndWeek()     // 2
                            + task.getBiweekly()    // 1
                            + task.getAreaNo()      // 2
                            + task.getClassCount()  // 1
                            + isFix                 // 1
                            + ifFixRoomS            // 1
                            + task.getCourseAttr()  // 2
                            + task.getTaskAttr()    // 2
                            + task.getCourseDepartmentNo()    // 2
                            + task.getDuration()    // 1
                            + ClassUtil.randomTime(ifScheduledInNight,task.getCourseAttr(),ifExpInNight);// 2
                resultGeneList.add(gene);
            }
        }
        return resultGeneList;
    }

    /**
     * 检查taskList中，是否存在班级的学时总数超过ClassUtil.MAX_TIMESLOT * 2，是则抛出异常
     */
    private void checkWeeksNumber(List<Task> taskList) {
        // 参数List<Task> taskList
        taskList.stream().collect(Collectors.groupingBy(Task::getClassNo)).forEach((k, v) -> {
            int sum = v.stream().mapToInt(Task::getWeeksNumber).sum();
            if (sum > (ClassUtil.MAX_WEEK_TIMESLOT+1) * 2) {
                throw new CourseArrangeException(String.format("班级：%s 的学时超过 %s，不能排课！", k, ClassUtil.MAX_WEEK_TIMESLOT * 2));
            }
        });
    }

    /********************************************************************************************************************/

    /** 给不同的基因编码分配教室
     * @param gene          需要分配教室的基因编码
     * @param roomList      教室(限制了areaNo和attr)
     * @param resultList    已分配了教室的编码
     */
    private String issueRoom(String gene, List<Room> roomList, List<String> resultList) {
        String classNo = ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene);
        int studentSize = classesMapper.selectStuSize(classNo);
        return chooseClassroom(studentSize, gene, roomList, resultList);
    }


    /**
     * 给不同课程的基因编码随机选择一个教室
     *
     * @param studentNum    开课的班级的学生人数
     * @param gene          需要安排教室的基因编码
     * @param roomList 教室
     * @description 这是一个递归方法
     */
    private String chooseClassroom(int studentNum,
                                   String gene,
                                   List<Room> roomList,
                                   List<String> resultList) {
        int min = 0;
        int max = roomList.size() - 1;
        int temp = min + (int) (Math.random() * (max + 1 - min));
        Room classroom = roomList.get(temp);
        // 分配教室
        boolean isRoomSuitable = judgeClassroom(studentNum, gene, classroom, resultList);
        if (isRoomSuitable) {
            // 该教室满足条件
            return classroom.getRoomNo();
        } else {
            // 不满足，继续找教室
            return chooseClassroom(studentNum, gene, roomList, resultList);
        }
    }

    /**
     * 判断教室是否符合上课班级所需
     * 即：不同属性的课要放在对应属性的教室上课
     * @param room  随机分配教室
     */
    private boolean judgeClassroom(int studentNum, String gene, Room room, List<String> resultList) {
        if (room.getCapacity() >= studentNum) {
            // 判断该教室上课时间是否重复
            return isFree(gene, resultList, room);
        } else {
            return false;
        }
    }

    /**
     * 判断同一时间同一个教室是否有多个班级使用
     */
    private Boolean isFree(String gene, List<String> resultList, Room room) {
        if (resultList.isEmpty()) {
            return true;
        } else {
            List<ExclusionRule> exRoomList = exclusionRuleMapper.getRoomList();
            int timeslot = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene));
            String roomNo1 = room.getRoomNo();
            String timeslot1 = ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene);
            int startWeek1 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.START_WEEK, gene));
            int endWeek1 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.END_WEEK, gene));
            int biweekly1 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.BIWEEKLY, gene));
            Set<Integer> forbiddenSlots = new HashSet<>();
            // 判断是否是禁排节次
            for(ExclusionRule rule : exRoomList) {
                if(rule.getRoomNo().equals(roomNo1)) {
                    forbiddenSlots.add(rule.getTimeslot());
                }
            }
            if(forbiddenSlots.contains(timeslot)){
                return false;
            }
            // 判断同一时间同一个教室是否有多个班级使用
            for (String resultGene : resultList) {
                String roomNo2 = ClassUtil.cutGene(ConstantInfo.ROOM_NO, resultGene);
                String timeslot2 = ClassUtil.cutGene(ConstantInfo.TIMESLOT, resultGene);
                int startWeek2 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.START_WEEK, resultGene));
                int endWeek2 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.END_WEEK, resultGene));
                int biweekly2 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.BIWEEKLY, resultGene));

                boolean intervalOverlap = startWeek1 <= endWeek2 && startWeek2 <= endWeek1;
                boolean timeOverlap = timeslot1.equals(timeslot2) && intervalOverlap && biweekly1+biweekly2 != 3;
                if (timeOverlap && roomNo1.equals(roomNo2)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 增加连排课程
    private List<String> addDurationCourse(List<String> resultGeneList) {
        List<String> afterAddresultGeneList = new ArrayList<>();
        for (String gene : resultGeneList) {
            if(ClassUtil.cutGene(ConstantInfo.DURATION, gene).equals("4")){
                String roomNo = ClassUtil.cutGene(ConstantInfo.ROOM_NO, gene);
                int timeslot = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene));
                int afterAddTimeslot = timeslot + 1;
                String newTimeslot = afterAddTimeslot < 10 ? ("0" + afterAddTimeslot) : String.valueOf(afterAddTimeslot);
                String newGene = gene.substring(0, ClassUtil.getTimeslotStartIndex()) + newTimeslot + roomNo;
                afterAddresultGeneList.add(newGene);
                afterAddresultGeneList.add(gene);
            }else if(ClassUtil.cutGene(ConstantInfo.DURATION, gene).equals("2"))
            {
                afterAddresultGeneList.add(gene);
            }
        }
        return afterAddresultGeneList;
    }
}









