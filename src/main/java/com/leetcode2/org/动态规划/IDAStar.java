package com.leetcode2.org.动态规划;
import java.util.*;
public class IDAStar {
    // 目标状态
    private static final int[][] goalState = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };
    // 计算启发式函数的值（曼哈顿距离）
    private static int heuristic(int[][] state) {
        int distance = 0;
        for (int i = 0; i < 3; i++) { // 遍历每个位置
            for (int j = 0; j < 3; j++) {
                int value = state[i][j]; // 当前值
                if (value != 0) { // 忽略空格（0）
                    int targetX = (value - 1) / 3; // 目标行
                    int targetY = (value - 1) % 3; // 目标列
                    distance += Math.abs(i - targetX) + Math.abs(j - targetY); // 计算曼哈顿距离
                }
            }
        }
        return distance;
    }

    // 深度优先搜索
    private static boolean dfs(int[][] state, int g, int threshold, List<String> path) {
        int h = heuristic(state); // 启发式函数值
        int f = g + h; // 总成本 f = g + h
        if (f > threshold) return false; // 超过阈值返回 false
        if (Arrays.deepEquals(state, goalState)) return true; // 达到目标状态返回 true
        int[] zeroPos = findZero(state); // 查找空格（0）的位置
        int x = zeroPos[0], y = zeroPos[1];
        // 四个可能的移动方向
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : directions) {
            int newX = x + dir[0], newY = y + dir[1];
            // 检查移动是否合法
            if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                swap(state, x, y, newX, newY); // 交换位置
                path.add(String.format("Move (%d, %d) to (%d, %d)", x, y, newX, newY)); // 添加移动路径
                if (dfs(state, g + 1, threshold, path)) return true; // 递归深度优先搜索
                path.remove(path.size() - 1); // 回溯，移除路径
                swap(state, newX, newY, x, y); // 交换回原位置
            }
        }
        return false;
    }
    // 交换两个位置的值
    private static void swap(int[][] state, int x1, int y1, int x2, int y2) {
        int temp = state[x1][y1];
        state[x1][y1] = state[x2][y2];
        state[x2][y2] = temp;
    }
    // 查找0的位置
    private static int[] findZero(int[][] state) {
        for (int i = 0; i < 3; i++) { // 遍历每个位置查找空格（0）
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    // IDA*搜索
    public static List<String> idaStar(int[][] initialState) {
        int threshold = heuristic(initialState); // 初始阈值为启发式函数值
        List<String> path = new ArrayList<>();
        while (true) { // 迭代加深搜索
            if (dfs(initialState, 0, threshold, path)) { // 进行深度优先搜索
                return path; // 找到路径，返回结果
            }
            threshold++; // 增加阈值
        }
    }
    public static void main(String[] args) {
        int[][] initialState = {
                {0, 4, 8},
                {5, 2, 1},
                {6, 3, 7}
        };
        List<String> result = idaStar(initialState);
        if (result != null) {
            for (String step : result) {
                System.out.println(step);
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
