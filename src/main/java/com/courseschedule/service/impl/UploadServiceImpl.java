package com.courseschedule.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.*;
import com.courseschedule.mapper.*;
import com.courseschedule.service.ClassesService;
import com.courseschedule.service.RoomService;
import com.courseschedule.service.TaskService;
import com.courseschedule.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private ClassesService classesService;

    @Resource
    private RoomService roomService;

    @Resource
    private TaskService classTaskService;

    @Autowired
    private ClassesMapper classesMapper;

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 文件上传实现并解析Excel存入数据库
     */
    @Override
    public Result uploadClass(MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);

        List<Classes> classesList = null;
        try {
            // classesList就是Excel文件中每一行的记录
            classesList = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    Classes.class, params
            );
        }catch (Exception e) {
            log.error("导入班级失败：{}", e.getMessage());
        }
        assert classesList != null;
        return saveClass(classesList) ? Result.success("导入班级成功") : Result.error("导入班级失败");
    }

    @Override
    public Result uploadTask(MultipartFile file) {

        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);

        List<Task> list = null;
        try {
            // list就是Excel文件中每一行的记录
            list = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    Task.class, params);
        } catch (Exception e) {
            log.error("导入课程任务失败: {}", e.getMessage());
        }
        // 调用课程任务存入数据库方法
        assert list != null;


        return save(list) ? Result.success("导入课程任务成功") : Result.error("导入课程任务失败");
    }





    @Override
    public Result uploadRoom(MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);

        List<Room> roomList = null;
        try {
            // roomList就是Excel文件中每一行的记录
            roomList = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    Room.class, params);
        } catch (Exception e) {
            log.error("导入教室失败: {}", e.getMessage());
        }
        // 调用课程任务存入数据库方法
        assert roomList != null;
        return saveRoom(roomList) ? Result.success("导入教室成功") : Result.error("导入教室失败");
    }

    /**
     * 将文件中的数据插入数据库
     */
    private boolean save(List<Task> list) {
        // 清空旧任务
        clearClassTaskOld();
        int i = 0;
        // 遍历课程任务插入数据库
        for (Task task : list) {
            Task c = new Task();
            BeanUtils.copyProperties(task, c);

            boolean b = classTaskService.save(c);

            Teacher teacher = new Teacher();
            teacher.setTeacherNo(c.getTeacherNo());
            teacher.setTeacherName(c.getTeacherName());

            Course course = new Course();
            course.setCourseNo(c.getCourseNo());
            course.setCourseName(c.getCourseName());
            course.setCourseAttr(c.getCourseAttr());

            Integer count1 = teacherMapper.selectCount(new LambdaQueryWrapper<>(teacher).eq(Teacher::getTeacherNo, teacher.getTeacherNo()));
            if (count1 == 0) {
                teacherMapper.insert(teacher);
            }
            Integer count2 = courseMapper.selectCount(new LambdaQueryWrapper<>(course).eq(Course::getCourseNo, course.getCourseNo()));
            if (count2 == 0) {
                courseMapper.insert(course);
            }



            if (b) {
                i+=1;
            }
        }
        return i == list.size();
    }

    private boolean saveClass(List<Classes> list) {
        int i = 0;
        int a = 0;
        // 遍历课程任务插入数据库
        for (Classes classes : list) {
            Classes c = new Classes();
            List<Classes> cs = classesMapper.selectClassNo();
            for (int j = 0; j < cs.size(); j++) {
                // 判断Excel表的班级编号是否有与数据库一致的
                if(Objects.equals(cs.get(j).getClassNo(), classes.getClassNo())){
                    a += 1;
                }
            }
            // 有于数据库班级编号一致的数据就结束该循环
            if (a != 0){
                break;
            }
            BeanUtils.copyProperties(classes, c);
            boolean b = classesService.save(c);
            if (b) {
                i+=1;
            }
        }
        return i == list.size();
    }

    private boolean saveRoom(List<Room> list) {
        int i = 0;
        int a = 0;
        // 遍历课程任务插入数据库
        for (Room room : list) {
            Room c = new Room();
            List<Room> rm = roomMapper.selectRoomNo();
            for (int j = 0; j < rm.size(); j++) {
                // 判断Excel表的教室编号是否有与数据库一致的
                if(Objects.equals(rm.get(j).getRoomNo(), room.getRoomNo())){
                    a += 1;
                }
            }
            if (a != 0){
                break;
            }
            BeanUtils.copyProperties(room, c);
            boolean b = roomService.save(c);
            if (b) {
                i+=1;
            }
        }
        return i == list.size();
    }

    /**
     * 清空旧的课程任务
     */
    private void clearClassTaskOld() {
        taskMapper.clearClassTaskOld();
    }

}
