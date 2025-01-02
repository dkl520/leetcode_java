package com.leetcode2.org.图论;

import java.util.*;

public class Solution3377 {

    // 预处理质数集合
    private static Set<Integer> primes = new HashSet<>();
    static {
        boolean[] isPrime = new boolean[10000];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        // 使用埃拉托色尼筛法标记质数
        for (int i = 2; i < 5000; i++) {
            if (isPrime[i]) {
                for (int j = i * 2; j < 10000; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        // 将质数添加到集合中
        for (int i = 2; i < 10000; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }

    /**
     * 计算从 n 变为 m 的最小操作次数
     * @param n 起始数
     * @param m 目标数
     * @return 最小操作次数，如果无法变为 m 则返回 -1
     */
    public int minOperations(int n, int m) {
        // 如果 n 或 m 是质数，返回 -1
        if (primes.contains(n) || primes.contains(m)) {
            return -1;
        }

        // 构建可能的增量列表
        int len = String.valueOf(n).length();
        List<Integer> increments = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            increments.add((int)Math.pow(10, i));
        }

        // 使用 Map 存储状态
        Map<Integer, Integer> current = new HashMap<>();
        current.put(n, n);
        Map<Integer, Integer> allStates = new HashMap<>();
        allStates.put(n, n);

        // 广度优先搜索
        while (!current.isEmpty()) {
            Map<Integer, Integer> next = new HashMap<>();

            for (Map.Entry<Integer, Integer> entry : current.entrySet()) {
                int num = entry.getKey();
                int value = entry.getValue();

                for (int inc : increments) {
                    int digit = (num / inc) % 10;

                    // 尝试减少
                    if (digit > 0) {
                        int newNum = num - inc;
                        if (String.valueOf(newNum).length() == len &&
                            !primes.contains(newNum) &&
                            (!allStates.containsKey(newNum) || value + newNum < allStates.get(newNum))) {
                            next.put(newNum, value + newNum);
                            allStates.put(newNum, value + newNum);
                        }
                    }

                    // 尝试增加
                    if (digit < 9) {
                        int newNum = num + inc;
                        if (String.valueOf(newNum).length() == len &&
                            !primes.contains(newNum) &&
                            (!allStates.containsKey(newNum) || value + newNum < allStates.get(newNum))) {
                            next.put(newNum, value + newNum);
                            allStates.put(newNum, value + newNum);
                        }
                    }
                }
            }
            current = next;
        }

        return allStates.getOrDefault(m, -1);
    }
}