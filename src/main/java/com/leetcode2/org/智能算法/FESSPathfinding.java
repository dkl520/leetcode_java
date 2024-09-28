package com.leetcode2.org.智能算法;

import java.util.*;

public class FESSPathfinding {
    // 状态类
    static class State {
        int x;
        int y;
        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }
        // 获取状态的特征
        public Map<String, Double> getFeatures() {
            Map<String, Double> features = new HashMap<>();
            features.put("ManhattanDistance", (double) (Math.abs(x - goalX) + Math.abs(y - goalY)));
            features.put("ObstacleCount", countObstacles(x, y));
            return features;
        }
    }
    // 操作类
    static class Action {
        int dx;
        int dy;

        public Action(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        // 获取操作的特征
        public Map<String, Double> getFeatures() {
            Map<String, Double> features = new HashMap<>();
            features.put("Direction", Math.atan2(dy, dx));
            return features;
        }
    }

    // 网格地图
    static int[][] grid = {
            {0, 0, 1, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0}
    };

    // 起点和终点坐标
    static int startX = 0;
    static int startY = 0;
    static int goalX = 4;
    static int goalY = 4;

    // 检查是否为障碍物
    static boolean isObstacle(int x, int y) {
        return grid[x][y] == 1;
    }

    // 计算周围障碍物数量
    static double countObstacles(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int newX = x + i;
                int newY = y + j;
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && isObstacle(newX, newY)) {
                    count++;
                }
            }
        }
        return count;
    }

    // FESS 算法
    public static State search(List<State> states, List<Action> actions) {

        // 1. 特征提取
        List<String> featureNames = extractFeatures(states, actions);

        // 2. 特征空间构建
        Map<State, double[]> featureSpace = buildFeatureSpace(states, featureNames);

        // 3. 搜索 (使用贪婪搜索)
        State bestState = findBestState(featureSpace);

        // 4. 映射回原始空间
        return bestState;
    }

    // 提取特征
    private static List<String> extractFeatures(List<State> states, List<Action> actions) {
        List<String> featureNames = new ArrayList<>();
        for (State state : states) {
            featureNames.addAll(state.getFeatures().keySet());
        }
        for (Action action : actions) {
            featureNames.addAll(action.getFeatures().keySet());
        }
        return featureNames;
    }

    // 构建特征空间
    private static Map<State, double[]> buildFeatureSpace(List<State> states, List<String> featureNames) {
        Map<State, double[]> featureSpace = new HashMap<>();
        for (State state : states) {
            double[] featureVector = new double[featureNames.size()];
            for (int i = 0; i < featureNames.size(); i++) {
                String featureName = featureNames.get(i);
                if (state.getFeatures().containsKey(featureName)) {
                    featureVector[i] = state.getFeatures().get(featureName);
                }
            }
            featureSpace.put(state, featureVector);
        }
        return featureSpace;
    }

    // 使用贪婪搜索找到最优状态
    private static State findBestState(Map<State, double[]> featureSpace) {
        State bestState = null;
        double bestScore = Double.MAX_VALUE;
        for (Map.Entry<State, double[]> entry : featureSpace.entrySet()) {
            State state = entry.getKey();
            double[] featureVector = entry.getValue();
            double score = featureVector[0] + featureVector[1]; // 曼哈顿距离 + 障碍物数量
            if (score < bestScore) {
                bestScore = score;
                bestState = state;
            }
        }
        return bestState;
    }

    public static void main(String[] args) {
        // 创建状态和操作
        List<State> states = new ArrayList<>();
        List<Action> actions = new ArrayList<>();

        // 生成所有可能的动作
        actions.add(new Action(-1, 0)); // 左
        actions.add(new Action(1, 0)); // 右
        actions.add(new Action(0, -1)); // 上
        actions.add(new Action(0, 1)); // 下

        // 从起点开始生成所有可达状态
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(startX, startY));
        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            states.add(currentState);
            for (Action action : actions) {
                int newX = currentState.x + action.dx;
                int newY = currentState.y + action.dy;
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && !isObstacle(newX, newY)) {
                    State newState = new State(newX, newY);
                    if (!states.contains(newState)) {
                        queue.offer(newState);
                    }
                }
            }
        }

        // 执行 FESS 算法
        State bestState = search(states, actions);

        // 打印最优状态
        System.out.println("Best State: (" + bestState.x + ", " + bestState.y + ")");
    }
}