package com.leetcode2.广度优先搜索;

import java.util.*;


public class Solution505_AStar {
    // 使用A*算法找到从起点到终点的最短路径
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;  // 迷宫的行数
        int cols = maze[0].length;  // 迷宫的列数
        int[][] distances = new int[rows][cols];  // 用于记录从起点到每个点的最短距离
        // 初始化距离数组，所有点的初始距离设置为无穷大
        for (int[] row : distances) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distances[start[0]][start[1]] = 0;  // 起点到自己的距离为0

        // 优先队列，按当前距离加上启发式估计距离从小到大排序
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) ->
                a[2] + heuristic(a, destination) - (b[2] + heuristic(b, destination))
        );
        heap.add(new int[]{start[0], start[1], 0});  // 将起点加入优先队列

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // 四个可能的移动方向：上、下、左、右

        while (!heap.isEmpty()) {
            int[] current = heap.poll();  // 取出当前估计距离最小的点
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            // 如果当前点是目的地，返回当前的距离
            if (x == destination[0] && y == destination[1]) {
                return dist;
            }

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

                // 如果新计算的距离比已记录的距离短，更新距离并将新的点加入优先队列
                if (dist + count < distances[nx][ny]) {
                    distances[nx][ny] = dist + count;
                    heap.add(new int[]{nx, ny, dist + count});
                }
            }
        }
        return -1;  // 如果无法到达目的地，返回-1
    }

    // 计算启发式函数，这里使用曼哈顿距离作为启发式估计
    private int heuristic(int[] point, int[] destination) {
        return Math.abs(point[0] - destination[0]) + Math.abs(point[1] - destination[1]);
    }

    // 主函数，用于测试上述算法
    public static void main(String[] args) {
        Solution505_AStar solution = new Solution505_AStar();
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



//在A算法中，一旦找到一个解（即到达目标节点），就可以确定该解是最短路径。这是因为A算法结合了实际距离和启发式估计距离，
// 保证了在每一步中扩展的节点都是当前最优的。
//
//为什么A*算法能保证找到最短路径？
//A*算法的优先队列是按照以下准则进行排序的：
//
//f(n) = g(n) + h(n)
//g(n) 是从起点到节点 n 的实际距离。
//h(n) 是从节点 n 到终点的启发式估计距离。
//关键点：
//A*算法保证在优先队列中总是优先处理 f(n) 最小的节点。
//一旦终点节点被处理（即从优先队列中取出），那么从起点到终点的路径一定是当前最短的，因为所有其他路径的 f(n) 都大于或等于当前路径。

