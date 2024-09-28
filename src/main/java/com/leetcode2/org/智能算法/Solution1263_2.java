package com.leetcode2.org.智能算法;

import java.awt.Point;
import java.util.*;

public class Solution1263_2 {

    public int minPushBox(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        Map<Character, List<Point>> g = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                g.computeIfAbsent(grid[i][j], k -> new ArrayList<>()).add(new Point(i, j));
            }
        }

        Point player = g.get('S').get(0);
        Point box = g.get('B').get(0);
        Point target = g.get('T').get(0);
        Set<Point> floor = new HashSet<>();
        floor.add(player);
        floor.add(box);
        floor.add(target);
        for (Point p : g.get('.')) {
            floor.add(p);
        }

        PriorityQueue<State> alphaStarQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.f));
        alphaStarQueue.add(new State(heuristic(box, target) + 1, 1, player, box));

        Set<Pair> visited = new HashSet<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int time = 1;

        while (!alphaStarQueue.isEmpty()) {
            State curr = alphaStarQueue.poll();
            Point currPlayer = curr.player;
            Point currBox = curr.box;
            int steps = curr.steps;

            for (int[] direction : directions) {
                Point nextPlayer = new Point(currBox.x - direction[0], currBox.y - direction[1]);
                Point nextBox = new Point(currBox.x + direction[0], currBox.y + direction[1]);

                if (floor.contains(nextBox) && !visited.contains(new Pair(nextPlayer, currBox)) &&
                        bestFirst(currPlayer, nextPlayer, floor, currBox)) {
                    if (nextBox.equals(target)) {
                        return steps;
                    }
                    alphaStarQueue.add(new State(heuristic(nextBox, target) + steps + 1, steps + 1, currBox, nextBox));
                    time++;
                    visited.add(new Pair(nextPlayer, currBox));
                }
            }
        }

        return -1;
    }

    private int heuristic(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private boolean bestFirst(Point begin, Point end, Set<Point> floor, Point currBox) {
        PriorityQueue<PlayerState> playerQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.priority));
        playerQueue.add(new PlayerState(heuristic(begin, end), begin));

        Set<Point> legalFloor = new HashSet<>(floor);
        legalFloor.remove(currBox);

        while (!playerQueue.isEmpty()) {
            PlayerState curr = playerQueue.poll();
            Point currPlayer = curr.player;

            if (currPlayer.equals(end)) {
                return true;
            }

            for (int[] direction : new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                Point nextPlayer = new Point(currPlayer.x + direction[0], currPlayer.y + direction[1]);

                if (legalFloor.contains(nextPlayer)) {
                    playerQueue.add(new PlayerState(heuristic(nextPlayer, end), nextPlayer));
                    legalFloor.remove(nextPlayer);
                }
            }
        }

        return false;
    }

    class State {
        int f;
        int steps;
        Point player;
        Point box;

        State(int f, int steps, Point player, Point box) {
            this.f = f;
            this.steps = steps;
            this.player = player;
            this.box = box;
        }
    }

    class PlayerState {
        int priority;
        Point player;

        PlayerState(int priority, Point player) {
            this.priority = priority;
            this.player = player;
        }
    }

    class Pair {
        Point player;
        Point box;

        Pair(Point player, Point box) {
            this.player = player;
            this.box = box;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return player.equals(pair.player) && box.equals(pair.box);
        }

        @Override
        public int hashCode() {
            return Objects.hash(player, box);
        }
    }
}
