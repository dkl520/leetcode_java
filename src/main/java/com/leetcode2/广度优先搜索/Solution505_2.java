package com.leetcode2.广度优先搜索;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution505_2 {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;  // 获取迷宫的行数
        int cols = maze[0].length;  // 获取迷宫的列数
        int[][] distances = new int[rows][cols];  // 用来记录从起点到各点的最短距离
        for (int[] row : distances) {
            Arrays.fill(row, Integer.MAX_VALUE);  // 初始化所有距离为无穷大
        }
        distances[start[0]][start[1]] = 0;  // 起点到自己的距离为0
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);  // 优先队列，按距离从小到大排序
        heap.add(new int[]{start[0], start[1], 0});  // 将起点加入优先队列
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // 四个方向：上、下、左、右

        while (!heap.isEmpty()) {
            int[] current = heap.poll();  // 取出当前距离最短的点
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            if (x == destination[0] && y == destination[1]) {
                return dist;  // 如果当前点是目的地，返回当前距离
            }
            for (int[] direction : directions) {
                int nx = x;
                int ny = y;
                int count = 0;  // 用来计算滚动到下一个停止点的距离
                // 模拟球滚动，直到碰到墙或者边界
                while (nx + direction[0] >= 0 && nx + direction[0] < rows && ny + direction[1] >= 0 && ny + direction[1] < cols
                        && maze[nx + direction[0]][ny + direction[1]] == 0) {
                    nx += direction[0];
                    ny += direction[1];
                    count++;
                }
                // 如果新的距离比记录的距离短，更新距离并将新的点加入优先队列
                if (dist + count < distances[nx][ny]) {
                    distances[nx][ny] = dist + count;
                    heap.add(new int[]{nx, ny, dist + count});
                }
            }
        }
        return -1;  // 如果无法到达目的地，返回-1
    }

    public static void main(String[] args) {
        Solution505_2 solution = new Solution505_2();
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] start = {0, 4};
        int[] destination = {4, 4};

        System.out.println(solution.shortestDistance(maze, start, destination));  // 输出: 12
    }
}

//Dijkstra算法通过贪心策略确保每次扩展距离起点最近的节点，从而找到从起点到所有其他节点的最短路径。
//虽然Dijkstra算法可以找到最短路径，但是它不涉及任何启发式信息，而A*算法则结合了实际距离和启发式估计距离。

//贪心选择性：Dijkstra算法每次选择距离起点最近的节点进行扩展。这种贪心策略确保了在当前阶段每个节点被标记为最短路径时，
//已知的最短路径距离就是从起点到该节点的最短路径距离。
//因为每次扩展时都选择当前距离起点最短的节点，所以首次标记的节点的路径长度一定是从起点到该节点的最短路径。
//
//路径长度逐步优化：在Dijkstra算法执行过程中，每个节点的路径长度会随着其邻居节点的扩展而逐步优化。当一个节点被标记为最短路径后，
//其邻居节点的路径长度可能会通过该节点更新为更短的路径。这种逐步优化确保了在每个节点被处理时，已知的最短路径是当前已发现的最优路径。