package com.leetcode2.org.数组;

import java.util.*;

public class Solution871 {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            // 创建一个优先队列（最大堆）来存储加油站的油量
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            int fuel = startFuel; // 当前燃料量
            int count = 0; // 加油次数
            int prevPosition = 0; // 上一个加油站的位置

            // 在 stations 末尾添加目标点以方便计算
            stations = Arrays.copyOf(stations, stations.length + 1);
            stations[stations.length - 1] = new int[]{target, 0};

            for (int[] station : stations) {
                int position = station[0];
                int fuelAmount = station[1];
                // 计算从上一个加油站到当前加油站的距离
                int distance = position - prevPosition;
                // 消耗相应的燃料
                fuel -= distance;
                // 如果燃料不足以到达当前加油站，则需要加油
                while (fuel < 0 && !maxHeap.isEmpty()) {
                    // 从优先队列中取出油量最多的加油站
                    fuel += maxHeap.poll();
                    count++; // 加油次数加1
                }
                // 如果仍然无法到达当前加油站，则返回 -1
                if (fuel < 0) {
                    return -1;
                }
                // 到达当前加油站，添加其油量到优先队列
                maxHeap.offer(fuelAmount);
                // 更新上一个加油站的位置
                prevPosition = position;
            }

            return count; // 返回加油次数
        }

        public static void main(String[] args) {
            Solution871 solution = new Solution871();
            System.out.println(solution.minRefuelStops(100, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}})); // 输出: 2
            System.out.println(solution.minRefuelStops(100, 1, new int[][]{{10, 100}})); // 输出: -1
            System.out.println(solution.minRefuelStops(1, 1, new int[][]{})); // 输出: 0
        }
    }
