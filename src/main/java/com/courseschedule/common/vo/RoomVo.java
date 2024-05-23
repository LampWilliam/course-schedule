package com.courseschedule.common.vo;

import lombok.Data;

@Data
public class RoomVo extends BaseVo {

    /**
     * 教室id
     */
    private Long id;

    /**
     * 教室编号
     */
    private String roomNo;

    /**
     * 教室名称
     */
    private String roomName;

    /**
     * 所在教学区域(教学楼、体育场等)编号
     */
    private String areaNo;

    /**
     * 教学区域名
     */
    private String areaName;

    /**
     * 教室人数容量
     */
    private Integer capacity;

    /**
     * 教室属性 01理论 02实验 03实践 04体育课
     */
    private String attr;

    /**
     * 备注
     */
    private String remark;
}
