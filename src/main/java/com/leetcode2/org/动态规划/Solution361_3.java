package com.leetcode2.org.动态规划;

public class Solution361_3 {
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length; // 获取网格的行数
        int n = grid[0].length; // 获取网格的列数
        int maxEnemies = 0; // 初始化最大敌人数量
        int[][] up = new int[m][n]; // 记录从上到下的敌人数量
        int[][] down = new int[m][n]; // 记录从下到上的敌人数量
        int[][] left = new int[m][n]; // 记录从左到右的敌人数量
        int[][] right = new int[m][n]; // 记录从右到左的敌人数量

        // 预处理每个方向的敌人数量，从上到下和从左到右
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'E') { // 如果当前位置是敌人
                    up[i][j] = (i > 0 ? up[i - 1][j] : 0) + 1; // 记录上方敌人数量
                    left[i][j] = (j > 0 ? left[i][j - 1] : 0) + 1; // 记录左方敌人数量
                } else if (grid[i][j] == 'W') { // 如果当前位置是墙
                    up[i][j] = 0; // 重置上方敌人数量
                    left[i][j] = 0; // 重置左方敌人数量
                } else { // 如果当前位置是空地
                    up[i][j] = i > 0 ? up[i - 1][j] : 0; // 保持上方敌人数量
                    left[i][j] = j > 0 ? left[i][j - 1] : 0; // 保持左方敌人数量
                }
            }
        }

        // 预处理每个方向的敌人数量，从下到上和从右到左
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 'E') { // 如果当前位置是敌人
                    down[i][j] = (i < m - 1 ? down[i + 1][j] : 0) + 1; // 记录下方敌人数量
                    right[i][j] = (j < n - 1 ? right[i][j + 1] : 0) + 1; // 记录右方敌人数量
                } else if (grid[i][j] == 'W') { // 如果当前位置是墙
                    down[i][j] = 0; // 重置下方敌人数量
                    right[i][j] = 0; // 重置右方敌人数量
                } else { // 如果当前位置是空地
                    down[i][j] = i < m - 1 ? down[i + 1][j] : 0; // 保持下方敌人数量
                    right[i][j] = j < n - 1 ? right[i][j + 1] : 0; // 保持右方敌人数量
                }
            }
        }

        // 计算每个空位的最大敌人数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') { // 如果当前位置是空地
                    int currentEnemies = up[i][j] + down[i][j] + left[i][j] + right[i][j]; // 计算当前位置的总敌人数量
                    maxEnemies = Math.max(maxEnemies, currentEnemies); // 更新最大敌人数量
                }
            }
        }

        return maxEnemies; // 返回最大敌人数量
    }

    public static void main(String[] args) {
        char[][] input = {
                {'0', 'E', '0', '0'},
                {'E', '0', 'W', 'E'},
                {'0', 'E', '0', '0'}
        };
        Solution361_3 solution361_3 = new Solution361_3();
        System.out.println(solution361_3.maxKilledEnemies(input)); // 输出: 3
    }
}
