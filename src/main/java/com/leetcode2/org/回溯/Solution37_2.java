package com.leetcode2.org.回溯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class Solution37_2 {
    Set<Character> setAll = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
    boolean solved = false; // 用来标记是否已经找到答案

    static class Point {
        int x, y;
        char val;
        int index;

        public Point(int x, int y, char val) {
            this.x = x;
            this.y = y;
            this.val = val;
            this.index = x / 3 * 3 + (y / 3);
        }
    }

    public void solveSudoku(char[][] board) {
        Point[][] allPoints = new Point[9][9];
        initData(board, allPoints);
        List<Point> emptyPoints = Arrays.stream(allPoints).flatMap(Arrays::stream).filter(p -> p.val == '0').toList();
        backTrack(emptyPoints, allPoints, 0, board);
    }

    void backTrack(List<Point> emptyPoints, Point[][] allPoints, int start, char[][] board) {
        if (solved) return; // 已经找到答案，停止回溯

        if (start == emptyPoints.size()) {
            emptyPoints.forEach(point -> board[point.x][point.y] = point.val);
            solved = true; // 标记已经找到答案
            return;
        }
        Point point = emptyPoints.get(start);
        Set<Character> filterSet = searchList(point.x, point.y, allPoints);
        for (char c : filterSet) {
            point.val = c;
            backTrack(emptyPoints, allPoints, start + 1, board);
            if (solved) return; // 已经找到答案，停止回溯
            point.val = '0'; // 回溯
        }
    }

    void initData(char[][] board, Point[][] allPoints) {
        int m = board.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                Point point;
                if (board[i][j] == '.') {
                    point = new Point(i, j, '0');
                } else {
                    point = new Point(i, j, board[i][j]);
                }
                allPoints[i][j] = point;
            }
        }
    }

    Set<Character> searchList(int i, int j, Point[][] allPoints) {
        int curIndex = i / 3 * 3 + (j / 3);
        List<Character> list = Arrays.stream(allPoints).flatMap(Arrays::stream)
                .filter(p -> (p.index == curIndex || p.x == i || p.y == j) && p.val != '0')
                .map(point -> point.val)
                .toList();
        Set<Character> copySet = new HashSet<>(setAll);
        list.forEach(copySet::remove);
        return copySet;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Solution37 solution37 = new Solution37();
        solution37.solveSudoku(board);

        // 打印结果
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
