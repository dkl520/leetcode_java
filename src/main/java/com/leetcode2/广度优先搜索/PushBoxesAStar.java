package com.leetcode2.广度优先搜索;

import java.util.*;

public class PushBoxesAStar {
    // 定义移动方向数组，上下左右四个方向
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows, cols;

    // 计算最少的推箱子次数
    public int minPushBox(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int[] player = null;  // 存储玩家的位置
        List<int[]> boxes = new ArrayList<>();  // 存储箱子的位置
        List<int[]> targets = new ArrayList<>();  // 存储目标位置

        // 遍历网格，初始化玩家位置、箱子位置和目标位置
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'S') player = new int[]{i, j};  // 玩家起始位置
                else if (grid[i][j] == 'B') boxes.add(new int[]{i, j});  // 箱子位置
                else if (grid[i][j] == 'P') targets.add(new int[]{i, j});  // 目标位置
            }
        }

        // 使用优先队列实现A*算法，按f值排序
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.f - b.f);
        Set<String> visited = new HashSet<>();  // 用于记录访问过的状态
        State initialState = new State(player[0], player[1], boxes, 0);
        initialState.f = initialState.g + heuristic(boxes, targets);  // 计算初始状态的f值
        pq.offer(initialState);

        // 开始A*搜索
        while (!pq.isEmpty()) {
            State curr = pq.poll();  // 获取当前状态
            if (isAllBoxesOnTarget(curr.boxes, targets)) {
                return curr.g;  // 如果所有箱子都在目标位置，返回当前步数
            }

            String key = getStateKey(curr);  // 获取当前状态的键值
            if (visited.contains(key)) continue;  // 如果状态已访问过，跳过
            visited.add(key);  // 标记状态为已访问

            // 尝试向四个方向移动
            for (int[] dir : DIRECTIONS) {
                int npx = curr.px + dir[0], npy = curr.py + dir[1];
                if (isValid(grid, npx, npy)) {  // 判断移动后的玩家位置是否合法
                    boolean pushed = false;
                    List<int[]> newBoxes = new ArrayList<>(curr.boxes);  // 复制当前箱子状态
                    for (int i = 0; i < newBoxes.size(); i++) {
                        int[] box = newBoxes.get(i);
                        if (npx == box[0] && npy == box[1]) {
                            int nbx = box[0] + dir[0], nby = box[1] + dir[1];
                            if (isValid(grid, nbx, nby) && !hasBox(newBoxes, nbx, nby)) {
                                newBoxes.set(i, new int[]{nbx, nby});  // 更新箱子位置
                                pushed = true;
                                break;
                            }
                        }
                    }
                    State newState = new State(npx, npy, newBoxes, curr.g + (pushed ? 1 : 0));  // 生成新状态
                    newState.f = newState.g + heuristic(newBoxes, targets);  // 计算新状态的f值
                    pq.offer(newState);  // 将新状态加入优先队列
                }
            }
        }

        return -1;  // 如果找不到解，返回-1
    }

    // 判断位置是否合法
    private boolean isValid(char[][] grid, int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] != '#';
    }

    // 判断所有箱子是否都在目标位置
    private boolean isAllBoxesOnTarget(List<int[]> boxes, List<int[]> targets) {
        for (int[] box : boxes) {
            boolean onTarget = false;
            for (int[] target : targets) {
                if (box[0] == target[0] && box[1] == target[1]) {
                    onTarget = true;
                    break;
                }
            }
            if (!onTarget) return false;
        }
        return true;
    }

    // 判断位置是否有箱子
    private boolean hasBox(List<int[]> boxes, int x, int y) {
        for (int[] box : boxes) {
            if (box[0] == x && box[1] == y) {
                return true;
            }
        }
        return false;
    }

    // 生成状态的唯一键值
    private String getStateKey(State state) {
        StringBuilder sb = new StringBuilder();
        sb.append(state.px).append(",").append(state.py);
        for (int[] box : state.boxes) {
            sb.append(",").append(box[0]).append(",").append(box[1]);
        }
        return sb.toString();
    }

    // 启发函数，计算所有箱子到目标位置的曼哈顿距离之和
    private int heuristic(List<int[]> boxes, List<int[]> targets) {
        int totalDistance = 0;
        for (int[] box : boxes) {
            int minDistance = Integer.MAX_VALUE;
            for (int[] target : targets) {
                int distance = Math.abs(box[0] - target[0]) + Math.abs(box[1] - target[1]);
                minDistance = Math.min(minDistance, distance);
            }
            totalDistance += minDistance;
        }
        return totalDistance;
    }

    // 定义状态类
    private class State {
        int px, py, g, f;
        List<int[]> boxes;

        State(int px, int py, List<int[]> boxes, int g) {
            this.px = px;
            this.py = py;
            this.boxes = boxes;
            this.g = g;
        }
    }

    public static void main(String[] args) {
        // 示例网格，使用不同布局进行测试
//        char[][] grid = {
//                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
//                {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', '#'},
//                {'#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', 'B', '#', '#', ' ', ' ', '#'},
//                {'#', '#', '#', '#', '#', '#', '#', ' ', ' ', '#', ' ', ' ', ' ', 'B', ' ', '#'},
//                {'#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', ' ', '#', ' ', '#', ' ', '#'},
//                {'#', '#', '#', '#', '#', '#', '#', ' ', 'B', ' ', 'B', ' ', ' ', 'B', ' ', '#'},
//                {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', 'B', '#', ' ', ' ', '#'},
//                {'#', '#', '#', 'S', ' ', 'B', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#'},
//                {'#', '#', '#', ' ', 'P', 'P', 'B', ' ', 'B', 'B', 'P', '#', '#', '#', '#', '#'},
//                {'#', '#', '#', '#', 'P', 'P', 'P', 'P', 'P', 'P', '#', '#', '#', '#', '#', '#'},
//                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
//        };
//        char[][] grid = {
//                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
//                {'#', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', '#'},
//                {'#', '#', 'B', '#', '#', '#', ' ', ' ', ' ', '#'},
//                {'#', 'S', ' ', ' ', 'B', ' ', ' ', 'B', ' ', '#'},
//                {'#', ' ', 'P', 'P', '#', ' ', 'B', ' ', '#', '#'},
//                {'#', '#', 'P', 'P', '#', ' ', ' ', ' ', '#', '#'},
//                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
//        };
        char[][] grid = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', '#', '#', ' ', 'B', ' ', 'B', ' ', 'B', ' ', 'B', ' ', ' ', ' ', '#'},
                {'#', '#', '#', ' ', '#', 'P', '#', 'P', '#', 'P', '#', 'S', 'B', '#', '#'},
                {'#', '#', '#', 'P', 'P', 'P', 'P', 'P', 'P', 'P', ' ', ' ', ' ', '#', '#'},
                {'#', '#', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', 'B', '#', '#', '#'},
                {'#', '#', '#', ' ', 'B', ' ', 'B', ' ', 'B', ' ', 'B', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},

        };
        long startTime = System.currentTimeMillis();

        PushBoxesAStar solution = new PushBoxesAStar();
        long endTime = System.currentTimeMillis();
        System.out.println("Minimum pushes required: " + solution.minPushBox(grid));
        System.out.println((endTime - startTime) / 100000);
    }
}
