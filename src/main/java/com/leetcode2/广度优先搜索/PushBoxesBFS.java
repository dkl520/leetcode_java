package com.leetcode2.广度优先搜索;
import java.util.*;
public class PushBoxesBFS {

        private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        private int rows, cols;

        public int minPushBox(char[][] grid) {
            rows = grid.length;
            cols = grid[0].length;
            int[] player = null;
            List<int[]> boxes = new ArrayList<>();
            Set<String> targets = new HashSet<>();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 'S') player = new int[]{i, j};
                    else if (grid[i][j] == 'B') boxes.add(new int[]{i, j});
                    else if (grid[i][j] == 'P') targets.add(i + "," + j);
                }
            }

            Queue<State> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            queue.offer(new State(player[0], player[1], boxes, 0));

            while (!queue.isEmpty()) {
                State curr = queue.poll();
                if (isAllBoxesOnTarget(curr.boxes, targets)) {
                    return curr.pushes;
                }

                String key = getStateKey(curr);
                if (visited.contains(key)) continue;
                visited.add(key);

                for (int[] dir : DIRECTIONS) {
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
                        queue.offer(new State(npx, npy, newBoxes, curr.pushes + (pushed ? 1 : 0)));
                    }
                }
            }

            return -1;
        }

        private boolean isValid(char[][] grid, int x, int y) {
            return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] != '#';
        }

        private boolean isAllBoxesOnTarget(List<int[]> boxes, Set<String> targets) {
            for (int[] box : boxes) {
                if (!targets.contains(box[0] + "," + box[1])) {
                    return false;
                }
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

        private class State {
            int px, py, pushes;
            List<int[]> boxes;
            State(int px, int py, List<int[]> boxes, int pushes) {
                this.px = px; this.py = py; this.boxes = boxes; this.pushes = pushes;
            }
        }

        public static void main(String[] args) {
            char[][] grid = {
                    {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                    {'#', '.', '.', '.', '#', 'P', '.', '.', '.', 'P', '.', '#'},
                    {'#', '.', 'B', '.', '#', '.', '#', '#', '#', '.', '.', '#'},
                    {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                    {'#', '.', '#', '.', '#', 'B', '#', '#', '#', 'B', '.', '#'},
                    {'#', 'P', '.', '.', '#', '.', '.', '.', '#', '.', '.', '#'},
                    {'#', '.', '.', '.', '#', '.', 'S', '.', '#', '.', '.', '#'},
                    {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
            };

            PushBoxesBFS solution = new PushBoxesBFS();
            System.out.println("Minimum pushes required: " + solution.minPushBox(grid));
        }
    }

