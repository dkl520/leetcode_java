package com.leetcode2.广度优先搜索;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution1730 {
    static class Person {
        int x;
        int y;
        int manhattanDistance;
        int calccDistance;
        int steps;
        public Person(int x, int y, int manhattanDistance, int steps) {
            this.x = x;
            this.y = y;
            this.manhattanDistance = manhattanDistance;
            this.steps = steps;
            this.calccDistance = manhattanDistance + steps;
        }
    }
    public int getFood(char[][] grid) {
        int[] person = new int[2];
        int m = grid.length;
        int n = grid[0].length;
        List<int[]> foods = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '*') {
                    person[0] = i;
                    person[1] = j;
                }
                if (grid[i][j] == '#') {
                    foods.add(new int[]{i, j});
                }
            }
        }
        Queue<Person> queue = new PriorityQueue<>((a, b) -> a.calccDistance - b.calccDistance);
        queue.offer(new Person(person[0], person[1], manhattanDistance(person, foods), 0));
        boolean[][] visited = new boolean[m][n];
        visited[person[0]][person[1]] = true;
        int[] position = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            Person p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = p.x + position[i];
                int y = p.y + position[i + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 'X' && !visited[x][y]) {
                    if (grid[x][y] == '#') {
                        return p.steps + 1;
                    }
                    visited[x][y] = true;
                    queue.offer(new Person(x, y, manhattanDistance(new int[]{x, y}, foods), p.steps + 1));
                }
            }
        }
        return -1;
    }

    int manhattanDistance(int[] person, List<int[]> foods) {
        int minDistance = Integer.MAX_VALUE;
        for (int[] food : foods) {
            minDistance = Math.min(minDistance, Math.abs(person[0] - food[0]) + Math.abs(person[1] - food[1]));
        }
        return minDistance;
    }


    public static void main(String[] args) {
        char[][] grid = {
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', '*', 'O', 'X', 'O', '#', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'O', '#', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };
        Solution1730 solution1730 = new Solution1730();
        System.out.println(solution1730.getFood(grid));
    }
}
