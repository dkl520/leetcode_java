package com.leetcode2.广度优先搜索;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1111 {


    // Complete the countConnections function below.
    static int countConnections(List<List<Integer>> matrix) {
        if (matrix == null || matrix.isEmpty()) return 0;
        int m = matrix.size();
        int n = matrix.get(0).size();
        int connection = 0;
        int[][] directions = {
                {0, 1},
                {1, 0},
                {1, 1},
                {1, -1}
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) == 1) {
                    for (int[] dir : directions) {
                        int newii = i + dir[0];
                        int newjj = j + dir[1];
                        if (newii >= 0 && newii < m && newjj > 0 && newjj < n && matrix.get(newii).get(newjj) == 1) {
                            connection++;
                        }
                    }
                }
            }
        }
        return connection;

    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<List<Integer>>();
        int[][] arr = {
                {1, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 0, 0, 1},

        };
         for (int i = 0; i < arr.length; i++) {
             List<Integer> list= new ArrayList<>();
             for (int j = 0; j < arr[i].length; j++) {
                 list.add(arr[i][j]);
             }
             matrix.add(list);
         }

        Solution1111 sol = new Solution1111();

        System.out.println(sol.countConnections(matrix));

    }
}
