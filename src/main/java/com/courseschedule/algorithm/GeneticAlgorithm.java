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
            hybridization(individualMap);
            List<String> allIndividual = collectGene(individualMap);
            resultGeneList = geneMutation(allIndividual);
            List<String> list = conflictResolution(resultGeneList);
            individualMap.clear();
            individualMap = transformIndividual(list);
        }

        return individualMap;
    }

    /**
     * <h1>将初始基因编码(都分配好时间)划分以班级为单位的个体<h1/>
     * 班级编号的集合，去重
     */
    public Map<String, List<String>> transformIndividual(List<String> resultGeneList) {
        Map<String, List<String>> individualMap = new HashMap<>();
        List<String> classNoList = taskMapper.selectClassNo();

        for (String classNo : classNoList) {
            List<String> geneList = new ArrayList<>();
            for (String gene : resultGeneList) {
                if (classNo.equals(ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene))) {
                    geneList.add(gene);
                }
            }
            if (!geneList.isEmpty()) {
                individualMap.put(classNo, geneList);
            }
        }
        return individualMap;
    }

    /**
     * 给每个班级交叉：一个班级看作一个种群
     */
    private Map<String, List<String>> hybridization(Map<String, List<String>> individualMap) {
        for (Map.Entry<String, List<String>> entry : individualMap.entrySet()) {
            String classNo = entry.getKey();
            List<String> individualList = individualMap.get(classNo);
            List<String> oldIndividualList = new ArrayList<>(individualList);

            selectGene(individualList);

            // 计算并对比子父代的适应度值，高的留下进行下一代遗传，相当于进化，
            if (ClassUtil.calculateExpectedValue(individualList) >= ClassUtil.calculateExpectedValue(oldIndividualList)) {
                individualMap.put(classNo, individualList);
            } else {
                individualMap.put(classNo, oldIndividualList);
            }
        }
        return individualMap;
    }

    /**
     * 个体中随机选择基因进行交叉(交换上课时间)
     */
    private List<String> selectGene(List<String> individualList) {
        int individualListSize = individualList.size();
        boolean flag;
        do {
            int firstIndex = ClassUtil.RANDOM.nextInt(individualListSize);
            int secondIndex = ClassUtil.RANDOM.nextInt(individualListSize);

            String firstGene = individualList.get(firstIndex);
            String secondGene = individualList.get(secondIndex);

            if (firstIndex == secondIndex) {
                flag = false;
            } else if (ClassUtil.cutGene(ConstantInfo.IS_FIX, firstGene).equals(ConstantInfo.FIX_TIME_FLAG)
                    || ClassUtil.cutGene(ConstantInfo.IS_FIX, secondGene).equals(ConstantInfo.FIX_TIME_FLAG)) {
                flag = false;
            } else {
                String firstClassTime = ClassUtil.cutGene(ConstantInfo.CLASS_TIME, firstGene);
                String secondClassTime = ClassUtil.cutGene(ConstantInfo.CLASS_TIME, secondGene);
                firstGene = firstGene.substring(0, 24) + secondClassTime;
                secondGene = secondGene.substring(0, 24) + firstClassTime;
                individualList.set(firstIndex, firstGene);
                individualList.set(secondIndex, secondGene);
                flag = true;
            }
        } while (!flag);
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
     * 基因变异
     */
    private List<String> geneMutation(List<String> resultGeneList) {
        final double mutationRate = 0.005;
        int mutationNumber = (int) (resultGeneList.size() * mutationRate);

        if (mutationNumber < 1) {
            mutationNumber = 1;
        }

        for (int i = 0; i < mutationNumber; i++) {
            int randomIndex = ClassUtil.RANDOM.nextInt(resultGeneList.size());
            String gene = resultGeneList.get(randomIndex);
            if (ClassUtil.cutGene(ConstantInfo.IS_FIX, gene).equals(ConstantInfo.FIX_TIME_FLAG)) {
                log.debug("固定时间的不会发生变异！！{} {}", ClassUtil.cutGene(gene, ConstantInfo.COURSE_NO), ClassUtil.cutGene(gene, ConstantInfo.CLASS_TIME));
                break;
            } else {
                String newClassTime = ClassUtil.randomTime();
                gene = gene.substring(0, 24) + newClassTime;
                resultGeneList.remove(randomIndex);
                resultGeneList.add(randomIndex, gene);
            }
        }
        return resultGeneList;
    }
    /**
     * 冲突消除,同一个讲师同一时间上多门课。解决：重新分配一个时间，直到所有的基因编码中
     * 不再存在上课时间冲突为止
     * 因素：讲师-课程-时间-教室
     *
     * @param resultGeneList 所有个体集合 （大种群）
     */
    private List<String> conflictResolution(List<String> resultGeneList) {
        int conflictTimes = 0;
        exit:
        for (int i = 0; i < resultGeneList.size(); i++) {
            String gene = resultGeneList.get(i);
            String teacherNo = ClassUtil.cutGene(ConstantInfo.TEACHER_NO, gene);
            String classTime = ClassUtil.cutGene(ConstantInfo.CLASS_TIME, gene);
            String classNo = ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene);

            for (int j = i + 1; j < resultGeneList.size(); j++) {
                String tempGene = resultGeneList.get(j);
                String tempTeacherNo = ClassUtil.cutGene(ConstantInfo.TEACHER_NO, tempGene);
                String tempClassTime = ClassUtil.cutGene(ConstantInfo.CLASS_TIME, tempGene);
                String tempClassNo = ClassUtil.cutGene(ConstantInfo.CLASS_NO, tempGene);
                // 冲突检测
                if (classTime.equals(tempClassTime) && classNo.equals(tempClassNo)) {
                    log.error("一个班级在同一时间上上多门课 {}", conflictTimes++);

                    String newClassTime = ClassUtil.randomTimeForClassConflict(gene, resultGeneList, classNo, teacherNo, classTime);

                    replaceConflictTime(resultGeneList, tempGene, newClassTime);

                    continue exit;
                } else if (classTime.equals(tempClassTime) && teacherNo.equals(tempTeacherNo)) {
                    log.error("同一个老师在同一时间上上多门课 {}", conflictTimes++);
                    String newClassTime = ClassUtil.randomTimeForTeacherConflict(gene, resultGeneList, teacherNo, classNo);
                    replaceConflictTime(resultGeneList, tempGene, newClassTime);
                    continue exit;
                }
            }
        }
        log.error("冲突发生次数: {}", conflictTimes);
        return resultGeneList;
    }


    private void replaceConflictTime(List<String> resultGeneList, String gene, String newClassTime) {
        String newGene = gene.substring(0, 24) + newClassTime;

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
