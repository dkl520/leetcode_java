package com.leetcode2.org.哈希表;

import java.util.*;
public class SolutionSwap {
        // 辅助函数：生成交换两位数字后的所有可能结果
        private static Set<String> generateAllSwaps(String number) {
            Set<String> result = new HashSet<>();
            result.add(number); // 原数字也要加入结果
            char[] arr = number.toCharArray();

            // 两层循环，生成两位交换的结果
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    // 交换位置 i 和 j
                    char temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;

                    result.add(new String(arr));

                    // 恢复原数组
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }

            return result;
        }

        public static int solution(int[] numbers) {
            int count = 0;
            Map<String, Integer> map = new HashMap<>();

            // 对每个数字，生成所有两位交换的可能结果，并将其存入map中
            for (int num : numbers) {

                String strNum = String.valueOf(num);
                Set<String> allSwaps = generateAllSwaps(strNum);

                for (String swap : allSwaps) {
                    // 如果这个排列已经存在于 map 中，那么它和当前数字形成一个合法对
                    count += map.getOrDefault(swap, 0);
                }

                // 将当前数字的原始字符串加入 map
                map.put(strNum, map.getOrDefault(strNum, 0) + 1);
            }

            return count;
        }

        public static void main(String[] args) {
            int[] numbers1 = {1, 23, 156, 1650, 651, 165, 32};
//            int[] numbers2 = {123, 321, 123};

            System.out.println("Output for numbers1: " + solution(numbers1)); // 输出应该是 3
//            System.out.println("Output for numbers2: " + solution(numbers2)); // 输出应该是 3
        }
    }
