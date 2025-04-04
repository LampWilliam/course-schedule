package com.courseschedule.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.groups.Default;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PriorityRule {
    @JsonSerialize(using = ToStringSerializer.class) //适应前端--字段类型从数值转换为字符串
    @TableId(value = "id", type = IdType.ASSIGN_ID) //雪花算法
    private Long id;

    /**
     * 优先节次(大节)
     */
    @Excel(name = "优先节次（大节）")
    private Integer timeslot;

    /**
     * 开课院系编号
     */
    @Excel(name = "开课院系编号")
    private String courseDepartmentNo;

    /**
     * 开课院系
     */
    @Excel(name = "开课院系")
    private String courseDepartmentName;

    /**
     * 课程性质编号
     */
    @Excel(name = "课程性质编号")
    private String taskAttr;

    /**
     * 课程性质
     */
    @Excel(name = "课程性质")
    private String taskAttrName;

    /**
     * 学时类型编号
     */
    @Excel(name = "学时类型编号")
    private String courseAttr;

    /**
     * 学时类型
     */
    @Excel(name = "学时类型")
    private String courseAttrName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedTime;

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }

    public String getDepartmentNo(String DepartmentName) {
        if(DepartmentName.equals("计算机科学与技术学院")) {
            return "00";
        } else if (DepartmentName.equals("教育艺术学院")) {
            return "01";
        } else if (DepartmentName.equals("心理教学部")) {
            return "02";
        } else if (DepartmentName.equals("马克思主义学院")) {
            return "03";
        } else if (DepartmentName.equals("机械电气工程学院")) {
            return "04";
        } else if (DepartmentName.equals("公共基础学院")) {
            return "05";
        } else if (DepartmentName.equals("信息智能工程学院")) {
            return "06";
        }else if (DepartmentName.equals("汽车与智能交通学院")) {
            return "07";
        } else if (DepartmentName.equals("化学与材料工程学院")) {
            return "08";
        } else if (DepartmentName.equals("现代农业学院")) {
            return "09";
        }
        return "";
    }
    
    public String getTaskAttr(String TaskAttrName) {
        if (TaskAttrName.equals("必修课")) {
            return "01";
        } else if (TaskAttrName.equals("专业拓展课")) {
            return "02";
        } else if (TaskAttrName.equals("专业选修课")) {
            return "03";
        } else if (TaskAttrName.equals("公共必修课")) {
            return "04";
        }else if (TaskAttrName.equals("公共选修课")) {
            return "05";
        } else if (TaskAttrName.equals("公共基础课")) {
            return "06";
        }
        return "";
    }

    public String getCourseAttr(String courseAttrName) {
        if(courseAttrName.equals("理论")) {
            return "01";
        } else if (courseAttrName.equals("实验")) {
            return "02";
        }else if (courseAttrName.equals("实践")) {
            return "03";
        } else if (courseAttrName.equals("体育课")) {
            return "04";
        }
        return "";
    }
}
