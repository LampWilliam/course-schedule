package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.TimetableDto;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.entity.Timetable;
import com.courseschedule.entity.TimetableRehearsal;
import com.courseschedule.mapper.TimetableMapper;
import com.courseschedule.mapper.TimetableRehearsalMapper;
import com.courseschedule.service.TimetableService;
import com.courseschedule.utils.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * [output]课程表 服务实现类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@Service
public class TimetableServiceImpl extends ServiceImpl<TimetableMapper, Timetable> implements TimetableService {

    @Autowired
    private TimetableMapper timetableMapper;

    @Autowired
    private TimetableRehearsalMapper timetableRehearsalMapper;

    /*****************************手动排课的冲突检查*************************************/
    @Override
    public Result getConflictingColumnNameList(Long semesterId, String columnName) {
        boolean columnNameError
                = !"class_no".equals(columnName)
                && !"teacher_no".equals(columnName)
                && !"room_no".equals(columnName);
        if (columnNameError) {
            return Result.error("传入字段名columnName有误");
        }
        List<String> list = timetableMapper.getConflictingColumnNameList(semesterId, columnName);
        String msg = "成功获取" + semesterId + "学期课表中存在冲突的" + columnName + "列表";
        return Result.success(msg, list);
    }

    @Override
    public Result getConflictingTimeslotIdList(Long semesterId, String columnName, String no) {
        boolean columnNameError
                = !"class_no".equals(columnName)
                && !"teacher_no".equals(columnName)
                && !"room_no".equals(columnName);
        if (columnNameError) {
            return Result.error("传入字段名columnName有误");
        }

        List<Long> list = timetableMapper.getConflictingTimeslotIdList(semesterId, columnName, no);
        String msg = "成功获取 " + semesterId + "学期课表中" + columnName + "=" + no + "中存在冲突的timetable.id";
        return Result.success(msg, list);
    }

    @Override
    public Result getConfilctList(Long id) {
        Timetable timetable = super.getById(id);
        if (timetable == null || timetable.getIsDeleted().equals(BaseVo.DELETED)) {
            Result.error("待查询课表字段不存在！");
        }

        List<TimetableDto> list = timetableMapper.getConfilctList(id);
        String msg = "成功获取与timetable.id=" + id +
                "存在冲突的其它timetable.*，并返回冲突类型";
        return Result.success(msg, list);
    }

    @Override
    public Result rehearsalChangeTimeslot(Long id) {
        Timetable timetable = super.getById(id);
        if (timetable == null || timetable.getIsDeleted().equals(BaseVo.DELETED)) {
            Result.error("待查询课表字段不存在！");
        }

        // 4.1 清空演练表
        timetableRehearsalMapper.deleteAll();
        // 4.2 准备排练数据
        timetableRehearsalMapper.insertRehearsalData(timetable.getSemesterId());
        // 4.3 JAVA那边写循环
        int timeslot1 = timetable.getTimeslot();
        List list = new ArrayList<Integer>();
        for(int timeslot2=0; timeslot2<= ClassUtil.MAX_WEEK_TIMESLOT; timeslot2++) {
            if (timeslot1 == timeslot2) {continue;}
            // 4.3.1 更新排练数据
            timetableRehearsalMapper.updateRehearsalData(id, timeslot2);
            // 4.3.2 进行排练 返回0条数据表示无冲突，其它情况(其实也就只会返回1条) 表示有冲突
            TimetableRehearsal conflictingSelf = timetableRehearsalMapper.rehearsalChangeTimeslot(id);

            if (conflictingSelf == null) {
                list.add(timeslot2);
            }
        }
        String msg = "成功获取(timetable.id=" + id + "的字段的)可变动目的时间";
        return Result.success(msg, list);
    }

    /***************************** others *************************************/
}
