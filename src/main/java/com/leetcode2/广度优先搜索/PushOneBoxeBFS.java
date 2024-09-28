package com.leetcode2.广度优先搜索;

import java.util.*;

public class PushOneBoxeBFS {
    // 定义四个方向：上、下、左、右
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows, cols;

    // 主函数：计算推箱子所需的最少步数
    public int minPushBox(char[][] grid) {
        rows = grid.length; // 获取行数
        cols = grid[0].length; // 获取列数
        int[] player = null, box = null; // 玩家和箱子的初始位置
        List<int[]> targets = new ArrayList<>(); // 目标位置列表

        // 找到初始位置和目标位置
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'S') player = new int[]{i, j}; // 找到玩家初始位置
                else if (grid[i][j] == 'B') box = new int[]{i, j}; // 找到箱子初始位置
                else if (grid[i][j] == 'P') targets.add(new int[]{i, j}); // 添加目标位置
            }
        }

        // 使用队列进行广度优先搜索（BFS）
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>(); // 记录访问过的状态
        queue.offer(new State(player[0], player[1], box[0], box[1], 0)); // 初始状态入队

        // 开始BFS
        while (!queue.isEmpty()) {
            State curr = queue.poll(); // 获取当前状态
            // 如果箱子到达目标位置，则返回推箱子的次数
            if (isTarget(curr.bx, curr.by, targets)) {
                return curr.pushes;
            }

            // 生成当前状态的唯一标识符
            String key = curr.px + "," + curr.py + "," + curr.bx + "," + curr.by;
            if (visited.contains(key)) continue; // 如果已经访问过该状态，跳过
            visited.add(key); // 标记为已访问

            // 尝试从四个方向移动玩家
            for (int[] dir : DIRECTIONS) {
                int npx = curr.px + dir[0], npy = curr.py + dir[1]; // 新的玩家位置
                if (isValid(grid, npx, npy)) { // 检查新位置是否合法
                    // 如果玩家移动到箱子位置
                    if (npx == curr.bx && npy == curr.by) {
                        int nbx = curr.bx + dir[0], nby = curr.by + dir[1]; // 新的箱子位置
                        if (isValid(grid, nbx, nby)) { // 检查新的箱子位置是否合法
                            queue.offer(new State(npx, npy, nbx, nby, curr.pushes + 1)); // 新状态入队
                        }
                    } else {
                        queue.offer(new State(npx, npy, curr.bx, curr.by, curr.pushes)); // 玩家移动但不推箱子的状态入队
                    }
                }
            }
        }
        return -1; // 如果无法到达目标位置，返回-1
    }
    // 检查位置是否合法（是否在边界内且不是墙）
    private boolean isValid(char[][] grid, int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] != '#';
    }
    // 检查当前位置是否是目标位置
    private boolean isTarget(int x, int y, List<int[]> targets) {
        for (int[] target : targets) {
            if (x == target[0] && y == target[1]) return true;
        }
        return false;
    }
    // 定义状态类
    private class State {
        int px, py, bx, by, pushes;
        State(int px, int py, int bx, int by, int pushes) {
            this.px = px; this.py = py; this.bx = bx; this.by = by; this.pushes = pushes;
        }
    }
    public static void main(String[] args) {
        // 定义地图
        char[][] grid = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '#', '#', '#', '#'},
                {'#', '.', '#', '#', '#', '#', '.', '.', '#', '#', '#', '#'},
                {'#', '.', '#', '#', '#', '#', '.', '.', '#', 'P', '#', '#'},
                {'#', '.', '#', '#', '#', '#', '.', '.', '#', '.', '#', '#'},
                {'#', '.', '.', '.', '.', '.', 'B', '.', '.', '.', '.', '.'},
                {'#', '#', '#', '#', '#', '#', '#', '.', '#', '.', '#', '.'},
                {'#', '#', '#', '#', '#', '#', 'S', '.', '#', '.', '.', '.'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };

        PushOneBoxeBFS solution = new PushOneBoxeBFS();

        // 输出测试结果
        System.out.println("Test Case 1 Result: " + solution.minPushBox(grid));
    }

//    改代码只能找到一个箱子。。多个箱子就不行了。。。。
}
