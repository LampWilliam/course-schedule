package com.courseschedule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * [output]课程表
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Timetable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班级编号
     */
    private String classesNo;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 讲师编号
     */
    private String teacherNo;

    /**
     * 教室编号
     */
    private String roomNo;

    /**
     * 上课时间
     */
    private String timeslot;

    private Integer taskId;


}
