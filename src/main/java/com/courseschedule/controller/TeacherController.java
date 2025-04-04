package com.courseschedule.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Teacher;
import com.courseschedule.entity.Timetable;
import com.courseschedule.service.TeacherService;
import com.courseschedule.service.TimetableService;
import com.courseschedule.utils.JwtUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 教师 前端控制器
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TimetableService timetableService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentScoreService studentScoreService;

    private String teacherToken;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/getList")
    public Result getList() {
        return teacherService.getList();
    }

    @RequiresAuthentication
    @PostMapping
    public Result add(@RequestBody @Validated(Teacher.Add.class) Teacher teacher) {
        teacherService.add(teacher);
        return Result.success("添加成功");
    }

    @RequiresAuthentication
    @PutMapping
    public Result update(@RequestBody @Validated(Teacher.Update.class) Teacher teacher){
        teacherService.update(teacher);
        return Result.success("修改成功");
    }

    @RequiresAuthentication
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        teacherService.delete(id);
        return Result.success("删除成功");
    }

    /**
     * 根据教师编号查找课表
     */
    @GetMapping("/timeTable/{semesterId}/{id}")
    public Result getTeacherTimetable(@PathVariable("semesterId") Long semesterId, @PathVariable("id") String id) {
        String teacherNo = teacherService.getClassNo(id);

        LambdaQueryWrapper<Timetable> wrapper = new LambdaQueryWrapper<Timetable>()
                .eq(Timetable::getSemesterId, semesterId)
                .eq(Timetable::getTeacherNo, teacherNo)
                .orderByAsc(Timetable::getTimeslot);
        List<Timetable> timetableList = timetableService.list(wrapper);
        if (null == timetableList || timetableList.isEmpty()) {
            return Result.error("该教师在该学期没有课表");
        }
        return timetableService.queryTimetableByClassNo(timetableList);
    }

    /**
     * 根据教师编号查找教师评价
     */
    @GetMapping("/teacherComment/{id}")
    public Result getTeacherComment(@PathVariable String id) {
        String teacherNo = teacherService.getClassNo(id);
        return Result.success("查找成功",teacherService.getTeacherComment(teacherNo));
    }

    @GetMapping("/studentScore")
    public Result getStudentScoreList() {
        List<StudentScore> studentScore = studentScoreService.getList();
        return Result.success("查询成功", studentScore);
    }

    @GetMapping("/studentScore/{id}")
    public Result getScoreListGroupBYClass(@PathVariable String id) {
        List<StudentScore> studentScore = studentScoreService.getListGroupBYClass(id);
        return Result.success("查询成功", studentScore);
    }

    @RequiresAuthentication
    @PostMapping("/studentScore")
    public Result addStudentScore(@RequestBody @Validated(StudentScore.Add.class) StudentScore studentScore) {
        studentScoreService.add(studentScore);
        return Result.success("添加成功");
    }

    @RequiresAuthentication
    @PutMapping("/studentScore")
    public Result updateStudentScore(@RequestBody @Validated(StudentScore.Update.class) StudentScore studentScore){
        studentScoreService.update(studentScore);
        return Result.success("修改成功");
    }

    @RequiresAuthentication
    @DeleteMapping("/studentScore/{id}")
    public Result deleteStudentScore(@PathVariable("id") Integer id){
        studentScoreService.delete(id);
        return Result.success("删除成功");
    }

    @RequiresAuthentication
    @GetMapping("/studentScore/classSort/{id}")
    public Result getClassScoreListSoreted(@PathVariable String id){
        List<StudentScore> studentScore = studentScoreService.getListGroupBYClass(id);
        Collections.sort(studentScore, new Comparator<StudentScore>() {
            @Override
            public int compare(StudentScore o1, StudentScore o2) {
                if (o1.getScore() != o2.getScore()) {
                    return o2.getScore() - o1.getScore(); // 降序排列
                } else {
                    return o1.getId().compareTo(o2.getId()); // 升序排列
                }
            }
        });
        return Result.success("查询成功", studentScore);
    }

    @RequiresAuthentication
    @GetMapping("/studentScore/sort")
    public Result getScoreListSoreted(){
        List<StudentScore> studentScore = studentScoreService.getList();
        Collections.sort(studentScore, new Comparator<StudentScore>() {
            @Override
            public int compare(StudentScore o1, StudentScore o2) {
                if (o1.getScore() != o2.getScore()) {
                    return o2.getScore() - o1.getScore(); // 降序排列
                } else {
                    return o1.getId().compareTo(o2.getId()); // 升序排列
                }
            }
        });
        return Result.success("查询成功", studentScore);
    }

}
