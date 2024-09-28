package com.leetcode2.广度优先搜索;


import java.util.*;

public class Solution2258 {
    // 定义四个方向的移动，分别是上、下、左、右
    static final int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maximumMinutes(int[][] grid) {
        // 获取网格的行数和列数
        final int n = grid.length, m = grid[0].length;
        // dps 为当前位置的状态，可以直接使用 grid 数组来表示状态，1 表示人走过，2 表示火烧过，4 表示墙
        var dps = grid;

        // 初始化火的队列，用于存储最新要扩展烧出去的火源点
        int[][] fire = new int[n * m][];
        int fireHead = 0, fireTail = -1;
        // 遍历网格，找到所有火源点并将其加入队列，同时将火的状态标记为 2
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (grid[y][x] == 1) fire[++fireTail] = new int[]{y, x};
                grid[y][x] <<= 1; // 将火的状态左移一位，标记为 2
            }
        }

        // 初始化人的队列，存储“可能”安全的不重复最早到达位置点
        int[][] men = new int[n * m][];
        int menHead = 0, menTail = 0;
        men[0] = new int[2]; // 初始位置（0,0）
        dps[0][0] = 1; // 将人的初始位置标记为已走过

        // 定义时间轴
        // menTopSaveTime 和 menLeftSaveTime 是人从上方和左方到达安全屋的时间点
        // fireTopTime 和 fireLeftTime 是火从上方和左方到达安全屋的时间点
        int time = 0;
        int menTopSaveTime = -1, menLeftSaveTime = -1;
        int fireTopTime = -1, fireLeftTime = -1;

        // 模拟火和人的扩展
        while (menHead <= menTail || fireHead <= fireTail) {
            time++; // 每循环一次时间增加 1

            // 人的扩展，遍历人的队列进行移动
            if (menHead <= menTail) {
                for (int i = 0, size = menTail - menHead; i <= size; i++) {
                    int[] lastPos = men[menHead++]; // 取出队首的位置
                    // 如果当前位置已经被火烧过，跳过
                    if (dps[lastPos[0]][lastPos[1]] > 1) continue;
                    // 尝试四个方向的移动
                    for (int[] move : moves) {
                        int posY = lastPos[0] + move[0], posX = lastPos[1] + move[1];
                        // 判断新位置是否在网格范围内且未被访问过
                        if (posY >= 0 && posY < n && posX >= 0 && posX < m && dps[posY][posX] == 0) {
                            dps[posY][posX] = 1; // 标记为人走过
                            men[++menTail] = new int[]{posY, posX}; // 将新位置加入人的队列
                        }
                    }
                }
                // 如果人到达了安全屋（网格右下角），记录到达时间
                if (dps[n - 1][m - 1] == 1) {
                    menHead = men.length; // 停止人的扩展

                    // 使用位操作记录人到达安全屋的时间
                    menTopSaveTime = time + (dps[n - 2][m - 1] & 1 ^ 1); // 从上方到达安全屋的时间
                    menLeftSaveTime = time + (dps[n - 1][m - 2] & 1 ^ 1); // 从左方到达安全屋的时间
                }
            }

            // 火的扩展，遍历火的队列进行扩展
            if (fireHead <= fireTail) {
                for (int i = 0, size = fireTail - fireHead; i <= size; i++) {
                    int[] lastPos = fire[fireHead++]; // 取出队首的火源点
                    // 尝试四个方向的扩展
                    for (int[] move : moves) {
                        int posY = lastPos[0] + move[0], posX = lastPos[1] + move[1];
                        // 判断新位置是否在网格范围内且未被火烧过或是墙
                        if (posY >= 0 && posY < n && posX >= 0 && posX < m && dps[posY][posX] < 2) {
                            dps[posY][posX] = 2; // 标记为火烧过
                            fire[++fireTail] = new int[]{posY, posX}; // 将新位置加入火的队列
                        }
                    }
                }
                // 如果火到达了安全屋（网格右下角），记录到达时间
                if (dps[n - 1][m - 1] == 2) {
                    fireHead = fire.length; // 停止火的扩展

                    // 使用位操作记录火到达安全屋的时间
//                    fireTopTime = time + (dps[n - 2][m - 1] >> 1 & 1 ^ 1); // 从上方到达安全屋的时间
//                    fireLeftTime = time + (dps[n - 1][m - 2] >> 1 & 1 ^ 1); // 从左方到达安全屋的时间
                    // 计算从上方到达安全屋的火到达时间
                    if (dps[n - 2][m - 1] == 2) {
                        fireTopTime = time; // 如果火已经到达上方，则时间就是当前时间
                    } else {
                        fireTopTime = time + 1; // 否则，火需要额外一单位时间到达
                    }

// 计算从左方到达安全屋的火到达时间
                    if (dps[n - 1][m - 2] == 2) {
                        fireLeftTime = time; // 如果火已经到达左方，则时间就是当前时间
                    } else {
                        fireLeftTime = time + 1; // 否则，火需要额外一单位时间到达
                    }

                }
            }
        }

        // 如果人无法到达安全屋，返回 -1
        if (menTopSaveTime == -1 && menLeftSaveTime == -1) return -1;

        // 如果火无法到达安全屋，返回一个很大的值表示无穷大
        if (fireTopTime == -1 && fireLeftTime == -1) return (int) 1e9;

        // 计算人在到达安全屋后火到达的最短时间差
        return Math.max(fireTopTime - menTopSaveTime, fireLeftTime - menLeftSaveTime) - 1;
    }

    public static void main(String[] args) {
//        int[][] grid = {
//                {0, 2, 0, 0, 0, 0, 0},
//                {0, 0, 0, 2, 2, 1, 0},
//                {0, 2, 0, 0, 1, 2, 0},
//                {0, 0, 2, 2, 2, 0, 2},
//                {0, 0, 0, 0, 0, 0, 0}
//        };
        int[][] grid = {
                {0, 2, 0, 0, 1},
                {0, 2, 0, 2, 2},
                {0, 2, 0, 0, 0},
                {0, 0, 2, 2, 0},
                {0, 0, 0, 0, 0}
        };
        Solution2258 solution2258 = new Solution2258();
        System.out.println(solution2258.maximumMinutes(grid));

    }
}
