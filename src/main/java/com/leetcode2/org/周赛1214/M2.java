package com.leetcode2.org.周赛1214;

import java.util.*;

public class M2 {

    public static int solution(int[] arr) {
        // 处理边界情况：数组为空或只有一个元素时，返回0
        if (arr == null || arr.length <= 1)
            return 0;

        int n = arr.length;
        // 创建一个映射，用于存储每个值对应的所有索引位置
        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
        // 遍历数组，建立值到索引的映射关系
        for (int i = 0; i < n; i++) {
            valueToIndices.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        // 创建队列用于BFS（广度优先搜索）
        Queue<Integer> queue = new LinkedList<>();
        // 创建访问标记数组，防止重复访问
        boolean[] visited = new boolean[n];
        // 将起始位置（索引0）加入队列并标记为已访问
        queue.offer(0);
        visited[0] = true;
        // 记录移动的步数
        int steps = 0;

        // BFS主循环
        while (!queue.isEmpty()) {
            // 获取当前层的节点数
            int size = queue.size();
            // 处理当前层的所有节点
            for (int i = 0; i < size; i++) {
                // 获取当前位置
                int curr = queue.poll();
                // 如果到达终点，返回步数
                if (curr == n - 1) {
                    return steps;
                }

                // 获取与当前位置值相同的所有索引
                List<Integer> sameValues = valueToIndices.get(arr[curr]);

                // 尝试向右移动一步
                if (curr + 1 < n && !visited[curr + 1]) {
                    queue.offer(curr + 1);
                    visited[curr + 1] = true;
                }
                // 尝试向左移动一步
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    queue.offer(curr - 1);
                    visited[curr - 1] = true;
                }

                // 尝试跳转到具有相同值的其他位置
                if (sameValues != null) {
                    for (int next : sameValues) {
                        if (!visited[next]) {
                            queue.offer(next);
                            visited[next] = true;
                        }
                    }
                    // 清空已处理的相同值列表，避免重复处理
                    sameValues.clear();
                }
            }
            // 当前层处理完毕，步数加1
            steps++;
        }

        // 如果无法到达终点，返回-1
        return -1;
    }

    public static void main(String[] args) {
        // 测试用例
        System.out.println(solution(new int[]{1, 11, 5, 5, 5, 5, 46, 5, -25, 46}) == 5);
        System.out.println(solution(new int[]{9}) == 0);
        System.out.println(solution(new int[]{9, 8, 1, 8, 1, 8, 1, 9}) == 1);
    }
}