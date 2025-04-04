package com.courseschedule.algorithm;


import com.courseschedule.common.lang.ConstantInfo;
import com.courseschedule.entity.ExclusionRule;
import com.courseschedule.entity.PriorityRule;
import com.courseschedule.mapper.*;
import com.courseschedule.utils.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.courseschedule.utils.ClassUtil.getAllTime;

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

    @Autowired
    private ExclusionRuleMapper exclusionRuleMapper;

    @Autowired
    private PriorityRuleMapper priorityRuleMapper;

    @Autowired
    private TeacherRuleMapper teacherRuleMapper;

    @Autowired
    private ClassesMapper classesMapper;

    public List<ExclusionRule> getExclusionRules(String Attr) {
        switch (Attr) {
            case "Class":
                return exclusionRuleMapper.getClassList();
            case "Course":
                return exclusionRuleMapper.getCourseList();
            case "Teacher":
                return exclusionRuleMapper.getTeacherList();
            case "Room":
                return exclusionRuleMapper.getRoomList();
        }
        return null;
    }

    public List<PriorityRule> getPriorityRules(String Attr) {
        switch (Attr) {
            case "CourseDepartment":
                return priorityRuleMapper.getCourseDepartmentList();
            case "TaskAttr":
                return priorityRuleMapper.getTaskAttrList();
            case "CourseAttr":
                return priorityRuleMapper.getCourseAttrList();
        }
        return null;
    }


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
     * 将初始基因编码(都分配好时间)划分以教师为单位的个体
     * @return 班级编号的集合，去重
     */
    public Map<String, List<Integer>> transformIndividualByTeacherNo(List<String> resultGeneList) {
        Map<String, List<Integer>> individualMaps = new HashMap<>();
        List<String> teacherNoList = taskMapper.selectTeacherNo();
        for (String teacherNo1 : teacherNoList) {
            List<Integer> TimeslotNumList = new ArrayList<>();
            for(int i=0;i<15;i++){
                TimeslotNumList.add(0);
            }
            for (String gene : resultGeneList) {
                String teacherNo2 = ClassUtil.cutGene(ConstantInfo.TEACHER_NO, gene);
                if (teacherNo1.equals(teacherNo2)) {
                    int timeslot = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.TIMESLOT,gene));
                    String duration = ClassUtil.cutGene(ConstantInfo.DURATION,gene);
                    List<Integer> range;
                    if(duration.equals("4")){
                        int afterAddTimeslot = timeslot + 1;
                        List<Integer> range1 = judgeTimeslot(timeslot);
                        List<Integer> range2 = judgeTimeslot(afterAddTimeslot);
                        range1.addAll(range2);
                        Set<Integer> set = new HashSet<>(range1);
                        range = new ArrayList<>(set);
                    }else{
                        range = judgeTimeslot(timeslot);
                    }
                    if(!range.isEmpty()){
                        for (int integer : range) {
                            int timeslotNum = TimeslotNumList.get(integer) + 1;
                            TimeslotNumList.set(integer, timeslotNum);
                        }
                    }
                }
            }
            if (!TimeslotNumList.isEmpty()) {
                individualMaps.put(teacherNo1, TimeslotNumList);
            }
        }
        return individualMaps;
    }

    /**
     * 判断timeslot属于周几和周几早上/下午
     */
    public List<Integer> judgeTimeslot(int timeslot){
        List<Integer> range = new ArrayList<>();
        if(timeslot == 0 || timeslot == 1){ // 周一上午
            range.add(0);
            range.add(2);
        } else if(timeslot == 2 || timeslot == 3){ // 周一下午
            range.add(1);
            range.add(2);
        } else if (timeslot == 5 || timeslot == 6) { // 周二上午
            range.add(3);
            range.add(5);
        } else if (timeslot == 7 || timeslot == 8) { // 周二下午
            range.add(4);
            range.add(5);
        } else if (timeslot == 10 || timeslot == 11) { // 周三上午
            range.add(6);
            range.add(8);
        } else if (timeslot == 12 || timeslot == 13) { // 周三下午
            range.add(7);
            range.add(8);
        } else if (timeslot == 15 || timeslot == 16) { // 周四上午
            range.add(9);
            range.add(11);
        } else if (timeslot == 17 || timeslot == 18) { // 周四下午
            range.add(10);
            range.add(11);
        } else if (timeslot == 20 || timeslot == 21) { // 周五上午
            range.add(12);
            range.add(14);
        } else if (timeslot == 22 || timeslot == 23) { // 周五下午
            range.add(13);
            range.add(14);
        } else if (timeslot == 4) {
            range.add(2);
        } else if (timeslot == 9) {
            range.add(5);
        } else if (timeslot == 14) {
            range.add(8);
        } else if (timeslot == 19) {
            range.add(11);
        } else if (timeslot == 24) {
            range.add(14);
        }
        return range;
    }

    public Map<String, List<String>> geneticEvolution(Map<String, List<String>> individualMap,Map<String,List<Integer>> teacherTimeslotNum,int ifScheduledInNight,int ifExpInNight) {
        List<String> resultGeneList;

        for (int i = 0; i < ConstantInfo.GENERATION; ++i) {
            hybridization(individualMap,teacherTimeslotNum);// 软约束、交叉选择
            List<String> allIndividual = collectGene(individualMap); // Map<班级,gene>转List<gene>
            resultGeneList = geneMutation(allIndividual,ifScheduledInNight,ifExpInNight); // 对时间进行变异
            teacherTimeslotNum.clear();
            teacherTimeslotNum = transformIndividualByTeacherNo(allIndividual);
            List<String> list = conflictResolution(resultGeneList,teacherTimeslotNum,ifScheduledInNight,ifExpInNight); // 消除冲突(teacher - class - time)
            individualMap.clear();
            individualMap = transformIndividual(list);// List<gene>转Map<班级,gene>
        }

        return individualMap;
    }


    /**
     * 给每个班级交叉：一个班级看作一个种群
     * <code>这个方法目前来讲是木有用的 因为我fitness那个函数没写 不知道怎么改<code/>
     */
    private Map<String, List<String>> hybridization(Map<String, List<String>> individualMap,Map<String,List<Integer>> teacherTimeslotNum) {
        for (Map.Entry<String, List<String>> entry : individualMap.entrySet()) {
            String classNo = entry.getKey();  // 班级列表
            List<String> individualList = individualMap.get(classNo);   // 班级种群
            List<String> oldIndividualList = new ArrayList<>(individualList); // 旧班级种群

            selectGene(individualList);  //只选择了两个DNA交换基因

            // 计算并对比子父代的适应度值，高的留下进行下一代遗传，相当于进化
            if (fitness(individualList,teacherTimeslotNum) >= fitness(oldIndividualList,teacherTimeslotNum)) {
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
    private List<String> geneMutation(List<String> resultGeneList,int ifScheduledInNight,int ifExpInNight) {
        final double mutationRate = 0.005;
        int mutationNumber = (int) (resultGeneList.size() * mutationRate); // 需要变异的个数

        if (mutationNumber < 1) {
            mutationNumber = 1;
        }

        for (int i = 0; i < mutationNumber; i++) {
            Random rand = new Random();
            int randomIndex = ClassUtil.RANDOM.nextInt(resultGeneList.size());
            String gene = resultGeneList.get(randomIndex);
            String courseNo = ClassUtil.cutGene((ConstantInfo.COURSE_NO), gene);
            String classNo = ClassUtil.cutGene((ConstantInfo.CLASS_NO), gene);
            String teacherNo = ClassUtil.cutGene((ConstantInfo.TEACHER_NO), gene);
            // if (ClassUtil.cutGene(ConstantInfo.IS_FIX, gene).equals(ConstantInfo.FIX_TIME_FLAG)) {
            //     log.debug("固定时间的不会发生变异！！{} {}", ClassUtil.cutGene(gene, ConstantInfo.COURSE_NO), ClassUtil.cutGene(gene, ConstantInfo.TIMESLOT));
            //     break;
            // } else {
//            String newTimeslots = ClassUtil.randomTime();
            String duration = ClassUtil.cutGene(ConstantInfo.DURATION, gene);
            String courseAttr = ClassUtil.cutGene((ConstantInfo.COURSE_ATTR), gene);
            String ifFixRoom = ClassUtil.cutGene((ConstantInfo.IFFIXROOM), gene);
            List<String> validSlots = getValidSlots(duration,courseNo,classNo,teacherNo,courseAttr,ifScheduledInNight,ifFixRoom,ifExpInNight);  // 根据连排节次获取有效的上课时间列表
            String newTimeslot = validSlots.get(rand.nextInt(validSlots.size())); // 随机获取一个有效上课时间
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
    private List<String> conflictResolution(List<String> resultGeneList,Map<String,List<Integer>> teacherTimeslotNum,int ifScheduledInNight,int ifExpInNight) {
        int conflictTimes = 0;
        exit:
        for (int i = 0; i < resultGeneList.size(); i++) {
            String gene1 = resultGeneList.get(i);
            String courseNo1 = ClassUtil.cutGene((ConstantInfo.COURSE_NO), gene1);
            String teacherNo1 = ClassUtil.cutGene(ConstantInfo.TEACHER_NO, gene1);
            String timeslot1 = ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene1);
            int timeSlot1 = Integer.parseInt(timeslot1);
            String classNo1 = ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene1);
            String duration1 = ClassUtil.cutGene(ConstantInfo.DURATION, gene1);
            String ifFixRoom1 = ClassUtil.cutGene(ConstantInfo.IFFIXROOM, gene1);
            String courseAttr1 = ClassUtil.cutGene(ConstantInfo.COURSE_ATTR, gene1);
            int startWeek1 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.START_WEEK, gene1));
            int endWeek1 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.END_WEEK, gene1));
            int biweekly1 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.BIWEEKLY, gene1));
            for (int j = i + 1; j < resultGeneList.size(); j++) {
                String gene2 = resultGeneList.get(j);
                String courseNo2 = ClassUtil.cutGene(ConstantInfo.COURSE_NO, gene2);
                String teacherNo2 = ClassUtil.cutGene(ConstantInfo.TEACHER_NO, gene2);
                String timeslot2 = ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene2);
                int timeSlot2 = Integer.parseInt(timeslot2);
                String classNo2 = ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene2);
                String duration2 = ClassUtil.cutGene(ConstantInfo.DURATION, gene2);
                String ifFixRoom2 = ClassUtil.cutGene(ConstantInfo.IFFIXROOM, gene2);
                String courseAttr2 = ClassUtil.cutGene(ConstantInfo.COURSE_ATTR, gene2);
                int startWeek2 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.START_WEEK, gene2));
                int endWeek2 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.END_WEEK, gene2));
                int biweekly2 = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.BIWEEKLY, gene2));

                /* 冲突检测
                【时间冲突】
                 t1 = t2 && 区间[startweek1:endweek1]与[startweek2:endweek2]存在重合 && (biweekly1+biweekly!=3)不是一个单周一个双周的情况
                 【空间冲突】
                 人1 = 人2
                 */
                if(ifExceed(gene1,teacherTimeslotNum)){
                    String newTimeslot = ClassUtil.randomTime(ifScheduledInNight,courseAttr1,ifExpInNight);
                    replaceConflictTime(resultGeneList, gene1, newTimeslot);
                    continue exit;
                }
                if(ifExceed(gene2,teacherTimeslotNum)){
                    String newTimeslot = ClassUtil.randomTime(ifScheduledInNight,courseAttr2,ifExpInNight);
                    replaceConflictTime(resultGeneList, gene2, newTimeslot);
                    continue exit;
                }
                if (!isValidStartSlot(gene1,ifScheduledInNight,ifExpInNight)) {
                    Random rand = new Random();
                    log.error("{} 基因一不是合法的连排初始节次或位于禁排节次中 {}", timeSlot1,conflictTimes++);
                    List<String> validStartSlot = getValidSlots(duration1,courseNo1,classNo1,teacherNo1,courseAttr1,ifScheduledInNight,ifFixRoom1,ifExpInNight);
                    String newTimeslot = validStartSlot.get(rand.nextInt(validStartSlot.size()));

                    replaceConflictTime(resultGeneList, gene1, newTimeslot);
                    continue exit;
                }
                if (!isValidStartSlot(gene2,ifScheduledInNight,ifExpInNight)) {
                    Random rand = new Random();
                    log.error("{} 基因二不是合法的连排初始节次或位于禁排节次 {}", timeSlot2,conflictTimes++);
                    List<String> validStartSlot = getValidSlots(duration2,courseNo2,classNo2,teacherNo2,courseAttr1,ifScheduledInNight,ifFixRoom2,ifExpInNight);
                    String newTimeslot = validStartSlot.get(rand.nextInt(validStartSlot.size()));
                    replaceConflictTime(resultGeneList, gene2, newTimeslot);
                    continue exit;
                }
                boolean durationConflict = false;
                if(duration1.equals("4") && duration2.equals("4")) {
                    if(timeSlot1 + 1 == timeSlot2 || timeSlot2 + 1 == timeSlot1) {
                        durationConflict = true;
                    }
                } else if (duration1.equals("2") && duration2.equals("4")) {
                    if(timeSlot2 + 1 == timeSlot1){
                        durationConflict = true;
                    }
                } else if (duration1.equals("4") && duration2.equals("2")) {
                    if (timeSlot1 + 1 == timeSlot2){
                        durationConflict = true;
                    }
                } else if (duration1.equals("2") && duration2.equals("2")) {
                    if (timeSlot2 == timeSlot1){
                        durationConflict = true;
                    }
                }
                boolean intervalOverlap = startWeek1 <= endWeek2 && startWeek2 <= endWeek1;
                boolean timeOverlap = intervalOverlap && biweekly1+biweekly2!=3 && durationConflict;
//                boolean timeOverlap = intervalOverlap && biweekly1+biweekly2!=3 && timeslot1.equals(timeslot2);
                if (timeOverlap && classNo1.equals(classNo2)) {
                    log.error("一个班级在同一时间上上多门课 {}", conflictTimes++);

                    String newTimeslot = ClassUtil.randomTimeForClassConflict
                            (gene1, resultGeneList, classNo1, teacherNo1, timeslot1,courseAttr1,ifScheduledInNight,ifExpInNight);

                    replaceConflictTime(resultGeneList, gene2, newTimeslot);

                    continue exit;
                } else if (timeOverlap && teacherNo1.equals(teacherNo2)) {
                    log.error("同一个老师在同一时间上上多门课 {}", conflictTimes++);
                    String newTimeslot = ClassUtil.randomTimeForTeacherConflict
                            (gene1, resultGeneList, teacherNo1, classNo1,courseAttr1,ifScheduledInNight,ifExpInNight);

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
        // 更新教师每天与每天上/下午上课节数
        transformIndividualByTeacherNo(resultGeneList);
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

    // 根据连排长度和禁排规则返回合法起始节次
    private List<String> getValidSlots(String duration,String courseNo,String classNo,String teacherNo,String courseAttr,int ifScheduledInNight,String ifFixRoom,int ifExpInNight) {
        List<ExclusionRule> ExCourseList = getExclusionRules("Course");
        List<ExclusionRule> ExClassList = getExclusionRules("Class");
        List<ExclusionRule> ExTeacherList = getExclusionRules("Teacher");
        List<ExclusionRule> ExRoomList = getExclusionRules("Room");
        List<String> validSlots = new ArrayList<>();
        List<String> forbiddenSlots = new ArrayList<>();
        if (duration.equals("2")) {
            if(ifScheduledInNight == 0){
                for(int i=0;i<25;i++){
                    if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24){
                        continue;
                    }
                    String si = i < 10 ? ("0" + i) : String.valueOf(i);
                    validSlots.add(si);
                }
            }
            if(ifScheduledInNight == 1 && ifExpInNight == 1){
                for(int i=0;i<25;i++){
                    String si = i < 10 ? ("0" + i) : String.valueOf(i);
                    validSlots.add(si);
                }
            } else if (ifScheduledInNight == 1 && ifExpInNight == 0) {
                if(courseAttr.equals("02")){
                    for(int i=0;i<25;i++){
                        if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24){
                            continue;
                        }
                        String si = i < 10 ? ("0" + i) : String.valueOf(i);
                        validSlots.add(si);
                    }
                }else{
                    for(int i=0;i<25;i++){
                        String si = i < 10 ? ("0" + i) : String.valueOf(i);
                        validSlots.add(si);
                    }
                }
            }

//            return Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
//            validSlots = Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
        } else if (duration.equals("4")) {
            if(ifScheduledInNight == 1){
                for(int i=0;i<25;i++){
                    if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24){
                        continue;
                    }
                    String si = i < 10 ? ("0" + i) : String.valueOf(i);
                    validSlots.add(si);
                }
            }else if(ifScheduledInNight == 0){
                for(int i=0;i<25;i++){
                    if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24 ||
                       i == 3 || i == 8 || i == 13 || i == 18 || i == 23){
                        continue;
                    }
                    String si = i < 10 ? ("0" + i) : String.valueOf(i);
                    validSlots.add(si);
                }
            }

//            return Arrays.asList("00", "01", "02", "03", "05", "06", "07", "08", "10", "11", "12", "13", "15", "16", "17", "18", "20", "21", "22", "23");
//            validSlots = Arrays.asList("00", "01", "02", "03", "05", "06", "07", "08", "10", "11", "12", "13", "15", "16", "17", "18", "20", "21", "22", "23");
        }
        if(ifFixRoom.equals("1")){
            String roomNo = classesMapper.selectFixRoomNo(classNo);
            for(ExclusionRule exclusionRule : ExRoomList) {
                if(exclusionRule.getRoomNo().equals(roomNo)) {
//                validSlots.remove(exclusionRule.getTimeslot());
                    int exTimeslot = exclusionRule.getTimeslot();
                    String exTimeslots = exTimeslot < 10 ? ("0" + exTimeslot) : String.valueOf(exTimeslot);
                    forbiddenSlots.add(exTimeslots);
                }
            }
        }
        for(ExclusionRule exclusionRule : ExCourseList) {
            if(exclusionRule.getCourseNo().equals(courseNo)) {
//                validSlots.remove(exclusionRule.getTimeslot());
                int exTimeslot = exclusionRule.getTimeslot();
                String exTimeslots = exTimeslot < 10 ? ("0" + exTimeslot) : String.valueOf(exTimeslot);
                forbiddenSlots.add(exTimeslots);
            }
        }
        for(ExclusionRule exclusionRule : ExClassList) {
            if(exclusionRule.getClassNo().equals(classNo)) {
//                validSlots.remove(exclusionRule.getTimeslot());
                int exTimeslot = exclusionRule.getTimeslot();
                String exTimeslots = exTimeslot < 10 ? ("0" + exTimeslot) : String.valueOf(exTimeslot);
                forbiddenSlots.add(exTimeslots);
            }
        }
        for(ExclusionRule exclusionRule : ExTeacherList) {
            if(exclusionRule.getTeacherNo().equals(teacherNo)) {
//                validSlots.remove(exclusionRule.getTimeslot());
                int exTimeslot = exclusionRule.getTimeslot();
                String exTimeslots = exTimeslot < 10 ? ("0" + exTimeslot) : String.valueOf(exTimeslot);
                forbiddenSlots.add(exTimeslots);

            }
        }
        Set<String> ForbiddenSlots = new HashSet<>(forbiddenSlots);
        validSlots.removeAll(ForbiddenSlots);
        return validSlots;
    }

    private boolean isValidStartSlot(String gene,int ifScheduledInNight,int ifExpInNight) {
        int timeslot = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene));
        String duration = ClassUtil.cutGene(ConstantInfo.DURATION, gene);
        List<ExclusionRule> ExCourseList = getExclusionRules("Course");
        List<ExclusionRule> ExClassList = getExclusionRules("Class");
        List<ExclusionRule> ExTeacherList = getExclusionRules("Teacher");
        List<ExclusionRule> ExRoomList = getExclusionRules("Room");
        String courseNo = ClassUtil.cutGene(ConstantInfo.COURSE_NO, gene);
        String classNo = ClassUtil.cutGene(ConstantInfo.CLASS_NO, gene);
        String teacherNo = ClassUtil.cutGene(ConstantInfo.TEACHER_NO, gene);
        String ifFixRoom = ClassUtil.cutGene(ConstantInfo.IFFIXROOM,gene);
        String courseAttr = ClassUtil.cutGene(ConstantInfo.COURSE_ATTR, gene);
        List<Integer> validStartSlot = new ArrayList<>();
        List<Integer> forbiddenSlots = new ArrayList<>();
        if (duration.equals("2")) {
            if(ifScheduledInNight == 0){
                for(int i=0;i<25;i++){
                    if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24){
                        continue;
                    }
                    validStartSlot.add(i);
                }

            }
            if(ifScheduledInNight == 1 && ifExpInNight == 1){
                for(int i=0;i<25;i++){
                    validStartSlot.add(i);
                }
            } else if (ifScheduledInNight == 1 && ifExpInNight == 0) {
                if(courseAttr.equals("02")){
                    for(int i=0;i<25;i++){
                        if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24){
                            continue;
                        }
                        validStartSlot.add(i);
                    }
                }else{
                    for(int i=0;i<25;i++){
                        validStartSlot.add(i);
                    }
                }

            }

//            return Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
//            validSlots = Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
        } else if (duration.equals("4")) {
            if(ifScheduledInNight == 1){
                for(int i=0;i<25;i++){
                    if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24){
                        continue;
                    }
                    validStartSlot.add(i);
                }
            }else if(ifScheduledInNight == 0){
                for(int i=0;i<25;i++){
                    if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24 || i == 3 || i == 8 || i == 13 || i == 18 || i == 23){
                        continue;
                    }
                    validStartSlot.add(i);
                }
            }

//            return Arrays.asList("00", "01", "02", "03", "05", "06", "07", "08", "10", "11", "12", "13", "15", "16", "17", "18", "20", "21", "22", "23");
//            validSlots = Arrays.asList("00", "01", "02", "03", "05", "06", "07", "08", "10", "11", "12", "13", "15", "16", "17", "18", "20", "21", "22", "23");
        }
        if(ifFixRoom.equals("1")){
            for(ExclusionRule exclusionRule : ExRoomList) {
                String roomNo = classesMapper.selectFixRoomNo(classNo);
                if(exclusionRule.getRoomNo().equals(roomNo)) {
//                validStartSlot.remove(exclusionRule.getTimeslot());
                    forbiddenSlots.add(exclusionRule.getTimeslot());
                }
            }
        }
        for(ExclusionRule exclusionRule : ExCourseList) {
            if(exclusionRule.getCourseNo().equals(courseNo)) {
//                validStartSlot.remove(exclusionRule.getTimeslot());
                forbiddenSlots.add(exclusionRule.getTimeslot());

            }
        }
        for(ExclusionRule exclusionRule : ExClassList) {
            if(exclusionRule.getClassNo().equals(classNo)) {
//                validStartSlot.remove(exclusionRule.getTimeslot());
                forbiddenSlots.add(exclusionRule.getTimeslot());
            }
        }
        for(ExclusionRule exclusionRule : ExTeacherList) {
            if(exclusionRule.getTeacherNo().equals(teacherNo)) {
//                validStartSlot.remove(exclusionRule.getTimeslot());
                forbiddenSlots.add(exclusionRule.getTimeslot());

            }
        }
        Set<Integer> ForbiddenSlots = new HashSet<>(forbiddenSlots);
        validStartSlot.removeAll(ForbiddenSlots);
        return validStartSlot.contains(timeslot);
    }

    /**
     * 计算每个个体的适应值
     * 目的:进行软约束
     */
    public double fitness(List<String> individualList,Map<String,List<Integer>> teacherTimeslotNum) {
        double penalty = 0;
        double reward = 0;
        for (String gene : individualList) {
            if(ifExceed(gene,teacherTimeslotNum)){
                penalty += 100;
            }
            // 检查连排起始节次是否合法
            if (!isValidStartDSlot(gene)) {
                penalty += 50; // 违反连排规则惩罚
            }
            // 检查是否为禁排节次
            if (isExclusionSlot(gene)) {
                penalty += 100; // 违反禁排规则惩罚
            }
            // 检查是否为优先排节次
            if(isPrioritySlot(gene)){
                reward += 60;
            }

        }
        double K1 = 3;// 权重1 - 不是早八
        double K2 = 5;// 权重2 - 不是晚课
        double K3 = 2;// 权重3 - 离散程度
        int F1 = 0;
        int F2 = 0;
        int F3;
        double Fx = 0;// 总适应度
        // 计算每一个个体的适应度
        for (String gene : individualList) {
            int timeslot = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene));
            boolean isEarlyMorning = timeslot%5 == 0;
            boolean isLateNight = timeslot%5 == 4;
            if (!isEarlyMorning) F1++;
            if (!isLateNight) F2++;
        }
        F3 = calculateDiscreteExpect(individualList);
        Fx = K1*F1 + K2*F2 + K3*F3 + 1 / 1 + penalty + reward;
        return Fx;
    }

    // 验证连排起始节次
    private boolean isValidStartDSlot(String gene) {
        int start = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene));
        int duration = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.DURATION, gene));
        if (duration == 2) {
            return Arrays.asList(0, 1, 2, 3, 4, 5, 6 ,7 ,8 ,9 ,10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24).contains(start);
        } else if (duration == 4) {
            return Arrays.asList(0, 1, 2, 3, 5, 6, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18, 20, 21, 22, 23).contains(start);
        }
        return false;
    }

    // 验证是否是禁排节次
    private boolean isExclusionSlot(String gene) {
        int timeslot = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene));
        String courseNo = ClassUtil.cutGene((ConstantInfo.COURSE_NO), gene);
        String classNo = ClassUtil.cutGene((ConstantInfo.CLASS_NO), gene);
        String teacherNo = ClassUtil.cutGene((ConstantInfo.TEACHER_NO), gene);
        String ifFixRoom = ClassUtil.cutGene((ConstantInfo.IFFIXROOM), gene);
        List<ExclusionRule> ExCourseList = getExclusionRules("Course");
        List<ExclusionRule> ExClassList = getExclusionRules("Class");
        List<ExclusionRule> ExTeacherList = getExclusionRules("Teacher");
        Set<Integer> forbiddenSlots = new HashSet<>();
        if(ifFixRoom.equals(1)){
            String RoomNo = classesMapper.selectFixRoomNo(classNo);
            List<ExclusionRule> ExRoomList = getExclusionRules("Room");
            for(ExclusionRule exclusionRule : ExRoomList) {
                if(exclusionRule.getRoomNo().equals(RoomNo)) {
                    forbiddenSlots.add(exclusionRule.getTimeslot());
                }
            }
        }
        for(ExclusionRule exclusionRule : ExCourseList) {
            if(exclusionRule.getCourseNo().equals(courseNo)) {
                forbiddenSlots.add(exclusionRule.getTimeslot());
            }
        }
        for(ExclusionRule exclusionRule : ExClassList) {
            if(exclusionRule.getClassNo().equals(classNo)) {
                forbiddenSlots.add(exclusionRule.getTimeslot());
            }
        }
        for(ExclusionRule exclusionRule : ExTeacherList) {
            if(exclusionRule.getTeacherNo().equals(teacherNo)) {
                forbiddenSlots.add(exclusionRule.getTimeslot());
            }
        }
        return forbiddenSlots.contains(timeslot);
    }

    // 验证是否是优先排节次
    private boolean isPrioritySlot(String gene) {
        int timeslot = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene));
        String courseDepartmentNo = ClassUtil.cutGene((ConstantInfo.COURSEDEPARTMENTNO), gene);
        String taskAttr = ClassUtil.cutGene((ConstantInfo.TASKATTR), gene);
        String courseAttr = ClassUtil.cutGene((ConstantInfo.COURSE_ATTR), gene);
        List<PriorityRule> prCourseDepartmentList = getPriorityRules("CourseDepartment");
        List<PriorityRule> prTaskAttrList = getPriorityRules("TaskAttr");
        List<PriorityRule> prCourseAttrList = getPriorityRules("CourseAttr");
        Set<Integer> prioritySlots = new HashSet<>();
        for(PriorityRule priorityRule : prCourseDepartmentList) {
            if(priorityRule.getCourseDepartmentNo().equals(courseDepartmentNo)) {
                prioritySlots.add(priorityRule.getTimeslot());
            }
        }
        for(PriorityRule priorityRule : prTaskAttrList) {
            if(priorityRule.getTaskAttr().equals(taskAttr)) {
                prioritySlots.add(priorityRule.getTimeslot());
            }
        }
        for(PriorityRule priorityRule : prCourseAttrList) {
            if(priorityRule.getCourseAttr().equals(courseAttr)) {
                prioritySlots.add(priorityRule.getTimeslot());
            }
        }
        return prioritySlots.contains(timeslot);
    }

    // 是否符合教师规则
    private boolean ifExceed(String gene,Map<String,List<Integer>> teacherTimeslotNum){
        String TeacherNo = ClassUtil.cutGene((ConstantInfo.TEACHER_NO), gene);
        int timeslot = Integer.parseInt(ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene));
        List<Integer> range = judgeTimeslot(timeslot);
        int dTimeslot = teacherRuleMapper.findDTByTeacherNo(TeacherNo) == null ? 0 : teacherRuleMapper.findDTByTeacherNo(TeacherNo);
        int mTimeslot = teacherRuleMapper.findMTByTeacherNo(TeacherNo) == null ? 0 : teacherRuleMapper.findMTByTeacherNo(TeacherNo);
        int eTimeslot = teacherRuleMapper.findETByTeacherNo(TeacherNo) == null ? 0 : teacherRuleMapper.findETByTeacherNo(TeacherNo);
        if(!range.isEmpty()){
            for (Integer integer : range) {
                int timeslotNum = teacherTimeslotNum.get(TeacherNo).get(integer);
//                int timeslotNum = 0;
                if (integer == 0 || integer == 3 || integer == 6 || integer == 9 || integer == 12) {
                    if (timeslotNum > mTimeslot) {
                        return true;
                    }
                } else if (integer == 1 || integer == 4 || integer == 7 || integer == 10 || integer == 13) {
                    if (timeslotNum > eTimeslot) {
                        return true;
                    }
                } else if (integer == 2 || integer == 5 || integer == 8 || integer == 11 || integer == 14) {
                    if (timeslotNum > dTimeslot) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 计算课程离散度期望值
     */
    private static int calculateDiscreteExpect(List<String> individualList) {
        // 离散程度期望值
        int F5 = 0;
        // 返回每个班级的对应课程下面的排序上课时间
        Map<String, List<String>> classTimeMap = courseGrouping(individualList);

        for (List<String> classTimeList : classTimeMap.values()) {
            if (classTimeList.size() > 1) {
                for (int i = 0; i < classTimeList.size() - 1; ++i) {
                    // 计算一门课上课的时间差
                    int temp = Integer.parseInt(classTimeList.get(++i)) - Integer.parseInt(classTimeList.get(i - 1));
                    F5 = F5 + judgingDiscreteValues(temp);
                }
            }
        }
        return F5;
    }
    /**
     * 将一个个体（班级课表）的同一门课程的所有上课时间进行统计，并且进行分组
     * 每个班级的课表都算是一个个体
     */
    private static Map<String, List<String>> courseGrouping(List<String> individualList) {
        Map<String, List<String>> classTimeMap = new HashMap<>();
        // 先将一个班级课表所上的课程区分出来（排除掉重复的课程）
        for (String gene : individualList) {
            classTimeMap.put(ClassUtil.cutGene(ConstantInfo.COURSE_NO, gene), null);
        }
        // 遍历课程编号
        for (String courseNo : classTimeMap.keySet()) {
            List<String> classTimeList = new ArrayList<>();
            for (String gene : individualList) {
                // 获得同一门课程的所有上课时间片
                if (ClassUtil.cutGene(ConstantInfo.COURSE_NO, gene).equals(courseNo)) {
                    classTimeList.add(ClassUtil.cutGene(ConstantInfo.TIMESLOT, gene));
                }
            }
            // 将课程的时间片进行排序
            Collections.sort(classTimeList);
            // 每一门课对应的上课时间集合(classNo, List)
            classTimeMap.put(courseNo, classTimeList);
        }
        return classTimeMap;
    }

    /**
     * 判断两课程的时间差在哪个区间
     * 并返回对应的期望值
     */
    private static int judgingDiscreteValues(int temp) {
        int[] tenExpectValue = {5, 6, 7, 8}; // 期望值为10时两课之间的时间差
        int[] sixExpectValue = {4, 9, 10, 11, 12, 13}; // 期望值为6时两课之间的时间差
        int[] fourExpectValue = {3, 14, 15, 16, 17, 18}; // 期望值为4时两课之间的时间差
        int[] twoExpectValue = {2, 19, 20, 21, 22, 23}; // 期望值为2时两课之间的时间差
        //int [] zeroExpectValue = {1,24};//期望值为0时两课之间的时间差
        if (ArrayUtils.contains(tenExpectValue, temp)) {
            return 10;
        } else if (ArrayUtils.contains(sixExpectValue, temp)) {
            return 6;
        } else if (ArrayUtils.contains(fourExpectValue, temp)) {
            return 4;
        } else if (ArrayUtils.contains(twoExpectValue, temp)) {
            return 2;
        } else {
            return 0;
        }
    }

}
