package com.courseschedule.entity;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 排练课程表
 * </p>
 *
 * @author 
 * @since 2024-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TimetableRehearsal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 讲师编号
     */
    private String teacherNo;

    /**
     * 教室编号
     */
    private String roomNo;

    /**
     * 上课时间(大节)
     */
    private Integer timeslot;

    /**
     * 开始周
     */
    private Integer startWeek;

    /**
     * 结束周
     */
    private Integer endWeek;

    /**
     * 单双周 0代表非单双周 1代表单周 2代表双周
     */
    private Integer biweekly;

    /**
     * 连排节次，只能有2或4
     */
    private String duration;


}
