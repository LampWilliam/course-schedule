package com.courseschedule.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Task;
import com.courseschedule.entity.Timetable;
import com.courseschedule.service.TimetableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * [output]课程表 前端控制器
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@RestController
@RequestMapping("/timetable")
public class TimetableController {


    @Resource
    private TimetableService timetableService;

    /*****************************手动排课的冲突检查*************************************/

    /** 1. 获取第 semesterId 学期课表中
     * 存在冲突的 columnName
     * @param columnName 取值可为 class_no/teacher_no/room_no
     */
    @GetMapping("/getConflictingColumnNameList/{semesterId}/{columnName}")
    public Result getConflictingColumnNameList(
            @PathVariable Long semesterId,
            @PathVariable String columnName) {
        return timetableService.getConflictingColumnNameList(semesterId, columnName);
    }

    /** 2. 获取第 semesterId 学期课表中
     * columnName = no 中存在冲突的 timetable.id
     * @param columnName 取值可为 class_no/teacher_no/room_no
     */
    @GetMapping("/getConflictingTimeslotIdList/{semesterId}/{columnName}/{no}")
    public Result getConflictingTimeslotIdList(
            @PathVariable Long semesterId,
            @PathVariable String columnName,
            @PathVariable String no) {
        return timetableService.getConflictingTimeslotIdList(semesterId, columnName, no);
    }

    /** 3. 获取与timetable.id=id存在冲突的其它timetable.*，并返回冲突类型
     */
    @GetMapping("/getConfilctList/{id}")
    public Result getConfilctList(@PathVariable Long id) {
        return timetableService.getConfilctList(id);
    }

    /** 4. 已知timetable.timeslot的取值为0~24。
     * 获取 timetable.id=id 的字段中 timeslot 改为其它所有取值后不存在冲突的情况对应的 timeslot
     * @方法 多次假装改时间 看可不可以无冲突
     * @目的 获取改动后无冲突目的时间们，用于前端提示用户
     * @英语相关 rehearsal - 排练
     */
    @GetMapping("/rehearsalChangeTimeslot/{id}")
    public Result rehearsalChangeTimeslot(@PathVariable Long id) {
        return timetableService.rehearsalChangeTimeslot(id);
    }


    /*****************************getXxx getXxxList*************************************/
    /**
     * 根据班级编号查找课表
     */
    @GetMapping("/{classNo}")
    public Result getTimetable(@PathVariable String classNo) {
        return timetableService.getTimetableByClassNo(classNo);
    }

    /**
     * 根据班级编号查找课表
     */
    @GetMapping("/class/{semesterId}/{classNo}")
    public Result getClassTimetable(@PathVariable("semesterId") String semesterId,
                                    @PathVariable("classNo") String classNo) {
        LambdaQueryWrapper<Timetable> wrapper = new LambdaQueryWrapper<Timetable>()
                .eq(Timetable::getSemesterId, semesterId)
                .eq(Timetable::getClassNo, classNo)
                .orderByAsc(Timetable::getTimeslot);
        List<Timetable> timetableList = timetableService.list(wrapper);
        if (null == timetableList || timetableList.isEmpty()) {
            return Result.error("该班级在该学期没有课表");
        }
        return timetableService.queryTimetableByClassNo(timetableList);
    }

    /**
     * 根据教室编号查找课表
     */
    @GetMapping("/room/{semesterId}/{roomNo}")
    public Result getRoomTimetable(@PathVariable("semesterId") String semesterId,
                                    @PathVariable("roomNo") String roomNo) {
        LambdaQueryWrapper<Timetable> wrapper = new LambdaQueryWrapper<Timetable>()
                .eq(Timetable::getSemesterId, semesterId)
                .eq(Timetable::getRoomNo, roomNo)
                .orderByAsc(Timetable::getTimeslot);
        List<Timetable> timetableList = timetableService.list(wrapper);
        if (null == timetableList || timetableList.isEmpty()) {
            return Result.error("该教室在该学期没有课表");
        }
        return timetableService.queryTimetableByClassNo(timetableList);
    }

    /**
     * 根据教师编号查找课表
     */
    @GetMapping("/teacher/{semesterId}/{teacherNo}")
    public Result getTeacherTimetable(@PathVariable("semesterId") String semesterId,
                                   @PathVariable("teacherNo") String teacherNo) {
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
    // 查询所有班级课表(根据班级查找课表)
    /**
     * 查询开课任务
     */
    @GetMapping("/getList")
    public Result getList() {
        return timetableService.getList();
    }

    // 根据教师编号查找课表
    // 查询所有教师课表


    // 根据教室编号查找课表
    // 查询所有教室课表



}
