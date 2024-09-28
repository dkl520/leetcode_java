package com.leetcode2.org.数组;

public class Solution2708_2 {
    /**
     * 计算数组通过乘法操作能达到的最大强度值。
     * 强度值定义为通过数组中元素的乘法操作可以得到的最大值。
     *
     * @param nums 输入的整数数组
     * @return 返回能够得到的最大强度值
     */
    public long maxStrength(int[] nums) {
        // 初始化最大值为数组的第一个元素，最小值也为数组的第一个元素
        long max = nums[0], min = nums[0];

        // 遍历数组中的每个元素（从第二个元素开始）
        for (int i = 1; i < nums.length; i++) {
            // 保存当前的最大值，以便在更新最小值时使用
            long temp = max;

            // 更新最大值：考虑当前元素、当前最大值、当前最大值与当前元素的乘积、当前最小值与当前元素的乘积
            max = Math.max(
                    Math.max(max, nums[i]),
                    Math.max(max * nums[i], min * nums[i])
            );

            // 更新最小值：考虑当前元素、当前最小值、保存的最大值与当前元素的乘积、当前最小值与当前元素的乘积
            // 注意：这里使用保存的最大值temp，而不是当前的max，因为当前的max可能已经被更新
            min = Math.min(
                    Math.min(min, nums[i]),
                    Math.min(temp * nums[i], min * nums[i])
            );
        }

        // 返回计算得到的最大强度值
        return max;
    }
}