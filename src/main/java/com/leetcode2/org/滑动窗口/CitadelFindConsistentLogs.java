package com.leetcode2.org.滑动窗口;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CitadelFindConsistentLogs {
    static int findConsistLog(int[] userEvent) {
        // 统计每个用户的出现频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int userId : userEvent) {
            frequencyMap.merge(userId, 1, Integer::sum);
        }
        // 找出最小频率
       Integer  minFrequency = frequencyMap.values().stream()
                .min((a, b) -> a - b)
                .orElse(0);
        String a="aaa";
        String b="bbb";
//        a.compareTo(b);

        Queue<Integer> window = new LinkedList<>();
        int maxLength = 0;
        for (int userId : userEvent) {
            window.offer(userId);
            frequencyMap.put(userId, frequencyMap.getOrDefault(userId, 0) + 1);

            // 如果当前 userId 的频率超过最小频率，则收缩窗口
            while (frequencyMap.get(userId) > minFrequency && !window.isEmpty()) {
                int removedId = window.poll();
                frequencyMap.put(removedId, frequencyMap.get(removedId) - 1);
                if (frequencyMap.get(removedId) == 0) {
                    frequencyMap.remove(removedId);
                }
            }

            // 只有当所有元素的频率都等于最小频率时，窗口才有效
            if (frequencyMap.get(userId) == minFrequency) {
                maxLength = Math.max(maxLength, window.size());
            }
        }


        return maxLength == 0 ? -1 : maxLength;
    }


    static int findConsistLog2(int[] userEvent) {
        int minF = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int userId : userEvent) {
            map.compute(userId, (k, v) -> v == null ? 1 : v + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() < minF) {
                minF = entry.getValue();
            }
        }
        map.clear();
        Queue<Integer> queue = new LinkedList<Integer>();
        int result = 0;
        for (int userId : userEvent) {
            queue.offer(userId);
            map.compute(userId, (k, v) -> v == null ? 1 : v + 1);
            if (map.get(userId) == minF) {
                result = Math.max(result, queue.size());
            } else if (map.get(userId) > minF) {
                while (!queue.isEmpty()) {
                    if (queue.peek() == userId) {
                        int removeId = queue.poll();
                        map.put(removeId, map.get(removeId) - 1);
                        result = Math.max(result, queue.size());
                        break;
                    }
                    int removeId = queue.poll();
                    map.put(removeId, map.get(removeId) - 1);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] userEvent = new int[]{1, 2, 1, 3, 4, 2, 4, 3, 3, 4};
        System.out.println(CitadelFindConsistentLogs.findConsistLog2(userEvent));
    }


}
