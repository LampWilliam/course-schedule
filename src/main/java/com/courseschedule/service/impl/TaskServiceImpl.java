package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.algorithm.GeneticAlgorithm;
import com.courseschedule.common.exception.CourseArrangeException;
import com.courseschedule.common.lang.ConstantInfo;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Room;
import com.courseschedule.entity.Task;
import com.courseschedule.entity.Timetable;
import com.courseschedule.mapper.ClassesMapper;
import com.courseschedule.mapper.RoomMapper;
import com.courseschedule.mapper.TaskMapper;
import com.courseschedule.mapper.TimetableMapper;
import com.courseschedule.service.TaskService;
import com.courseschedule.utils.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private TaskMapper taskMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private TimetableMapper timetableMapper;
    @Autowired
    private GeneticAlgorithm geneticAlgorithm;

    /**
     * <h1>排课算法<h1/>
     */
    @Transactional(rollbackFor = Exception.class)//该方法中抛出Exception及其子类时，当前事务回滚
    @Override
    public Result courseScheduling() {
        try {
            long start = System.currentTimeMillis();
            log.info("开始排课,时间:" + start);
            // 1. 获得排课任务表
            List<Task> taskList = taskMapper.selectList(new LambdaQueryWrapper<Task>());
            if (null == taskList || taskList.isEmpty()) {
                return Result.error("排课失败,查询不到排课任务!请导入排课任务再进行排课");
            }
            // 校验学时是否超过课表的容纳值
            checkWeeksNumber(taskList);
            // 2. 将开课任务的各项信息进行编码成染色体，分为固定时间与不固定时间
            Map<String, List<String>> geneMap = coding(taskList);
            // 3. 给初始基因编码随机分配时间，得到同班上课时间不冲突的编码
            List<String> resultGeneList = codingTime(geneMap);
            // 4. 将分配好时间的基因编码以班级分类成为以班级的个体，得到班级的不冲突时间初始编码
            Map<String, List<String>> individualMap = geneticAlgorithm.transformIndividual(resultGeneList);
            // 5. 遗传进化(这里面已经处理完上课时间)
            individualMap = geneticAlgorithm.geneticEvolution(individualMap);

            // 6. 分配教室并做教室冲突检测
            List<String> resultList = finalResult(individualMap);
            // 7. 解码
            List<Timetable> timetableList = decoding(resultList);
            // 8. 课程表写入数据库
            timetableMapper.deleteAllPlan();
            for (Timetable timetable : timetableList) {
                timetableMapper.insert(timetable);
            }
            long timeConsume = System.currentTimeMillis() - start;
            log.info("完成排课,耗时:" + timeConsume);
            return Result.success(String.format("排课成功,耗时:%sms", timeConsume));
        } catch (Exception e) {
            return Result.error("排课失败,出现异常!");
        }
    }

    private List<Timetable> decoding(List<String> resultList) {
        List<Timetable> timetableList = new ArrayList<>();
        for (String gene : resultList) {
            Timetable timetable = new Timetable();
            timetable.setClassesNo(ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene));
            timetable.setCourseNo(ClassUtil.cutGene(ConstantInfo.COURSE_NO, gene));
            timetable.setTeacherNo(ClassUtil.cutGene(ConstantInfo.TEACHER_NO, gene));
            timetable.setRoomNo(ClassUtil.cutGene(ConstantInfo.ROOM_NO, gene));
            timetable.setTimeslot(ClassUtil.cutGene(ConstantInfo.CLASS_TIME, gene));
            timetableList.add(timetable);
        }
        return timetableList;
    }

    /**
     * 开始给进化完的基因编码分配教室，即在原来的编码中加上教室编号
     */
    private List<String> finalResult(Map<String, List<String>> individualMap) {
        List<String> resultList = new ArrayList<>();
        List<String> resultGeneList = geneticAlgorithm.collectGene(individualMap);
        String RoomNo = "";
        List<String> gradeList = taskMapper.selectByColumnName(ConstantInfo.GRADE_NO);
        Map<String, List<String>> gradeMap = collectGeneByGrade(resultGeneList, gradeList);
        for (Map.Entry<String, List<String>> entry : gradeMap.entrySet()) {
            String gradeNo = entry.getKey();
            List<String> teachBuildNoList = teachBuildInfoDao.selectTeachBuildList(gradeNo);

            List<String> gradeGeneList = gradeMap.get(gradeNo);

            LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<Room>().in(Room::getTeachbuildNo, teachBuildNoList);
            List<Room> RoomList2 = roomMapper.selectList(wrapper);

            for (String gene : gradeGeneList) {
                // 分配教室
                RoomNo = issueRoom(gene, RoomList2, resultList);
                gene = gene + RoomNo;
                resultList.add(gene);
            }
        }
        return resultList;
    }

    private List<String> codingTime(Map<String, List<String>> geneMap) {
        return null;
    }

    private Map<String, List<String>> coding(List<Task> taskList) {
        return null;
    }

    /**
     * 待修改:这个用sql可以直接解决啊，为什么要用JAVA来写，没懂
     * 检查taskList中，是否存在班级的学时总数超过ClassUtil.MAX_CLASS_TIME * 2，是则抛出异常
     */
    private void checkWeeksNumber(List<Task> taskList) {
        taskList.stream().collect(Collectors.groupingBy(Task::getClassNo)).forEach((k, v) -> {
            int sum = v.stream().mapToInt(Task::getWeeksNumber).sum();
            if (sum > ClassUtil.MAX_CLASS_TIME * 2) {
                throw new CourseArrangeException(String.format("班级：%s 的学时超过 %s，不能排课！", k, ClassUtil.MAX_CLASS_TIME * 2));
            }
        });
    }

    /********************************************************************************************************************/

    /**
     * 给不同的基因编码分配教室
     *
     * @param gene          需要分配教室的基因编码
     * @param RoomList      教室
     * @param resultList    分配有教室的编码
     */
    private String issueRoom(String gene, List<Room> RoomList, List<String> resultList) {
        // 处理特殊课程，实验课，体育课
        List<Room> sportBuilding = roomMapper.selectByTeachbuildNo("12");
        List<Room> experimentBuilding = roomMapper.selectByTeachbuildNo("08");
        String classNo = ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene);
        int studentNum = classesMapper.selectStuNum(classNo);
        String courseAttr = ClassUtil.cutGene(ConstantInfo.COURSE_ATTR, gene);

        if (courseAttr.equals(ConstantInfo.EXPERIMENT_COURSE)) {
            // 03 为实验课
            return chooseClassroom(studentNum, gene, experimentBuilding, resultList);
        } else if (courseAttr.equals(ConstantInfo.PHYSICAL_COURSE)) {
            // 04为体育课
            return chooseClassroom(studentNum, gene, sportBuilding, resultList);
        } else {
            // 剩下主要课程、次要课程都放在普通的教室
            // 如果还有其他课程另外加判断课程属性，暂时设定4种：理论，实验，实践，体育。音乐舞蹈那些不算
            return chooseClassroom(studentNum, gene, RoomList, resultList);
        }
    }


    /**
     * 给不同课程的基因编码随机选择一个教室
     *
     * @param studentNum    开课的班级的学生人数
     * @param gene          需要安排教室的基因编码
     * @param classroomList 教室
     */
    private String chooseClassroom(int studentNum, String gene, List<Room> classroomList, List<String> resultList) {
        int min = 0;
        int max = classroomList.size() - 1;
        int temp = min + (int) (Math.random() * (max + 1 - min));
        Room classroom = classroomList.get(temp);
        // 分配教室
        boolean isClassRoomSuitable = judgeClassroom(studentNum, gene, classroom, resultList);
        if (isClassRoomSuitable) {
            // 该教室满足条件
            return classroom.getRoomNo();
        } else {
            // 不满足，继续找教室
            return chooseClassroom(studentNum, gene, classroomList, resultList);
        }
    }

    /**
     * 判断教室是否符合上课班级所需
     * 即：不同属性的课要放在对应属性的教室上课
     * @param room  随机分配教室
     */
    private boolean judgeClassroom(int studentNum, String gene, Room room, List<String> resultList) {

        String courseAttr = ClassUtil.cutGene(ConstantInfo.COURSE_ATTR, gene);
        // 只要是语数英物化生政史地这些课程都是放在普通教室上课
        if (courseAttr.equals(ConstantInfo.MAIN_COURSE) || courseAttr.equals(ConstantInfo.SECONDARY_COURSE)) {
            // 找到普通教室，普通教室的属性都是01
            if (room.getAttr().equals(ConstantInfo.NORMAL_CLASS_ROOM)) {
                if (room.getCapacity() >= studentNum) {
                    // 还要判断该教室是否在同一时间有别的班级使用了
                    return isFree(gene, resultList, room);
                } else {
                    // 教室容量不够
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (ClassUtil.cutGene(ConstantInfo.COURSE_ATTR, gene).equals(room.getAttr())) {
                if (room.getCapacity() >= studentNum) {
                    // 判断该教室上课时间是否重复
                    return isFree(gene, resultList, room);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 判断同一时间同一个教室是否有多个班级使用
     */
    private Boolean isFree(String gene, List<String> resultList, Room classroom) {
        if (resultList.isEmpty()) {
            return true;
        } else {
            for (String resultGene : resultList) {
                if (ClassUtil.cutGene(ConstantInfo.ROOM_NO, resultGene).equals(classroom.getRoomNo())
                        && (ClassUtil.cutGene(ConstantInfo.CLASS_TIME, gene).equals(ClassUtil.cutGene(ConstantInfo.CLASS_TIME, resultGene)))) {
                    return false;
                }
            }
        }
        return true;
    }
}
