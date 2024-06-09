package com.courseschedule.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.lang.ServerResponse;
import com.courseschedule.entity.Classes;
import com.courseschedule.entity.Room;
import com.courseschedule.entity.Task;
import com.courseschedule.entity.Timetable;
import com.courseschedule.service.ClassesService;
import com.courseschedule.service.TimetableService;
import com.courseschedule.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 15760
 * @Date: 2020/5/12
 * @Descripe: 文件上传，读取文件
 */

@RestController
@Slf4j
public class UploadController {

    @Resource
    private UploadService uploadService;

    @Resource
    private TimetableService timetableService;

    /**
     * 上传课程计划Excel文件
     */
    @PostMapping("/upload")
    public Result uploadClassTaskFile(MultipartFile file) {
        log.info("上传 Excel 文件(待排课的文件)。。。");
        return uploadService.uploadTask(file);
    }

    /**
     * 上传班级Excel文件
     */
    @PostMapping("/uploadClass")
    public Result uploadClassesFile(MultipartFile file) {
        log.info("上传 Excel 文件(班级文件)。。。");
        return uploadService.uploadClass(file);
    }

    /**
     * 上传教室Excel文件
     */
    @PostMapping("/uploadRoom")
    public Result uploadRoomFile(MultipartFile file) {
        log.info("上传 Excel 文件(班级文件)。。。");
        return uploadService.uploadRoom(file);
    }

    /**
     * 下载系统提供的Excel导入模板
     */
    @GetMapping(value = "/download", consumes = MediaType.ALL_VALUE)
    public void downloadTemplate(HttpServletResponse response) {
        // 获取文件
        File file = new File("doc/导入模板/课程任务导入模板.xls");
        if (!file.exists()) {
            // 没有该模板文件就调用创建模板文件方法
            log.info("创建模板文件");
            createTemplate();
        }
        // 获取文件名字
        String fileName = file.getName();
        response.reset();
        // 设置ContentType，响应内容为二进制数据流，编码为utf-8，此处设定的编码是文件内容的编码
        response.setContentType("application/octet-stream;charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("文件下载失败: {}", e.getMessage());
        }
        // 实现文件下载
        byte[] buffer = new byte[1024];

        try (
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis)
        ) {
            // 获取字节流
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            log.info("文件下载成功");
        } catch (Exception e) {
            log.error("文件下载失败: {}", e.getMessage());
        }
    }

    /**
     * 下载系统提供的Excel导入模板
     */
    @GetMapping(value = "/downloadClass", consumes = MediaType.ALL_VALUE)
    public void downloadClassTemplate(HttpServletResponse response) {
        // 获取文件
        File file = new File("doc/导入模板/班级导入模板.xls");
        if (!file.exists()){
            // 没有该模板文件就调用创建模板文件方法
            log.info("创建模板文件");
            createClassTemplate();
        }
        // 获取文件名字
        String fileName = file.getName();
        response.reset();
        // 设置ContentType，响应内容为二进制数据流，编码为utf-8，此处设定的编码是文件内容的编码
        response.setContentType("application/octet-stream;charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("文件下载失败: {}", e.getMessage());
        }
        // 实现文件下载
        byte[] buffer = new byte[1024];

        try (
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis)
        ) {
            // 获取字节流
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            log.info("文件下载成功");
        } catch (Exception e) {
            log.error("文件下载失败: {}", e.getMessage());
        }
    }

    /**
     * 下载系统提供的Excel导入模板
     */
    @GetMapping(value = "/downloadRoom", consumes = MediaType.ALL_VALUE)
    public void downloadRoomTemplate(HttpServletResponse response) {
        // 获取文件
        File file = new File("doc/导入模板/教室导入模板.xls");
        if (!file.exists()){
            // 没有该模板文件就调用创建模板文件方法
            log.info("创建模板文件");
            createRoomTemplate();
        }
        // 获取文件名字
        String fileName = file.getName();
        response.reset();
        // 设置ContentType，响应内容为二进制数据流，编码为utf-8，此处设定的编码是文件内容的编码
        response.setContentType("application/octet-stream;charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("文件下载失败: {}", e.getMessage());
        }
        // 实现文件下载
        byte[] buffer = new byte[1024];

        try (
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis)
        ) {
            // 获取字节流
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            log.info("文件下载成功");
        } catch (Exception e) {
            log.error("文件下载失败: {}", e.getMessage());
        }
    }

    /**
     * 如果没有模板文件就创建模板文件
     */
    private void createTemplate() {
        ExportParams params = new ExportParams();
        params.setTitle("课程任务导入模板(请严格对照数据库信息填写)");
        params.setSheetName("课程任务模板");
        List<Task> list = new ArrayList<>();
        Workbook workbook = ExcelExportUtil.exportExcel(params, Task.class, list);
        try {
            // 输出模板到本地
            FileOutputStream fos = new FileOutputStream("doc/导入模板/课程任务导入模板_new.xls");
            workbook.write(fos);
        } catch (Exception e) {
            log.error("创建模板文件失败: {}", e.getMessage());
        }
    }

    /**
     * 如果没有模板文件就创建模板文件
     */
    @GetMapping(value = "/downloadTimeTableClass/{classNo}", consumes = MediaType.ALL_VALUE)
    private void downloadTimeTableClass(HttpServletResponse response,@PathVariable String classNo) throws IOException{
        ExportParams params = new ExportParams();
        params.setTitle("学期课表");
        LambdaQueryWrapper<Timetable> wrapper = new LambdaQueryWrapper<Timetable>().eq(Timetable::getClassNo, classNo);
        List<Timetable> timetableList = timetableService.list(wrapper);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Timetable.class, timetableList);
        // 设置HTTP响应头
        String fileName = URLEncoder.encode("学期课表", "UTF-8");
        response.setContentType("application/octet-stream;charset=utf-8");
//        response.setCharacterEncoding("utf-8");
        String headerValue = "attachment; filename*=UTF-8''" + fileName + ".xlsx";
        response.setHeader("Content-Disposition", headerValue);

        // 将Workbook写入到输出流
        try (OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        }

        // 关闭Workbook（如果它实现了AutoCloseable）
        if (workbook instanceof AutoCloseable) {
            try {
                ((AutoCloseable) workbook).close();
            } catch (Exception e) {
                // 忽略关闭异常或进行适当处理
            }
        }
    }

    /**
     * 如果没有模板文件就创建模板文件
     */
    private void createClassTemplate() {
        ExportParams params = new ExportParams();
        params.setTitle("班级导入模板(请严格对照数据库信息填写)");
        params.setSheetName("班级模板");
        List<Classes> list = new ArrayList<>();
        Workbook workbook = ExcelExportUtil.exportExcel(params, Classes.class, list);
        try {
            // 输出模板到本地
            FileOutputStream fos = new FileOutputStream("doc/导入模板/班级导入模板_new.xls");
            workbook.write(fos);
        } catch (Exception e) {
            log.error("创建模板文件失败: {}", e.getMessage());
        }
    }

    /**
     * 如果没有模板文件就创建模板文件
     */
    private void createRoomTemplate() {
        ExportParams params = new ExportParams();
        params.setTitle("教室导入模板(请严格对照数据库信息填写)");
        params.setSheetName("教室模板");
        List<Room> list = new ArrayList<>();
        Workbook workbook = ExcelExportUtil.exportExcel(params, Room.class, list);
        try {
            // 输出模板到本地
            FileOutputStream fos = new FileOutputStream("doc/导入模板/教室导入模板_new.xls");
            workbook.write(fos);
        } catch (Exception e) {
            log.error("创建模板文件失败: {}", e.getMessage());
        }
    }


}
