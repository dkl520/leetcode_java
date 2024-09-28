package com.leetcode2.org.回溯;

import java.util.ArrayList;

class leetcode79 {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        ArrayList<Integer[]> list = backtrack(word, board, visited);
        if (list.isEmpty()) {
            return false;
        }
        ArrayList<Integer[]> nextList = new ArrayList<>();
        boolean results = false;
        for (int i = 0; i < list.size(); i++) {
            Integer[] start = list.get(i);
            visited[start[0]][start[1]] = true;
            results = dfs(board, start, word, 1, visited);
            if (results) {
                return results;
            }
            visited[start[0]][start[1]] = false;
        }
        return false;
    }

    static ArrayList<Integer[]> backtrack(String word, char[][] board, boolean[][] visited) {
        ArrayList<Integer[]> list = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    list.add(new Integer[] { i, j });
                }
            }
        }
        return list;
    }

    static boolean dfs(char[][] board, Integer[] start, String word, int index, boolean[][] visited) {
        // visited[start[0]][start[1]] = true;
        int lth = word.length();
        if (index == lth) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        int[] x = { -1, 1, 0, 0 };
        int[] y = { 0, 0, -1, 1 };
        char c = word.charAt(index);
        ArrayList<Integer[]> nextList = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            int nextY = start[0] + y[i];
            int nextX = start[1] + x[i];

            if (nextY >= 0 && nextY < m && nextX >= 0 && nextX < n && board[nextY][nextX] == c && !visited[nextY][nextX]) {

                index++;
                visited[nextY][nextX] = true;
                boolean results = dfs(board, new Integer[] { nextY, nextX }, word, index, visited);
                if (results) {
                    return true;
                }
                index--;
                visited[nextY][nextX] = false;
            }
        }
        return false;

    }

}