package com.leetcode2.广度优先搜索;

import java.util.*;
import java.util.concurrent.*;

public class PushBoxesAStarComplex2 {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows, cols;

    public int minPushBox(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int[] player = null;
        List<int[]> boxes = new ArrayList<>();
        List<int[]> targets = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'S') player = new int[]{i, j};
                else if (grid[i][j] == 'B') boxes.add(new int[]{i, j});
                else if (grid[i][j] == 'P') targets.add(new int[]{i, j});
                else if (grid[i][j] == 'T') {
                    boxes.add(new int[]{i, j});
                    targets.add(new int[]{i, j});
                }
            }
        }

        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.f - b.f);
        Set<String> visited = ConcurrentHashMap.newKeySet();
        State initialState = new State(player[0], player[1], boxes, 0);
        initialState.f = initialState.g + heuristic(boxes, targets);
        pq.offer(initialState);

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            if (isAllBoxesOnTarget(curr.boxes, targets)) {
                executor.shutdown();
                return curr.g;
            }

            String key = getStateKey(curr);
            if (visited.contains(key) || isDeadlock(curr.boxes, grid)) continue;
            visited.add(key);

            List<Callable<Void>> tasks = new ArrayList<>();

            for (int[] dir : DIRECTIONS) {
                tasks.add(() -> {
                    int npx = curr.px + dir[0], npy = curr.py + dir[1];
                    if (isValid(grid, npx, npy)) {
                        boolean pushed = false;
                        List<int[]> newBoxes = new ArrayList<>(curr.boxes);
                        for (int i = 0; i < newBoxes.size(); i++) {
                            int[] box = newBoxes.get(i);
                            if (npx == box[0] && npy == box[1]) {
                                int nbx = box[0] + dir[0], nby = box[1] + dir[1];
                                if (isValid(grid, nbx, nby) && !hasBox(newBoxes, nbx, nby)) {
                                    newBoxes.set(i, new int[]{nbx, nby});
                                    pushed = true;
                                    break;
                                }
                            }
                        }
                        State newState = new State(npx, npy, newBoxes, curr.g + (pushed ? 1 : 0));
                        newState.f = newState.g + heuristic(newBoxes, targets);
                        synchronized (pq) {
                            pq.offer(newState);
                        }
                    }
                    return null;
                });
            }

            try {
                executor.invokeAll(tasks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return -1;
    }

    private boolean isValid(char[][] grid, int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] != '#';
    }

    private boolean isAllBoxesOnTarget(List<int[]> boxes, List<int[]> targets) {
        for (int[] box : boxes) {
            boolean onTarget = false;
            for (int[] target : targets) {
                if (box[0] == target[0] && box[1] == target[1]) {
                    onTarget = true;
                    break;
                }
            }
            if (!onTarget) return false;
        }
        return true;
    }

    private boolean hasBox(List<int[]> boxes, int x, int y) {
        for (int[] box : boxes) {
            if (box[0] == x && box[1] == y) {
                return true;
            }
        }
        return false;
    }

    private String getStateKey(State state) {
        StringBuilder sb = new StringBuilder();
        sb.append(state.px).append(",").append(state.py);
        for (int[] box : state.boxes) {
            sb.append(",").append(box[0]).append(",").append(box[1]);
        }
        return sb.toString();
    }

    private int heuristic(List<int[]> boxes, List<int[]> targets) {
        int totalDistance = 0;
        for (int[] box : boxes) {
            int minDistance = Integer.MAX_VALUE;
            for (int[] target : targets) {
                int distance = Math.abs(box[0] - target[0]) + Math.abs(box[1] - target[1]);
                minDistance = Math.min(minDistance, distance);
            }
            totalDistance += minDistance;
        }
        return totalDistance;
    }

    private boolean isDeadlock(List<int[]> boxes, char[][] grid) {
        // 添加简单的死锁检测，比如检测箱子是否被推到角落
        for (int[] box : boxes) {
            int x = box[0], y = box[1];
            if (grid[x][y] != 'P' && grid[x][y] != 'T') {
                boolean cornered = (grid[x - 1][y] == '#' && grid[x][y - 1] == '#') ||
                        (grid[x - 1][y] == '#' && grid[x][y + 1] == '#') ||
                        (grid[x + 1][y] == '#' && grid[x][y - 1] == '#') ||
                        (grid[x + 1][y] == '#' && grid[x][y + 1] == '#');
                if (cornered) return true;
            }
        }
        return false;
    }

    private class State {
        int px, py, g, f;
        List<int[]> boxes;

        State(int px, int py, List<int[]> boxes, int g) {
            this.px = px;
            this.py = py;
            this.boxes = boxes;
            this.g = g;
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', 'B', '#', '#', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', ' ', ' ', '#', ' ', ' ', ' ', 'B', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', ' ', '#', ' ', '#', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', ' ', 'B', ' ', 'B', ' ', ' ', 'B', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', 'B', '#', ' ', ' ', '#'},
                {'#', '#', '#', ' ', ' ', 'B', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#'},
                {'#', '#', '#', 'S', 'P', 'P', 'B', ' ', 'T', 'T', 'P', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', 'P', 'P', 'P', 'P', 'P', 'P', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        };

        long start = System.currentTimeMillis();
        PushBoxesAStarComplex solution = new PushBoxesAStarComplex();
        System.out.println("Minimum pushes required: " + solution.minPushBox(grid));
        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start) + "ms");
    }
}


