package com.leetcode2.广度优先搜索;

import java.util.*;

import java.util.*;

public class Solution505_BFS {
    // 使用BFS算法找到从起点到终点的最短路径
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;  // 获取迷宫的行数
        int cols = maze[0].length;  // 获取迷宫的列数
        int[][] distances = new int[rows][cols];  // 用于记录从起点到每个点的最短距离

        // 初始化距离数组，所有点的初始距离设置为无穷大
        for (int[] row : distances) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distances[start[0]][start[1]] = 0;  // 起点到自己的距离为0

        Queue<int[]> queue = new LinkedList<>();  // 队列，用于BFS
        queue.add(new int[]{start[0], start[1]});  // 将起点加入队列

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // 四个可能的移动方向：上、下、左、右

        while (!queue.isEmpty()) {
            int[] current = queue.poll();  // 取出队列中的当前点
            int x = current[0];
            int y = current[1];
            // 遍历四个可能的移动方向
            for (int[] direction : directions) {
                int nx = x;
                int ny = y;
                int count = 0;  // 记录从当前点到下一个停止点的距离
                // 模拟球滚动，直到碰到墙壁或者边界
                while (nx + direction[0] >= 0 && nx + direction[0] < rows &&
                        ny + direction[1] >= 0 && ny + direction[1] < cols &&
                        maze[nx + direction[0]][ny + direction[1]] == 0) {
                    nx += direction[0];
                    ny += direction[1];
                    count++;
                }
                // 如果新计算的距离比已记录的距离短，更新距离并将新的点加入队列
                if (distances[x][y] + count < distances[nx][ny]) {
                    distances[nx][ny] = distances[x][y] + count;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        // 返回到达终点的最短距离，如果无法到达，则返回-1
        return distances[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distances[destination[0]][destination[1]];
    }

    // 主函数，用于测试上述算法
    public static void main(String[] args) {
        Solution505_BFS solution = new Solution505_BFS();
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] start = {0, 4};  // 起点
        int[] destination = {4, 4};  // 终点

        // 打印从起点到终点的最短距离
        System.out.println(solution.shortestDistance(maze, start, destination));  // 输出: 12
    }
}
