package com.courseschedule.algorithm;


import com.courseschedule.common.lang.ConstantInfo;
import com.courseschedule.mapper.TaskMapper;
import com.courseschedule.utils.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专门把遗传算法中的部分抽出来写这里
 * 不然可读性太差了
 * @Description 相当于是ServiceImpl层
 */
@Slf4j
@Component
public class GeneticAlgorithm {

    @Autowired
    private TaskMapper taskMapper;

    /** <h1>遗传算法</h1>
     *
     * 遗传进化(每个班级中多条基因编码)
     *
     * @param individualMap 按班级分的基因编码
     * @Description 步骤： <br/>
     * 1、初始化种群 <br/>
     * 2、交叉，选择 <br/>
     * 3、变异 <br/>
     * 4、重复2,3步骤 <br/>
     * 5、直到达到终止条件
     */
    public Map<String, List<String>> geneticEvolution(Map<String, List<String>> individualMap) {
        List<String> resultGeneList;

        for (int i = 0; i < ConstantInfo.GENERATION; ++i) {
            hybridization(individualMap);// 软约束
            List<String> allIndividual = collectGene(individualMap); // Map<班级,gene>转List<gene>
            resultGeneList = geneMutation(allIndividual); // 对时间进行变异
            List<String> list = conflictResolution(resultGeneList); // 消除冲突(teacher - class - time)
            individualMap.clear();
            individualMap = transformIndividual(list);// Map<班级,gene>转List<gene>
        }

        return individualMap;
    }

    /**
     * 将初始基因编码(都分配好时间)划分以班级为单位的个体
     * @return 班级编号的集合，去重
     */
    public Map<String, List<String>> transformIndividual(List<String> resultGeneList) {
        Map<String, List<String>> individualMap = new HashMap<>();
        List<String> classNoList = taskMapper.selectClassNo();
        for (String classNo1 : classNoList) {
            List<String> geneList = new ArrayList<>();
            for (String gene : resultGeneList) {
                String classNo2 = ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene);
                if (classNo1.equals(classNo2)) {
                    geneList.add(gene);
                }
            }
            if (!geneList.isEmpty()) {
                individualMap.put(classNo1, geneList);
            }
        }
        return individualMap;
    }

    /**
     * 给每个班级交叉：一个班级看作一个种群
     * <code>这个方法目前来讲是木有用的 因为我fitness那个函数没写 不知道怎么改<code/>
     */
    private Map<String, List<String>> hybridization(Map<String, List<String>> individualMap) {
        for (Map.Entry<String, List<String>> entry : individualMap.entrySet()) {
            String classNo = entry.getKey();
            List<String> individualList = individualMap.get(classNo);
            List<String> oldIndividualList = new ArrayList<>(individualList);

            selectGene(individualList);

            // 计算并对比子父代的适应度值，高的留下进行下一代遗传，相当于进化
            if (ClassUtil.fitness(individualList) >= ClassUtil.fitness(oldIndividualList)) {
                individualMap.put(classNo, individualList);
            } else {
                individualMap.put(classNo, oldIndividualList);
            }
        }
        return individualMap;
    }

    /**个体中随机选择基因进行交叉(交换随机俩task编码的上课时间)
     * @param individualList 单个班级的所有task编码
     */
    private List<String> selectGene(List<String> individualList) {
        int individualListSize = individualList.size();
        while(true) {
            int firstIndex = ClassUtil.RANDOM.nextInt(individualListSize);
            int secondIndex = ClassUtil.RANDOM.nextInt(individualListSize);

            String firstGene = individualList.get(firstIndex);// task1编码
            String secondGene = individualList.get(secondIndex);// task2编码

            if (firstIndex != secondIndex) {
                String firstClassTime = ClassUtil.cutGene(ConstantInfo.TIMESLOT, firstGene);
                String secondClassTime = ClassUtil.cutGene(ConstantInfo.TIMESLOT, secondGene);
                // 对俩task的时间进行一个交换
                firstGene = firstGene.substring(0, ClassUtil.getTimeslotStartIndex()) + secondClassTime;
                secondGene = secondGene.substring(0, ClassUtil.getTimeslotStartIndex()) + firstClassTime;
                individualList.set(firstIndex, firstGene);
                individualList.set(secondIndex, secondGene);
                break;
            }
        }
        return individualList;
    }

    /**
     * 重新合拢交叉后的个体,即不分班级的基因编码，得到所有的编码
     */
    public List<String> collectGene(Map<String, List<String>> individualMap) {
        List<String> resultList = new ArrayList<>();
        for (List<String> individualList : individualMap.values()) {
            resultList.addAll(individualList);
        }
        return resultList;
    }


    /**
     * 基因变异(给mutationNumber个task改变随机时间)
     */
    private List<String> geneMutation(List<String> resultGeneList) {
        final double mutationRate = 0.005;
        int mutationNumber = (int) (resultGeneList.size() * mutationRate); // 需要变异的个数

        if (mutationNumber < 1) {
            mutationNumber = 1;
        }

        for (int i = 0; i < mutationNumber; i++) {
            int randomIndex = ClassUtil.RANDOM.nextInt(resultGeneList.size());
            String gene = resultGeneList.get(randomIndex);
            // if (ClassUtil.cutGene(ConstantInfo.IS_FIX, gene).equals(ConstantInfo.FIX_TIME_FLAG)) {
            //     log.debug("固定时间的不会发生变异！！{} {}", ClassUtil.cutGene(gene, ConstantInfo.COURSE_NO), ClassUtil.cutGene(gene, ConstantInfo.TIMESLOT));
            //     break;
            // } else {
            String newTimeslot = ClassUtil.randomTime();
            gene = gene.substring(0, ClassUtil.getTimeslotStartIndex()) + newTimeslot;
            resultGeneList.remove(randomIndex);
            resultGeneList.add(randomIndex, gene);
            // }
        }
        return resultGeneList;
    }

    /**
     * 冲突消除,同一个讲师同一时间上多门课。 <br/>
     * 解决：重新分配一个时间，直到所有的基因编码中不再存在上课时间冲突为止 <br/>
     * 因素：讲师-课程-时间-教室
     *
     * @param resultGeneList 所有个体集合 （大种群）
     */
    private List<String> conflictResolution(List<String> resultGeneList) {
        int conflictTimes = 0;
        exit:
        for (int i = 0; i < resultGeneList.size(); i++) {
            String gene1 = resultGeneList.get(i);
            String teacherNo1 = ClassUtil.cutGene(ConstantInfo.TEACHER_NO, gene1);
            String timeslot1 = ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene1);
            String classNo1 = ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene1);
            int startWeek1 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.START_WEEK, gene1));
            int endWeek1 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.END_WEEK, gene1));
            int biweekly1 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.BIWEEKLY, gene1));

            for (int j = i + 1; j < resultGeneList.size(); j++) {
                String gene2 = resultGeneList.get(j);
                String teacherNo2 = ClassUtil.cutGene(ConstantInfo.TEACHER_NO, gene2);
                String timeslot2 = ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene2);
                String classNo2 = ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene2);
                int startWeek2 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.START_WEEK, gene2));
                int endWeek2 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.END_WEEK, gene2));
                int biweekly2 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.BIWEEKLY, gene2));

                /* 冲突检测
                【时间冲突】
                 t1 = t2 && 区间[startweek1:endweek1]与[startweek2:endweek2]存在重合 && (biweekly1+biweekly!=3)不是一个单周一个双周的情况
                 【空间冲突】
                 人1 = 人2
                 */
                boolean intervalOverlap = startWeek1 <= endWeek2 && startWeek2 <= endWeek1;
                boolean timeOverlap = timeslot1.equals(timeslot2) && intervalOverlap && biweekly1+biweekly2!=3;
                if (timeOverlap && classNo1.equals(classNo2)) {
                    log.error("一个班级在同一时间上上多门课 {}", conflictTimes++);

                    String newTimeslot = ClassUtil.randomTimeForClassConflict
                            (gene1, resultGeneList, classNo1, teacherNo1, timeslot1);

                    replaceConflictTime(resultGeneList, gene2, newTimeslot);

                    continue exit;
                } else if (timeOverlap && teacherNo1.equals(teacherNo2)) {
                    log.error("同一个老师在同一时间上上多门课 {}", conflictTimes++);
                    String newTimeslot = ClassUtil.randomTimeForTeacherConflict
                            (gene1, resultGeneList, teacherNo1, classNo1);

                    replaceConflictTime(resultGeneList, gene2, newTimeslot);
                    continue exit;
                }
            }
        }
        log.error("冲突发生次数: {}", conflictTimes);
        return resultGeneList;
    }


    private void replaceConflictTime(List<String> resultGeneList, String gene, String newTimeslot) {
        String newGene = gene.substring(0, ClassUtil.getTimeslotStartIndex()) + newTimeslot;

        // 替换新的随机时间给剩余大种群里面的编码
        replace(resultGeneList, gene, newGene);
    }


    /**
     * 替换基因编码
     */
    private void replace(List<String> resultGeneList, String oldGene, String newGene) {
        for (int i = 0; i < resultGeneList.size(); i++) {
            if (resultGeneList.get(i).equals(oldGene)) {
                resultGeneList.set(i, newGene);
                log.info("替换冲突时间");
                break;
            }
        }
    }

}
