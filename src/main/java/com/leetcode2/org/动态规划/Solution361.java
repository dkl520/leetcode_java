package com.leetcode2.org.动态规划;

public class Solution361 {
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int countMax = 0;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                   int currentResult= searchDirect(grid, i, j);
                    dp[i][j] = currentResult;
                    countMax = Math.max(countMax, currentResult);
                }
            }
        }
        return countMax;
    }

    public int searchDirect(char[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int i = row;
        int j = col;
        while (j >= 0 && grid[i][j] != 'W') {
            if (grid[i][j] == 'E') {
                count++;
            }
            j--;
        }
        j = col;
        while (j < n && grid[i][j] != 'W') {
            if (grid[i][j] == 'E') {
                count++;
            }
            j++;
        }
        j=col;
        while (i >= 0 && grid[i][j] != 'W') {
            if (grid[i][j] == 'E') {
                count++;
            }
            i--;
        }
        i = row;
        while (i < m && grid[i][j] != 'W') {
            if (grid[i][j] == 'E') {
                count++;
            }
            i++;
        }

        return count;
    }

    public static void main(String[] args) {
        char[][] input = {
                 {'0', 'E', '0', '0'},
                 {'E', '0', 'W', 'E'},
                 {'0', 'E', '0', '0'}
        };

        Solution361 solution361 = new Solution361();
        System.out.println(solution361.maxKilledEnemies(input));

    }
}
