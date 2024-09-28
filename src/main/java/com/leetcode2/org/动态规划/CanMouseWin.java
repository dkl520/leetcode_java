package com.leetcode2.org.动态规划;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CanMouseWin {
    int rows, columns; // 定义网格的行数和列数
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        rows = grid.length; // 获取网格的行数
        columns = grid[0].length(); // 获取网格的列数

        // 创建一个5维数组来存储每个状态的出度
        // [猫的行][猫的列][老鼠的行][老鼠的列][当前回合（0为老鼠，1为猫）]
        int[][][][][] degrees = new int[rows][columns][rows][columns][2];

        // 初始化起始位置和食物位置
        int startCatRow = -1, startCatColumn = -1, startMouseRow = -1, startMouseColumn = -1, foodRow = -1, foodColumn = -1;

        // 遍历网格，找到猫、老鼠和食物的初始位置
        for (int i = 0; i < rows; i++) {
            String row = grid[i];
            for (int j = 0; j < columns; j++) {
                if (row.charAt(j) == 'C') { // 找到猫的位置
                    startCatRow = i;
                    startCatColumn = j;
                } else if (row.charAt(j) == 'M') { // 找到老鼠的位置
                    startMouseRow = i;
                    startMouseColumn = j;
                } else if (row.charAt(j) == 'F') { // 找到食物的位置
                    foodRow = i;
                    foodColumn = j;
                }
            }
        }

        // 计算每个状态的出度
        for (int i = 0; i < rows; i++) {
            String row = grid[i];
            for (int j = 0; j < columns; j++) {
                if (row.charAt(j) != '#') { // 如果不是墙
                    for (int k = 0; k < rows; k++) {
                        String row2 = grid[k];
                        for (int m = 0; m < columns; m++) {
                            if (row2.charAt(m) != '#') { // 如果不是墙
                                // 计算老鼠的可移动位置数量
                                degrees[i][j][k][m][0] = getNeighbors(grid, k, m, mouseJump).size();
                                // 计算猫的可移动位置数量
                                degrees[i][j][k][m][1] = getNeighbors(grid, i, j, catJump).size();
                            }
                        }
                    }
                }
            }
        }

        // 创建一个5维数组来存储游戏状态
        // 1表示必胜，-1表示必败，0表示未知
        int[][][][][] game = new int[rows][columns][rows][columns][2];

        Queue<int[]> queue = new LinkedList<int[]>();

        // 初始化游戏状态
        for (int i = 0; i < rows; i++) {
            String row = grid[i];
            for (int j = 0; j < columns; j++) {
                if (row.charAt(j) != '#' && row.charAt(j) != 'F') { // 如果不是墙和食物
                    // 猫和老鼠在同一位置，老鼠必败，猫必胜
                    game[i][j][i][j][0] = -1;
                    game[i][j][i][j][1] = 1;
                    queue.offer(new int[]{i, j, i, j, 0});
                    queue.offer(new int[]{i, j, i, j, 1});
                }
            }
        }

        // 处理食物位置的特殊情况
        for (int i = 0; i < rows; i++) {
            String row = grid[i];
            for (int j = 0; j < columns; j++) {
                if (row.charAt(j) != '#' && row.charAt(j) != 'F') { // 如果不是墙和食物
                    // 老鼠到达食物位置必胜，猫到达食物位置必败
                    game[foodRow][foodColumn][i][j][0] = -1;
                    game[i][j][foodRow][foodColumn][1] = -1;
                    queue.offer(new int[]{foodRow, foodColumn, i, j, 0});
                    queue.offer(new int[]{i, j, foodRow, foodColumn, 1});
                }
            }
        }

        // 使用BFS遍历所有可能的游戏状态
        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int catRow = state[0], catColumn = state[1], mouseRow = state[2], mouseColumn = state[3], turn = state[4];

            if (turn == 0) { // 猫的回合
                List<int[]> neighbors = getNeighbors(grid, catRow, catColumn, catJump);
                for (int[] neighbor : neighbors) {
                    int row = neighbor[0], column = neighbor[1];
                    degrees[row][column][mouseRow][mouseColumn][turn ^ 1]--;
                    if (game[row][column][mouseRow][mouseColumn][turn ^ 1] == 0) {
                        if (game[catRow][catColumn][mouseRow][mouseColumn][turn] == -1) {
                            // 如果当前状态是必败，那么下一个状态就是必胜
                            game[row][column][mouseRow][mouseColumn][turn ^ 1] = 1;
                            queue.offer(new int[]{row, column, mouseRow, mouseColumn, turn ^ 1});
                        } else if (degrees[row][column][mouseRow][mouseColumn][turn ^ 1] == 0) {
                            // 如果所有可能的移动都已经探索完，且没有必胜策略，那么这个状态就是必败的
                            game[row][column][mouseRow][mouseColumn][turn ^ 1] = -1;
                            queue.offer(new int[]{row, column, mouseRow, mouseColumn, turn ^ 1});
                        }
                    }
                }
            } else { // 老鼠的回合
                List<int[]> neighbors = getNeighbors(grid, mouseRow, mouseColumn, mouseJump);
                for (int[] neighbor : neighbors) {
                    int row = neighbor[0], column = neighbor[1];
                    degrees[catRow][catColumn][row][column][turn ^ 1]--;
                    if (game[catRow][catColumn][row][column][turn ^ 1] == 0) {
                        if (game[catRow][catColumn][mouseRow][mouseColumn][turn] == -1) {
                            // 如果当前状态是必败，那么下一个状态就是必胜
                            game[catRow][catColumn][row][column][turn ^ 1] = 1;
                            queue.offer(new int[]{catRow, catColumn, row, column, turn ^ 1});
                        } else if (degrees[catRow][catColumn][row][column][turn ^ 1] == 0) {
                            // 如果所有可能的移动都已经探索完，且没有必胜策略，那么这个状态就是必败的
                            game[catRow][catColumn][row][column][turn ^ 1] = -1;
                            queue.offer(new int[]{catRow, catColumn, row, column, turn ^ 1});
                        }
                    }
                }
            }
        }

        // 返回初始状态下老鼠是否能赢
        return game[startCatRow][startCatColumn][startMouseRow][startMouseColumn][0] == 1;
    }

    // 获取指定位置可以移动到的所有位置（包括当前位置）
    public List<int[]> getNeighbors(String[] grid, int row, int column, int maxJump) {
        List<int[]> neighbors = new ArrayList<int[]>();
        neighbors.add(new int[]{row, column}); // 添加当前位置
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} }; // 上、下、左、右四个方向
        for (int[] direction : directions) {
            int curRow = row, curColumn = column;
            for (int i = 1; i <= maxJump; i++) {
                curRow += direction[0];
                curColumn += direction[1];
                // 如果超出边界或遇到墙，就停止在这个方向上的移动
                if (curRow < 0 || curRow >= rows || curColumn < 0 || curColumn >= columns || grid[curRow].charAt(curColumn) == '#')
                    break;
                neighbors.add(new int[]{curRow, curColumn});
            }
        }
        return neighbors;
    }
}