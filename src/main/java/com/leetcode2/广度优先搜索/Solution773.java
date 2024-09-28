package com.leetcode2.广度优先搜索;

import java.util.*;

class Grid {
    int[][] board;
    int steps;
    int estimatedTotalCost;

    public Grid(int[][] board, int steps, int estimatedTotalCost) {
        this.board = board;
        this.steps = steps;
//        当前步数加上启发式估计排序  这样最先的才有可能是最优解。！！！！
        this.estimatedTotalCost = estimatedTotalCost;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(board);
    }
}

public class Solution773 {
    public int slidingPuzzle(int[][] board) {
        int[][] target = new int[][]{
                {1, 2, 3},
                {4, 5, 0}
        };
        return Astar(board, target);
    }

    int Astar(int[][] board, int[][] target) {
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, 1},
                {0, -1}
        };
        int m = board.length;
        int n = board[0].length;

        PriorityQueue<Grid> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.estimatedTotalCost));
        Grid initGrid = new Grid(board, 0, heuristic(board, target));
        pq.offer(initGrid);
        Set<String> visited = new HashSet<>();
        visited.add(Arrays.deepToString(board));

        while (!pq.isEmpty()) {
            Grid curr = pq.poll();
            if (Arrays.deepEquals(curr.board, target)) {
                return curr.steps;
            }

            int[] currO = findIndex(curr.board, 0);

            for (int[] dir : directions) {
                int row = currO[0] + dir[0];
                int col = currO[1] + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n) {
                    int[][] newBoard = deepCopy(curr.board);
                    swap(newBoard, row, col, currO[0], currO[1]);
                    String newBoardStr = Arrays.deepToString(newBoard);
                    if (!visited.contains(newBoardStr)) {
                        visited.add(newBoardStr);
                        int newSteps = curr.steps + 1;
                        int newHeuristic = heuristic(newBoard, target);
                        pq.offer(new Grid(newBoard, newSteps, newSteps + newHeuristic));
                    }
                }
            }
        }
        return -1;
    }

    public static int[][] deepCopy(int[][] original) {
        return Arrays.stream(original)
                .map(int[]::clone)
                .toArray(int[][]::new);
    }

    public static void swap(int[][] array, int x1, int y1, int x2, int y2) {
        int temp = array[x1][y1];
        array[x1][y1] = array[x2][y2];
        array[x2][y2] = temp;
    }

    private int heuristic(int[][] board, int[][] target) {
        int distance = 0;
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target[i].length; j++) {
                int curNum = target[i][j];
                int[] currP = findIndex(board, curNum);
                distance += Math.abs(i - currP[0]) + Math.abs(j - currP[1]);
            }
        }
        return distance;
    }

    private int[] findIndex(int[][] board, int value) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == value) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Solution773 solution773 = new Solution773();
        int[][] board = new int[][]{
                {3, 2, 4},
                {1, 5, 0},
        };
        System.out.println(solution773.slidingPuzzle(board));
    }
}
