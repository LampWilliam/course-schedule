package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.TimeTableClassDto;
import com.courseschedule.common.dto.TimetableDto;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.entity.*;
import com.courseschedule.mapper.TimetableMapper;
import com.courseschedule.mapper.TimetableRehearsalMapper;
import com.courseschedule.service.*;
import com.courseschedule.utils.ClassUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * [output]课程表 服务实现类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@Service
public class TimetableServiceImpl extends ServiceImpl<TimetableMapper, Timetable> implements TimetableService {

    @Autowired
    private TimetableMapper timetableMapper;

    @Autowired
    private TimetableRehearsalMapper timetableRehearsalMapper;

    @Resource
    private RoomService roomService;
    @Resource
    private CourseService courseService;
    @Resource
    private ClassesService classesService;
    @Resource
    private TeacherService teacherService;

    /*****************************手动排课的冲突检查*************************************/
    @Override
    public Result getConflictingColumnNameList(Long semesterId, String columnName) {
        boolean columnNameError
                = !"class_no".equals(columnName)
                && !"teacher_no".equals(columnName)
                && !"room_no".equals(columnName);
        if (columnNameError) {
            return Result.error("传入字段名columnName有误");
        }
        List<String> list = timetableMapper.getConflictingColumnNameList(semesterId, columnName);
        String msg = "成功获取" + semesterId + "学期课表中存在冲突的" + columnName + "列表";
        return Result.success(msg, list);
    }

    @Override
    public Result getConflictingTimeslotIdList(Long semesterId, String columnName, String no) {
        boolean columnNameError
                = !"class_no".equals(columnName)
                && !"teacher_no".equals(columnName)
                && !"room_no".equals(columnName);
        if (columnNameError) {
            return Result.error("传入字段名columnName有误");
        }

        List<Long> list = timetableMapper.getConflictingTimeslotIdList(semesterId, columnName, no);
        String msg = "成功获取 " + semesterId + "学期课表中" + columnName + "=" + no + "中存在冲突的timetable.id";
        return Result.success(msg, list);
    }

    @Override
    public Result getConfilctList(Long id) {
        Timetable timetable = super.getById(id);
        if (timetable == null || timetable.getIsDeleted().equals(BaseVo.DELETED)) {
            Result.error("待查询课表字段不存在！");
        }

        List<TimetableDto> list = timetableMapper.getConfilctList(id);
        String msg = "成功获取与timetable.id=" + id +
                "存在冲突的其它timetable.*，并返回冲突类型";
        return Result.success(msg, list);
    }

    @Override
    public Result rehearsalChangeTimeslot(Long id) {
        Timetable timetable = super.getById(id);
        if (timetable == null || timetable.getIsDeleted().equals(BaseVo.DELETED)) {
            System.out.println("into this");
            Result.error("待查询课表字段不存在！");
        }

        // 4.1 清空演练表
        timetableRehearsalMapper.deleteAll();
        // 4.2 准备排练数据
        System.out.println(timetableRehearsalMapper);
        Long semesterId = timetable.getSemesterId();

        System.out.println(semesterId);
        timetableRehearsalMapper.insertRehearsalData(semesterId);// 空指针异常
        // 4.3 JAVA那边写循环
        int timeslot1 = timetable.getTimeslot();
        List list = new ArrayList<Integer>();
        for (int timeslot2 = 0; timeslot2 <= ClassUtil.MAX_WEEK_TIMESLOT; timeslot2++) {
            if (timeslot1 == timeslot2) {
                continue;
            }
            // 4.3.1 更新排练数据
            timetableRehearsalMapper.updateRehearsalData(id, timeslot2);
            // 4.3.2 进行排练 返回0条数据表示无冲突，其它情况(其实也就只会返回1条) 表示有冲突
            TimetableRehearsal conflictingSelf = timetableRehearsalMapper.rehearsalChangeTimeslot(id);

            if (conflictingSelf == null) {
                list.add(timeslot2);
            }
        }
        String msg = "成功获取(timetable.id=" + id + "的字段的)可变动目的时间";
        return Result.success(msg, list);
    }

    @Override
    public Result getList() {
        LambdaQueryWrapper<Timetable> wrapper = new LambdaQueryWrapper<Timetable>()
                .eq(Timetable::getIsDeleted, BaseVo.NOT_DELETED).groupBy(Timetable::getTimeslot);
        List<Timetable> timetableList = super.list(wrapper);

        if (null == timetableList || timetableList.isEmpty()) {
            return Result.error("该班级没有课表");
        }
        // 过滤出班级、课程、教室编号
        List<String> classNos = timetableList.stream().map(Timetable::getClassNo).distinct().collect(Collectors.toList());
        List<String> courseNos = timetableList.stream().map(Timetable::getCourseNo).distinct().collect(Collectors.toList());
        List<String> roomNos = timetableList.stream().map(Timetable::getRoomNo).distinct().collect(Collectors.toList());
        List<String> teacherNos = timetableList.stream().map(Timetable::getTeacherNo).distinct().collect(Collectors.toList());

        // 查询班级、课程、教室信息
        List<Classes> classes = classesService.list(new LambdaQueryWrapper<Classes>().in(Classes::getClassNo, classNos));
        List<Course> courseInfos = courseService.list(new LambdaQueryWrapper<Course>().in(Course::getCourseNo, courseNos));
        List<Room> rooms = roomService.list(new LambdaQueryWrapper<Room>().in(Room::getRoomNo, roomNos));
        List<Teacher> teachers = teacherService.list(new LambdaQueryWrapper<Teacher>().in(Teacher::getTeacherNo, teacherNos));

        List<TimeTableClassDto> timeTableClassDtos = new LinkedList<>();
        timetableList.forEach(v -> {
            // v 转换成 CoursePlanVo 对象
            TimeTableClassDto timeTableClassDto = new TimeTableClassDto();
            BeanUtils.copyProperties(v, timeTableClassDto);

            // 根据教师编号找到教师名称
            classes.stream().filter(t -> t.getClassNo().equals(v.getClassNo())).findFirst().ifPresent(t -> timeTableClassDto.setClassName(t.getClassName()));
            classes.stream().filter(c -> c.getClassNo().equals(v.getClassNo())).findFirst().ifPresent(c -> timeTableClassDto.setSize(c.getSize()));
            courseInfos.stream().filter(c -> c.getCourseNo().equals(v.getCourseNo())).findFirst().ifPresent(c -> timeTableClassDto.setCourseName(c.getCourseName()));
            rooms.stream().filter(r -> r.getRoomNo().equals(v.getRoomNo())).findFirst().ifPresent(r -> timeTableClassDto.setRoomName(r.getRoomName()));
            rooms.stream().filter(r -> r.getRoomNo().equals(v.getRoomNo())).findFirst().ifPresent(r -> timeTableClassDto.setAreaName(r.getAreaName()));
            teachers.stream().filter(t -> t.getTeacherNo().equals(v.getTeacherNo())).findFirst().ifPresent(t -> timeTableClassDto.setTeacherName(t.getTeacherName()));
            timeTableClassDtos.add(timeTableClassDto);
        });

        return Result.success("返回课表成功",timeTableClassDtos);
    }

    @Override
    public Result getTimetableByClassNo(String classNo) {
        LambdaQueryWrapper<Timetable> wrapper = new LambdaQueryWrapper<Timetable>().eq(Timetable::getClassNo, classNo).groupBy(Timetable::getTimeslot);
        List<Timetable> timetableList = super.list(wrapper);
        return Result.success("查询班级课表成功", timetableList);
    }

    @Override
    public Result queryTimetableByClassNo(List<Timetable> timetableList) {
        // 过滤出班级、课程、教室编号
        List<String> classNos = timetableList.stream().map(Timetable::getClassNo).distinct().collect(Collectors.toList());
        List<String> courseNos = timetableList.stream().map(Timetable::getCourseNo).distinct().collect(Collectors.toList());
        List<String> roomNos = timetableList.stream().map(Timetable::getRoomNo).distinct().collect(Collectors.toList());
        List<String> teacherNos = timetableList.stream().map(Timetable::getTeacherNo).distinct().collect(Collectors.toList());


        // 查询班级、课程、教室信息
        List<Classes> classes = classesService.list(new LambdaQueryWrapper<Classes>().in(Classes::getClassNo, classNos));
        List<Course> courseInfos = courseService.list(new LambdaQueryWrapper<Course>().in(Course::getCourseNo, courseNos));
        List<Room> rooms = roomService.list(new LambdaQueryWrapper<Room>().in(Room::getRoomNo, roomNos));
        List<Teacher> teachers = teacherService.list(new LambdaQueryWrapper<Teacher>().in(Teacher::getTeacherNo, teacherNos));

        List<TimeTableClassDto> timeTableClassDtos = new LinkedList<>();
        timetableList.forEach(v -> {
            // v 转换成 CoursePlanVo 对象
            TimeTableClassDto timeTableClassDto = new TimeTableClassDto();
            BeanUtils.copyProperties(v, timeTableClassDto);

            // 根据教师编号找到教师名称
            classes.stream().filter(t -> t.getClassNo().equals(v.getClassNo())).findFirst().ifPresent(t -> timeTableClassDto.setClassName(t.getClassName()));
            classes.stream().filter(c -> c.getClassNo().equals(v.getClassNo())).findFirst().ifPresent(c -> timeTableClassDto.setSize(c.getSize()));
            courseInfos.stream().filter(c -> c.getCourseNo().equals(v.getCourseNo())).findFirst().ifPresent(c -> timeTableClassDto.setCourseName(c.getCourseName()));
            rooms.stream().filter(r -> r.getRoomNo().equals(v.getRoomNo())).findFirst().ifPresent(r -> timeTableClassDto.setRoomName(r.getRoomName()));
            rooms.stream().filter(r -> r.getRoomNo().equals(v.getRoomNo())).findFirst().ifPresent(r -> timeTableClassDto.setAreaName(r.getAreaName()));
            teachers.stream().filter(t -> t.getTeacherNo().equals(v.getTeacherNo())).findFirst().ifPresent(t -> timeTableClassDto.setTeacherName(t.getTeacherName()));
            timeTableClassDtos.add(timeTableClassDto);
        });

        return Result.success("查询班级课表成功",timeTableClassDtos);
    }

    @Override
    public Result adjust(Long srcId, Integer destTimeslot) {
        Timetable timetable = super.getById(srcId); //根据前端传回来的课程id获取课程
        // 如果课程为空或已被删除，则返回“不存在”
        if(timetable==null || timetable.getIsDeleted().equals(BaseVo.DELETED)) {
            return Result.error("待调整课程不存在");
        }
        timetable.setTimeslot(destTimeslot);  // 调整课程时间（大节）
        boolean b = super.updateById(timetable);  // 更新课程表
        return b ? Result.success("调整课表成功") : Result.error("调整课表失败");
    }
}
