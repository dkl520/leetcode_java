package com.leetcode2.org.dynamicPrograming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode673 {

    public int findNumberOfLIS(int[] nums) {
        // 如果数组为空，则最长递增子序列的长度为0，且没有这样的子序列
        if (nums.length == 0) {
            return 0;
        }
        // dp数组用于存储以每个元素为结尾的最长递增子序列的长度
        int[] dp = new int[nums.length];
        // coutsPrev列表用于存储可以形成当前dp[i]值的所有前一个索引位置
        List<Integer> coutsPrev = new ArrayList<>();
        // 初始化dp和count数组
        dp[0] = 1;
        int[] count = new int[nums.length];
        count[0] = 1; // 只有一个元素的子序列有一个
        // 初始化最长递增子序列的长度为1
        int maxans = 1;
        // 遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 初始化dp[i]和count[i]
            dp[i] = 1;
            count[i] = 1; // 每个元素自身都能构成一个长度为1的子序列
            // 遍历当前元素之前的所有元素
            for (int j = 0; j < i; j++) {
                // 如果当前元素大于之前的某个元素
                if (nums[i] > nums[j]) {
                    // 如果将当前元素添加到以nums[j]为结尾的子序列中可以得到更长的子序列
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1; // 更新dp[i]
                        coutsPrev.clear(); // 清空之前的记录
                        coutsPrev.add(j); // 记录能形成当前dp[i]值的前一个索引位置
                    } else if (dp[j] + 1 == dp[i]) {
                        // 如果长度相同，则添加多个可能的前一个索引位置
                        coutsPrev.add(j);
                    }
                }
            }
            // 更新count[i]，即长度为dp[i]的子序列有多少个
            if (coutsPrev.size() > 1) {
                // 如果有多个前一个位置可以形成当前长度，则组合它们
                count[i] = 0;
                for (Integer position : coutsPrev) {
                    count[i] += count[position];
                }
            } else if (coutsPrev.size() == 1) {
                // 如果只有一个前一个位置可以形成当前长度，则直接继承那个位置的count值
                count[i] = count[coutsPrev.get(0)];
            }
            coutsPrev.clear(); // 清空列表以准备下一次循环
        }
        // 找到dp数组中的最大值及其索引
        int[] maxIndexArr = findMaxValueIndices(dp);
        // 使用这些索引从count数组中获取对应的计数值，并累加起来得到结果
        return Arrays.stream(maxIndexArr).reduce(0, (acc, v) -> acc + count[v]);
//        return Arrays.stream(maxIndexArr).map(v -> count[v]).sum();
    }

    public static int[] findMaxValueIndices(int[] arr) {
        if (arr == null || arr.length == 0) {
            // 处理空数组或无效输入的情况
            return new int[]{-1};
        }
        int maxValue = arr[0];
        List<Integer> maxIndices = new ArrayList<>();
        maxIndices.add(0);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
                maxIndices.clear(); // 重置最大值索引列表
                maxIndices.add(i);
            } else if (arr[i] == maxValue) {
                maxIndices.add(i);
            }
        }
        return maxIndices.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3};
        Leetcode673 Leetcode673 = new Leetcode673();
        System.out.println(Leetcode673.findNumberOfLIS(nums));


    }

}
