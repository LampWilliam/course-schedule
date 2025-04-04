package com.courseschedule.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.StudentVo;
import com.courseschedule.entity.Student;
import com.courseschedule.entity.TeacherComment;
import com.courseschedule.entity.Timetable;
import com.courseschedule.service.StudentService;
import com.courseschedule.service.TeacherCommentService;
import com.courseschedule.service.TimetableService;
import com.courseschedule.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Resource
    private TimetableService timetableService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherCommentService teacherCommentService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/commentList")
    public Result getTeacherCommentList() {
        List<TeacherComment> teacherComments = teacherCommentService.getList();
        return Result.success("查询成功", teacherComments);
    }

    @RequiresAuthentication
    @PostMapping("/commentList")
    public Result addTeacherComment(@RequestBody @Validated(TeacherComment.Add.class) TeacherComment teacherComment) {
        teacherCommentService.add(teacherComment);
        return Result.success("添加成功");
    }

    @RequiresAuthentication
    @PutMapping("/commentList")
    public Result updateTeacherComment(@RequestBody @Validated(TeacherComment.Update.class) TeacherComment teacherComment){
        teacherCommentService.update(teacherComment);
        return Result.success("修改成功");
    }


    @DeleteMapping("/commentList/{id}")
    public Result deleteTeacherComment(@PathVariable("id") Integer id){
        teacherCommentService.delete(id);
        return Result.success("删除成功");
    }

    @GetMapping("/getList")
    public Result getList() {
        List<Student> students = studentService.getList();
        return Result.success("查询成功", students);
    }

    @RequiresAuthentication
    @PostMapping
    public Result add(@RequestBody @Validated(Student.Add.class) Student student) {
        studentService.add(student);
        return Result.success("添加成功");
    }

    @RequiresAuthentication
    @PutMapping
    public Result update(@RequestBody @Validated(Student.Update.class) Student student){
        studentService.update(student);
        return Result.success("修改成功");
    }

    @RequiresAuthentication
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        studentService.delete(id);
        return Result.success("删除成功");
    }

    @GetMapping("/{id}")
    public Result getStudentMessage(@PathVariable("id") String id){
        return Result.success("查询成功",studentService.findByUserId(id));
    }

    /**
     * 根据班级编号查找课表
     */
    @GetMapping("/timeTable/{id}/{semesterId}")
    public Result getClassTimetable(@PathVariable("id") String id,@PathVariable("semesterId") Long semesterId) {
//        StudentDto studentDto = (StudentDto) SecurityUtils.getSubject().getPrincipal();
//        String classNo = studentDto.getClassNo();
        String classNo = studentService.getClassNo(id);
        LambdaQueryWrapper<Timetable> wrapper = new LambdaQueryWrapper<Timetable>()
                .eq(Timetable::getSemesterId, semesterId)
                .eq(Timetable::getClassNo, classNo)
                .orderByAsc(Timetable::getTimeslot);
        List<Timetable> timetableList = timetableService.list(wrapper);
        if (null == timetableList || timetableList.isEmpty()) {
            return Result.error("本学期没有课表",classNo);
        }
        return timetableService.queryTimetableByClassNo(timetableList);
    }
}
