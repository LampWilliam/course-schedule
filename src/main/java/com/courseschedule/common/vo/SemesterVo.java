package com.courseschedule.common.vo;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SemesterVo extends BaseVo {
    private Long id;

    /**
     * 学期名
     */
    @NotBlank(message = "学期名不能为空")
    private String semesterName;

    /**
     * 学期总周数
     */
    @NotNull(message = "学期总周数不能为空")
    @DecimalMin(value = "1", message = "总周数最小为1")
    private Integer semesterWeeksSum;

    /**
     * 学期开始日（必须为星期一）
     */
    @NotNull(message = "学期开始日不能为空")
    private LocalDate semesterStartDate;

    /**
     * 学期结束日
     */
    private LocalDate semesterEndDate;

    /**
     * 是否已排课 0未排课 1已排课
     */
    private Integer semesterStatus;
}
