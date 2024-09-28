//package com.leetcode2.游戏;
//
//import java.util.*;
//import java.time.Instant;
//
//class SheepSolver {
//    // 全局配置
//    private Map<String, Object> globalConfig;
//    // 卡牌序列
//    private CardSequence cardSequence;
//    // 游戏池控制器
//    private GamePoolController gamePoolController;
//
//    // 开始时间
//    private Instant startTime;
//    // 每次迭代的时间
//    private double iterationTime;
//
//    // 卡牌总数
//    private int cardCount;
//    // 当前进度
//    private double currentProgress;
//    // 最大进度
//    private double maximumProgress;
//    // 情境历史记录，用于避免重复状态
//    private Set<String> situationHistory;
//
//    // 构造函数，初始化各个组件
//    public SheepSolver(String solveType) {
//        this.globalConfig = generateGlobalConfig(); // 生成全局配置
//        this.cardSequence = new CardSequence(); // 初始化卡牌序列
//        this.gamePoolController = new GamePoolController(solveType, this.globalConfig); // 初始化游戏池控制器
//
//        this.startTime = null; // 初始化开始时间
//        this.iterationTime = 0; // 初始化迭代时间
//
//        this.cardCount = 0; // 初始化卡牌总数
//        this.currentProgress = 0; // 初始化当前进度
//        this.maximumProgress = 0; // 初始化最大进度
//        this.situationHistory = new HashSet<>(); // 初始化情境历史记录
//    }
//
//    // 加载地图数据并准备游戏数据
//    public void loadMapData(Map<String, Object> mapData) {
//        this.gamePoolController.initMapData(mapData); // 初始化地图数据
//        this.gamePoolController.prepareGameData(this.cardSequence); // 准备游戏数据
//        this.cardCount = this.gamePoolController.getAllCardCount(); // 获取卡牌总数
//    }
//
//    // 求解函数
//    public void solve() {
//        showSolvingProgress(); // 显示求解进度
//        recordCurrentProgress(); // 记录当前进度
//        List<Integer> headList = this.gamePoolController.generateHeadList(); // 生成头部列表
//        headList = this.gamePoolController.ensureHeadListAlive(headList); // 确保头部列表中的元素存活
//        headList = this.gamePoolController.ensureHeadListDisappear(headList, this.currentProgress); // 确保头部列表中的元素消失
//        for (Integer headItem : headList) { // 遍历头部列表
//            generateCurrentIterationTime(); // 生成当前迭代时间
//            if (!checkProgrammeCanContinue()) { // 检查程序是否可以继续
//                break;
//            }
//            operationPickCard(headItem); // 选取卡牌操作
//            String headFingerprint = this.gamePoolController.generateHeadFingerprint(); // 生成头部指纹
//            if (this.situationHistory.contains(headFingerprint)) { // 如果情境历史中已存在该指纹
//                operationRecoverCard(headItem); // 恢复卡牌操作
//                continue;
//            } else {
//                this.situationHistory.add(headFingerprint); // 将指纹添加到情境历史中
//            }
//            solve(); // 递归调用solve进行求解
//            if (!this.gamePoolController.isGameOver()) { // 如果游戏没有结束
//                operationRecoverCard(headItem); // 恢复卡牌操作
//            } else {
//                break; // 游戏结束，跳出循环
//            }
//        }
//    }
//
//    // 生成全局配置
//    private Map<String, Object> generateGlobalConfig() {
//        return ProjectHelper.getProjectConfig("normal", "global");
//    }
//
//    // 生成当前迭代时间
//    private void generateCurrentIterationTime() {
//        if (this.startTime != null) {
//            this.iterationTime = (Instant.now().toEpochMilli() - this.startTime.toEpochMilli()) / 1000.0;
//        } else {
//            this.startTime = Instant.now();
//            this.iterationTime = 0;
//        }
//    }
//
//    // 检查程序是否可以继续
//    private boolean checkProgrammeCanContinue() {
//        if (isSolverInTimeLimit()) { // 检查是否在时间限制内
//            return isSolverProgressMeetsExpect(); // 检查进度是否符合预期
//        } else {
//            return false;
//        }
//    }
//
//    // 检查求解器是否在时间限制内
//    private boolean isSolverInTimeLimit() {
//        int timeLimit = (int) this.globalConfig.get("time_limit");
//        return timeLimit < 0 || this.iterationTime <= timeLimit;
//    }
//
//    // 检查进度是否符合预期
//    private boolean isSolverProgressMeetsExpect() {
//        Map<String, Object> expectProgress = (Map<String, Object>) this.globalConfig.get("expect_progress");
//        int expectTime = (int) expectProgress.get("time");
//        double expectPercentage = (double) expectProgress.get("percentage");
//        boolean detectResult = this.iterationTime <= expectTime || this.maximumProgress >= expectPercentage;
//        return expectTime < 0 || detectResult;
//    }
//
//    // 显示求解进度
//    private void showSolvingProgress() {
//        if ((boolean) this.globalConfig.get("show_progress")) {
//            List<Integer> pickIndexList = this.cardSequence.getPickIndexList();
//            pickIndexList.removeIf(item -> item < 0);
//            System.out.printf("当前进度为: %d/%d%n", pickIndexList.size(), this.cardCount);
//        }
//    }
//
//    // 记录当前进度
//    private void recordCurrentProgress() {
//        List<Integer> pickIndexList = this.cardSequence.getPickIndexList();
//        pickIndexList.removeIf(item -> item < 0);
//        this.currentProgress = (double) pickIndexList.size() / this.cardCount;
//        if (this.currentProgress > this.maximumProgress) {
//            this.maximumProgress = this.currentProgress;
//        }
//    }
//
//    // 选取卡牌操作
//    private void operationPickCard(int cardIndex) {
//        this.gamePoolController.pickCard(cardIndex);
//    }
//
//    // 恢复卡牌操作
//    private void operationRecoverCard(int cardIndex) {
//        this.gamePoolController.recoverCard(cardIndex);
//    }
//
//    // 生成卡牌详细字典
//    private Map<Integer, Card> generateCardDetailDict() {
//        return this.gamePoolController.getAllCardDict();
//    }
//
//    // 从详细字典中获取卡牌ID
//    private static String getCardIdFromDetail(Map<Integer, Card> cardDetailDict, int cardIndex) {
//        if (cardDetailDict.containsKey(cardIndex)) {
//            return cardDetailDict.get(cardIndex).getCardId();
//        } else {
//            return "0-0-0";
//        }
//    }
//
//    // 生成卡牌ID列表
//    private List<String> generateCardIdList(Map<Integer, Card> cardDetailDict, List<Integer> cardList) {
//        List<String> resultList = new ArrayList<>();
//        for (Integer cardItem : cardList) {
//            String resultItem = getCardIdFromDetail(cardDetailDict, cardItem);
//            resultList.add(resultItem);
//        }
//        return resultList;
//    }
//
//    // 生成卡牌类型列表
//    private static List<Map<String, Object>> generateCardTypeList(List<int[]> cardList) {
//        List<Map<String, Object>> resultList = new ArrayList<>();
//        for (int[] cardItem : cardList) {
//            Map<String, Object> item = new HashMap<>();
//            item.put("index", cardItem[0]);
//            item.put("type", cardItem[1]);
//            resultList.add(item);
//        }
//        return resultList;
//    }
//
//    // 生成卡牌索引结果
//    public List<Integer> generateCardIndexResult() {
//        if (this.gamePoolController.isGameOver()) {
//            return this.cardSequence.getPickIndexList();
//        } else {
//            return null;
//        }
//    }
//
//    // 生成卡牌ID结果
//    public List<String> generateCardIdResult() {
//        if (this.gamePoolController.isGameOver()) {
//            Map<Integer, Card> cardDetailDict = generateCardDetailDict();
//            List<Integer> pickIndexList = this.cardSequence.getPickIndexList();
//            return generateCardIdList(cardDetailDict, pickIndexList);
//        } else {
//            return null;
//        }
//    }
//
//    // 生成卡牌类型结果
//    public List<Map<String, Object>> generateCardTypeResult() {
//        if (this.gamePoolController.isGameOver()) {
//            List<int[]> pickTypeList = this.cardSequence.getPickTypeList();
//            return generateCardTypeList(pickTypeList);
//        } else {
//            return null;
//        }
//    }
//}
