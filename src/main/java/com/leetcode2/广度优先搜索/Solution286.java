package com.leetcode2.广度优先搜索;

import java.util.*;

class Solution286 {
    public void wallsAndGates(int[][] rooms) {
        // 获取房间的行数和列数
        int m = rooms.length;
        int n = rooms[0].length;

        // 使用队列来进行广度优先搜索
        Deque<int[]> q = new LinkedList<>();

        // 初始化队列，将所有值为0的单元格（即门）加入队列
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == 0) {
                    q.offer(new int[] {i, j}); // 将门的坐标加入队列
                }
            }
        }

        // d用于记录从门出发的步数
        int d = 0;

        // 定义四个方向的偏移量，用于遍历相邻的单元格
        // 注意：dirs数组是交错存储的，即dirs[0]和dirs[1]为一组，dirs[2]和dirs[3]为另一组
        int[] dirs = {-1, 0, 1, 0, -1}; // -1, 0 表示向上；1, 0 表示向下；0, -1 表示向左；0, 1 表示向右

        // 当队列不为空时，继续搜索
        while (!q.isEmpty()) {
            // 每进入一层循环，表示从门出发的步数增加1
            ++d;

            // 使用循环遍历当前层的所有节点，这样可以避免在遍历过程中修改队列大小导致的问题
            for (int i = q.size(); i > 0; --i) {
                int[] p = q.poll(); // 取出队列中的一个节点

                // 遍历该节点的四个相邻节点
                for (int j = 0; j < 4; ++j) {
                    int x = p[0] + dirs[j]; // 计算相邻节点的行索引
                    int y = p[1] + dirs[j + 1]; // 计算相邻节点的列索引

                    // 检查相邻节点是否有效（在网格范围内且值为无穷大，即尚未被访问过）
                    if (x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] == Integer.MAX_VALUE) {
                        // 更新相邻节点的值为当前步数d，并将其加入队列以便后续搜索
                        rooms[x][y] = d;
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
    }
}