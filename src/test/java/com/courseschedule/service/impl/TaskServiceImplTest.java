package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Semester;
import com.courseschedule.entity.Timetable;
import com.courseschedule.mapper.TimetableMapper;
import com.courseschedule.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 把大象放进冰箱只需要三步
 */
@SpringBootTest
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TimetableMapper timetableMapper;

    // 一、导入
    @Test
    public void inputTask() {
        // // 1班 高数 1周 2节
        // Task task1 = new Task(1,"1","1","高数",
        //         "1","张三","01",50,1,
        //         2,1,1,0,1,3.0);
        // // 1班 JAVA 1周 2节
        // Task task2 = new Task(2,"1","2","JAVA",
        //         "2","李四","01",50,1,
        //         2,1,1,0,1,4.0);
        // taskService.save(task1);
        // taskService.save(task2);
    }

    // 二、排课
    @Test
    public void courseScheduling() {
        Semester semester = new Semester();
        semester.setSemesterName("2023-2024学年 第1学期");
        semester.setSemesterWeeksSum(20);
        Result result = taskService.courseScheduling(semester);
        System.err.println(result);
    }

    // 三、导出
    @Test
    public void outputTimetable() {
        System.err.println("按班级输出");
        // 找1班的课表
        List<Timetable> timetableList = timetableMapper.selectList(new LambdaQueryWrapper<Timetable>()
                .eq(Timetable::getClassNo, "1"));
        System.err.println("周几\t几节\t课程\t老师\t教室");
        System.err.println("---------------------------------------------");
        for (Timetable t : timetableList) {
            // TIME(WEEK-SECTION) - COURSE - TEACHER - ROOM
            // 假设这个学期的课没有单双周，错开周这种阴间玩意
            // 周一的timeslot为01234,以此类推到周日
            // 自动排课中"不排周末"是硬约束,但是手动是可以调到周末的
            Integer weekday = t.getTimeslot() / 5 + 1;//因为数据库数据是从0开始计算所以表示时要+1
            Integer section = t.getTimeslot() % 5 + 1;
            System.err.println("周"+weekday+"\t\t"+section+"节\t\t" +
                    t.getCourseNo() +"班\t\t"+ t.getTeacherNo() +"师\t\t"+ t.getRoomNo()+"室");
        }
    }


}
