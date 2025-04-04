package com.courseschedule.utils;

import com.courseschedule.algorithm.GeneticAlgorithm;
import com.courseschedule.common.lang.ConstantInfo;
import com.courseschedule.entity.ExclusionRule;
import com.courseschedule.mapper.ExclusionRuleMapper;
import com.courseschedule.service.ExclusionRuleService;
import com.courseschedule.service.impl.ExclusionRuleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * 工具类
 */
@Slf4j
public class ClassUtil {

    private ClassUtil() {

    }

    public static final Random RANDOM = new Random();

    /**
     * 课程表最大节数，当前是 24，用于生成随机时间的上限
     */
    public static int MAX_WEEK_TIMESLOT = 24;// 0~24,一周5天*每天5大节

    /** 用于切割获得编码的染色体中需要的属性
     *
     * @param aim 目标信息
     * @param source 源编码
     * @return 切割出来的目标信息
     */
    public static String cutGene(String aim, String source) {
        int startIndex = 0;
        int endIndex = 0;
        for (Map.Entry<String, Integer> entry : ConstantInfo.RULES.entrySet()) {
            endIndex += entry.getValue();
            if (entry.getKey().equals(aim)) {
                return source.substring(startIndex, endIndex);
            }
            startIndex += entry.getValue();
        }
        return "";
    }

    /**
     * 返回cutGene时Timeslot的startIndex
     * @return
     */
    public static int getTimeslotStartIndex() {
        int indexBeforeTimeslot = 0;
        for (Map.Entry<String, Integer> entry : ConstantInfo.RULES.entrySet()) {
            if (entry.getKey().equals(ConstantInfo.TIMESLOT)) {
                return indexBeforeTimeslot;
            }
            indexBeforeTimeslot += entry.getValue();
        }
        return indexBeforeTimeslot;
    }



    /** 成随机时间
     * @return -> 两位时间
     **/
    public static String randomTime(int ifScheduledInNight,String CourseAttr,int ifExpInNight) {
        int temp = RANDOM.nextInt(MAX_WEEK_TIMESLOT) ;
        if(ifScheduledInNight == 0) {                               // 所有课都不允许晚上排
            if(temp == 4 || temp == 9 || temp == 14 || temp == 19 || temp == 24) {
                return randomTime(ifScheduledInNight,CourseAttr,ifExpInNight);
            }
        }
        if(ifScheduledInNight == 1 && ifExpInNight == 1) {          // 所有课程都允许晚上排
            return temp < 10 ? ("0" + temp) : String.valueOf(temp);
        }else if(ifScheduledInNight == 1 && ifExpInNight == 0) {    // 除了实验课都允许晚上排
            if(CourseAttr.equals("02")){
                if(temp == 4 || temp == 9 || temp == 14 || temp == 19 || temp == 24) {
                    return randomTime(ifScheduledInNight,CourseAttr,ifExpInNight);
                }
            }
            return temp < 10 ? ("0" + temp) : String.valueOf(temp);
        }
        return temp < 10 ? ("0" + temp) : String.valueOf(temp);
    }


    /** 生成 00 - 24 的时间集合
     * @return -> java.util.List<java.lang.String>
     **/
    public static List<String> getAllTime(String courseAttr,int ifScheduledInNight,int ifExpInNight) {
        if(ifScheduledInNight == 0) {
            return IntStream.rangeClosed(0, MAX_WEEK_TIMESLOT)
                    .filter(i -> i % 5 != 4) // 排除 i = 4, 9, 14, 19, 24
                    .mapToObj(i -> i < 10 ? ("0" + i) : String.valueOf(i))
                    .collect(Collectors.toList());
        }
        if(ifScheduledInNight == 1 && ifExpInNight == 1) {
            return IntStream.rangeClosed(0, MAX_WEEK_TIMESLOT).mapToObj(i -> i < 10 ? ("0" + i) : String.valueOf(i)).collect(Collectors.toList());
        } else if (ifScheduledInNight == 1 && ifExpInNight == 0) {
            if(courseAttr.equals("02")){
                return IntStream.rangeClosed(0, MAX_WEEK_TIMESLOT)
                        .filter(i -> i % 5 != 4) // 排除 i = 4, 9, 14, 19, 24
                        .mapToObj(i -> i < 10 ? ("0" + i) : String.valueOf(i))
                        .collect(Collectors.toList());
            }
            return IntStream.rangeClosed(0, MAX_WEEK_TIMESLOT).mapToObj(i -> i < 10 ? ("0" + i) : String.valueOf(i)).collect(Collectors.toList());
        }
        return IntStream.rangeClosed(0, MAX_WEEK_TIMESLOT).mapToObj(i -> i < 10 ? ("0" + i) : String.valueOf(i)).collect(Collectors.toList());
    }

    /** 解决同一个class同时有两个timeslot在上课的冲突问题
     * @param gene 当前发生冲突的基因编码
     * @param geneList 种群
     * @param classNo 班级
     * @param teacherNo 讲师编号
     * @param classTime 上课时间
     * @return 未使用的时间
     **/
    public static String randomTimeForClassConflict(String gene, List<String> geneList, String classNo, String teacherNo, String classTime,String courseAttr,int ifScheduledInNight,int ifExpInNight) {
        // 找出当前班级在 01-25 时间之间还未使用的时间

        Set<String> usedTimeList =
                geneList.stream()
                        .filter(item -> cutGene(ConstantInfo.CLASS_NO, item).equals(classNo))
                        .map(item -> cutGene(ConstantInfo.TIMESLOT, item))
                        .sorted()
                        .collect(Collectors.toSet());

        log.debug("{} 班级 剩余空闲上课时间 {}", classNo, usedTimeList);

        return getFreeTime(courseAttr,usedTimeList,ifScheduledInNight,ifExpInNight);
    }

    /**
     * 解决同一个teacher同时有两个timeslot在上课的冲突问题
     * @return 未使用的时间
     */
    public static String randomTimeForTeacherConflict(String gene, List<String> geneList, String teacherNo, String classNo,String courseAttr,int ifScheduledInNight,int ifExpInNight) {
        // 找出当前教师在 01-25 时间之间还未使用的时间

        // usedTimeList 存储了该教师已经使用的所有时间段
        Set<String> usedTimeList =
                geneList.stream()
                        .filter(item -> cutGene(ConstantInfo.TEACHER_NO, item).equals(teacherNo))
                        .map(item -> cutGene(ConstantInfo.TIMESLOT, item))
                        .sorted()
                        .collect(Collectors.toSet());

        log.debug("{} 讲师 已经用的上课时间 {}", teacherNo, usedTimeList);

        return getFreeTime(courseAttr,usedTimeList,ifScheduledInNight,ifExpInNight);
    }

    /**
     * 获取 01-25 内还未使用的时间
     **/
    private static String getFreeTime(String courseAttr,Set<String> usedTimeList,int ifScheduledInNight,int ifExpInNight) {
        List<String> allTime = getAllTime(courseAttr,ifScheduledInNight,ifExpInNight);

        boolean isRemoveSuccess = allTime.removeAll(usedTimeList);

        if (isRemoveSuccess && !allTime.isEmpty()) {
            int randomIndex = RANDOM.nextInt(allTime.size());
            return allTime.get(randomIndex);
        }

        return randomTime(1,"01",1);
    }

    /**
     * 将一个个体（班级课表）的同一门课程的所有上课时间进行统计，并且进行分组
     * 每个班级的课表都算是一个个体
     */
    private static Map<String, List<String>> courseGrouping(List<String> individualList) {
        Map<String, List<String>> classTimeMap = new HashMap<>();
        // 先将一个班级课表所上的课程区分出来（排除掉重复的课程）
        for (String gene : individualList) {
            classTimeMap.put(cutGene(ConstantInfo.COURSE_NO, gene), null);
        }
        // 遍历课程编号
        for (String courseNo : classTimeMap.keySet()) {
            List<String> classTimeList = new ArrayList<>();
            for (String gene : individualList) {
                // 获得同一门课程的所有上课时间片
                if (cutGene(ConstantInfo.COURSE_NO, gene).equals(courseNo)) {
                    classTimeList.add(cutGene(ConstantInfo.TIMESLOT, gene));
                }
            }
            // 将课程的时间片进行排序
            Collections.sort(classTimeList);
            // 每一门课对应的上课时间集合(classNo, List)
            classTimeMap.put(courseNo, classTimeList);
        }
        return classTimeMap;
    }

}
