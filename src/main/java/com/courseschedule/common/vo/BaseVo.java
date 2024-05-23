package com.courseschedule.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 比较规范的BaseVo
 * 用于存分页信息
 *
 * 但是我估计用不到
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseVo implements Serializable {
    public static final Integer NOT_DELETED = 0;
    public static final Integer DELETED = 1;

    // 页数
    private Integer page;
    // 数量
    private Integer size;
    // 查询关键字
    private String key;

    // 偏移量
    private Integer offset;

    // 获取偏移量
    public Integer getOffset(){
        return (this.page - 1) * this.size;
    }

    public static Integer calculatePages(Integer size, Integer total) {
        return (total+size-1)/size;
    }
}
