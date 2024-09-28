package com.leetcode2.org.高级搜索;

import java.util.*;

public class FeatureSpaceSearch {
    // 定义位置类，包含x坐标、y坐标和权重
    static class Position {
        int x, y;
        int weight;

        Position(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    // 定义单元格类，包含x坐标和y坐标，并重写equals和hashCode方法
    static class Cell {
        int x, y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Cell cell = (Cell) obj;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // 定义四个方向，分别为上下左右
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int MAZE_SIZE = 5;  // 迷宫大小
    private static final char WALL = '#';    // 墙壁字符
    private static final char PATH = '.';    // 路径字符
    private static final char START = 'S';   // 起点字符
    private static final char END = 'E';     // 终点字符

    public static void main(String[] args) {
        // 定义一个5x5的迷宫
        char[][] maze = {
                {START, PATH, WALL, WALL, WALL},
                {PATH, PATH, WALL, PATH, WALL},
                {WALL, PATH, WALL, PATH, WALL},
                {WALL, PATH, PATH, PATH, WALL},
                {WALL, WALL, WALL, END, WALL}
        };

        // 找到起点位置
        Position start = findStart(maze);
        if (start == null) {
            System.out.println("No start position found!");  // 如果未找到起点，输出提示信息
            return;
        }

        // 使用FESS算法搜索路径
        if (fessSearch(maze, start)) {
            System.out.println("Path found!");  // 如果找到路径，输出路径找到的信息
        } else {
            System.out.println("No path found.");  // 如果未找到路径，输出未找到路径的信息
        }
    }

    // 找到迷宫中的起点位置
    private static Position findStart(char[][] maze) {
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                if (maze[i][j] == START) {
                    return new Position(i, j, 0);  // 返回起点位置，并初始化权重为0
                }
            }
        }
        return null;  // 如果未找到起点，返回null
    }

    // 使用FESS算法搜索路径
    private static boolean fessSearch(char[][] maze, Position start) {
        // 优先队列，用于存储待扩展的节点，按照权重排序
        PriorityQueue<Position> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));
        // 特征空间，用于存储已经访问过的节点
        Map<Cell, Position> featureSpace = new HashMap<>();
        pq.add(start);  // 将起点加入优先队列
        featureSpace.put(new Cell(start.x, start.y), start);  // 将起点加入特征空间

        while (!pq.isEmpty()) {
            Position current = pq.poll();  // 取出权重最小的节点
            if (maze[current.x][current.y] == END) {
                return true;  // 如果当前节点是终点，返回true，表示找到路径
            }

            // 遍历四个方向
            for (int[] direction : DIRECTIONS) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];
                if (isValid(maze, newX, newY)) {  // 检查新位置是否合法
                    Position newPosition = new Position(newX, newY, current.weight + 1);  // 创建新位置，并更新权重
                    Cell newCell = new Cell(newX, newY);
                    // 如果新位置未被访问过或找到更小权重的路径
                    if (!featureSpace.containsKey(newCell) || featureSpace.get(newCell).weight > newPosition.weight) {
                        featureSpace.put(newCell, newPosition);  // 更新特征空间
                        pq.add(newPosition);  // 将新位置加入优先队列
                    }
                }
            }
        }
        return false;  // 如果队列为空，返回false，表示未找到路径
    }

    // 检查位置是否合法
    private static boolean isValid(char[][] maze, int x, int y) {
        return x >= 0 && x < MAZE_SIZE && y >= 0 && y < MAZE_SIZE && maze[x][y] != WALL;
    }
}
