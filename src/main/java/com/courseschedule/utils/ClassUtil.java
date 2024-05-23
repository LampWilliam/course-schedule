package com.courseschedule.utils;

import com.courseschedule.common.lang.ConstantInfo;
import lombok.extern.slf4j.Slf4j;

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
    public static String randomTime() {
        int temp = RANDOM.nextInt(MAX_WEEK_TIMESLOT) + 1;
        return temp < 10 ? ("0" + temp) : String.valueOf(temp);
    }


    /** 生成 00 - 24 的时间集合
     * @return -> java.util.List<java.lang.String>
     **/
    private static List<String> getAllTime() {
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
    public static String randomTimeForClassConflict(String gene, List<String> geneList, String classNo, String teacherNo, String classTime) {
        // 找出当前班级在 01-25 时间之间还未使用的时间

        Set<String> usedTimeList =
                geneList.stream()
                        .filter(item -> cutGene(ConstantInfo.CLASS_NO, item).equals(classNo))
                        .map(item -> cutGene(ConstantInfo.TIMESLOT, item))
                        .sorted()
                        .collect(Collectors.toSet());

        log.debug("{} 班级 剩余空闲上课时间 {}", classNo, usedTimeList);

        return getFreeTime(usedTimeList);
    }

    /**
     * 解决同一个teacher同时有两个timeslot在上课的冲突问题
     * @return 未使用的时间
     */
    public static String randomTimeForTeacherConflict(String gene, List<String> geneList, String teacherNo, String classNo) {
        // 找出当前教师在 01-25 时间之间还未使用的时间

        // usedTimeList 存储了该教师已经使用的所有时间段
        Set<String> usedTimeList =
                geneList.stream()
                        .filter(item -> cutGene(ConstantInfo.TEACHER_NO, item).equals(teacherNo))
                        .map(item -> cutGene(ConstantInfo.TIMESLOT, item))
                        .sorted()
                        .collect(Collectors.toSet());

        log.debug("{} 讲师 已经用的上课时间 {}", teacherNo, usedTimeList);

        return getFreeTime(usedTimeList);
    }

    /**
     * 获取 01-25 内还未使用的时间
     **/
    private static String getFreeTime(Set<String> usedTimeList) {
        List<String> allTime = getAllTime();

        boolean isRemoveSuccess = allTime.removeAll(usedTimeList);

        if (isRemoveSuccess && !allTime.isEmpty()) {
            int randomIndex = RANDOM.nextInt(allTime.size());
            return allTime.get(randomIndex);
        }

        return randomTime();
    }

    /**
     * 计算每个个体的适应值
     * 目的:进行软约束
     */
    public static double fitness(List<String> individualList) {
        double K1 = 0.3;// 权重1 - 不是早八
        double K2 = 0.7;// 权重2 - 不是晚课
        int F1 = 0;
        int F2 = 0;
        double Fx = 0;// 总适应度
        // 计算每一个个体的适应度
        for (String gene : individualList) {
            int timeslot = Integer.parseInt(cutGene(ConstantInfo.TIMESLOT, gene));
            boolean isEarlyMorning = timeslot%5 == 0;
            boolean isLateNight = timeslot%5 == 4;
            if (!isEarlyMorning) F1++;
            if (!isLateNight) F2++;
        }
        Fx = K1*F1 + K2*F2;
        return Fx;
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
