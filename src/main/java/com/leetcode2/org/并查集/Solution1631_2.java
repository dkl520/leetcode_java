package com.leetcode2.org.并查集;

import java.util.LinkedList;
import java.util.Queue;

public class Solution1631_2 {
        // 定义四个方向的移动偏移量
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int minimumEffortPath(int[][] heights) {
            int m = heights.length; // 地图的行数
            int n = heights[0].length; // 地图的列数

            // 设定二分搜索的初始边界，最小努力值为0，最大努力值根据题目给定范围设为999999
            int left = 0, right = 999999, ans = 0;

            // 使用二分搜索来找到最小的努力值
            while (left <= right) {
                int mid = (left + right) / 2; // 当前尝试的努力值

                // 使用广度优先搜索来检查是否存在一条路径，使得路径上任意两点的高度差都不超过mid
                Queue<int[]> queue = new LinkedList<>(); // 广度优先搜索的队列
                queue.offer(new int[]{0, 0}); // 从起点(0, 0)开始搜索
                boolean[] seen = new boolean[m * n]; // 标记数组，记录是否访问过某个位置
                seen[0] = true; // 标记起点为已访问

                // 广度优先搜索过程
                while (!queue.isEmpty()) {
                    int[] cell = queue.poll(); // 从队列中取出一个待处理的节点
                    int x = cell[0], y = cell[1]; // 获取当前节点的坐标

                    // 尝试向四个方向移动
                    for (int i = 0; i < 4; ++i) {
                        int nx = x + dirs[i][0]; // 计算下一个节点的x坐标
                        int ny = y + dirs[i][1]; // 计算下一个节点的y坐标

                        // 检查下一个节点是否在地图内、是否未被访问过，以及当前节点和下一个节点的高度差是否小于等于当前尝试的努力值
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                            queue.offer(new int[]{nx, ny}); // 将符合条件的下一个节点加入队列
                            seen[nx * n + ny] = true; // 标记为已访问
                        }
                    }
                }

                // 检查是否访问到了终点(m-1, n-1)
                if (seen[m * n - 1]) {
                    ans = mid; // 更新最小努力值为当前尝试的值
                    right = mid - 1; // 缩小搜索范围，尝试寻找更小的努力值
                } else {
                    left = mid + 1; // 增大搜索范围，因为当前努力值不足以到达终点
                }
            }

            // 返回找到的最小努力值
            return ans;
        }
    }