package com.leetcode2.org.大厂;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CodeSignal_3_Matrix {

    static class Ball {
        int x, y;
        List<int[]> dirs;

        public Ball(int x, int y, List<int[]> dirs) {
            this.x = x;
            this.y = y;
            this.dirs = dirs;
        }
    }

    static class Score {
        int score;
        int start;

        public Score(int start, int score) {
            this.start = start;
            this.score = score;
        }
    }

    int[] calcmatrixBoucing(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            int pos = matrix[i][0];
            result.add(boucing(matrix, i));
        }


        return result.stream().mapToInt(i -> i).toArray();
    }

    int boucing(int[][] matrix, int row) {
//        int [][] dirs= new int [][] {
//                {-1,1},
//                {1,1},
//                {-1,-1},
//                {1,-1}
//        };
//        for (int i = 0; i < dirs.length; i++) {
//
//
//        }

        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<Ball> queue = new LinkedList<>();
        List<int[]> list = new ArrayList<>();

        Ball ball;
        if (row == 0) {
            list.add(new int[]{1, 1});
            ball = new Ball(row, 0, list);
        } else if (row == matrix.length - 1) {
            list.add(new int[]{-1, 1});
            ball = new Ball(row, 0, list);
        } else {
            list.add(new int[]{-1, 1});
            list.add(new int[]{1, 1});
            ball = new Ball(row, 0, list);
        }


        queue.add(ball);
        int score = matrix[ball.x][ball.y];
        visited[ball.x][ball.y] = true;
        while (!queue.isEmpty()) {
            Ball startB = queue.poll();
            List<int[]> dirs = startB.dirs;
            for (int[] dir : dirs) {
                int nextX = dir[0] + startB.x;
                int nextY = dir[1] + startB.y;
                if (nextX >= 0 && nextX < matrix.length && nextY >= 0 && nextY < matrix[0].length && !visited[nextX][nextY]) {


                }
            }


        }
        return score;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 4, 3, 9},
                {3, 6, 4, 7},
                {9, 3, 2, 5},
                {4, 1, 3, 3}
        };

        CodeSignal_3_Matrix codeSignal_3_Matrix = new CodeSignal_3_Matrix();
        codeSignal_3_Matrix.calcmatrixBoucing(matrix);
    }
}
