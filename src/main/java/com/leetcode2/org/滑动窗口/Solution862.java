package com.leetcode2.org.滑动窗口;

import com.leetcode2.Main;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution862 {

    /**
     * 找到数组中最短的子数组，使得该子数组的元素和至少为k。
     * 如果不存在这样的子数组，则返回-1。
     *
     * @param nums 输入的整数数组
     * @param k    子数组元素和的最小值
     * @return 最短满足条件的子数组的长度，如果不存在则返回-1
     */
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        // 创建一个长度为n+1的前缀和数组，preSumArr[0]初始化为0，便于后续计算
        long[] preSumArr = new long[n + 1];
        for (int i = 0; i < n; i++) {
            // 计算前缀和
            preSumArr[i + 1] = preSumArr[i] + nums[i];
        }
        // 初始化结果为数组长度+1，便于后续比较（因为最短子数组长度不可能大于数组长度）
        int res = n + 1;

        // 使用双端队列存储前缀和的索引，保持队列的单调性（递减）
        Deque<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i <= n; i++) {
            long curSum = preSumArr[i]; // 当前的前缀和
            // 移除队列头部的索引，直到当前前缀和与队列头部索引对应的前缀和之差小于k
            while (!queue.isEmpty() && curSum - preSumArr[queue.peekFirst()] >= k) {
                res = Math.min(res, i - queue.pollFirst()); // 更新最短子数组长度
            }
            while (!queue.isEmpty() && preSumArr[queue.peekLast()] >= curSum) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        // 如果最短子数组长度仍然是n+1，则表示不存在满足条件的子数组
        return res < n + 1 ? res : -1;
    }
//    使用前缀和的差来计算子数组的和；
//    使用一种数据结构，将当前前缀和i与最前面的前缀和j作差，如果满足>=k的条件，那么j在之后就可以不用看了。
//            【因为即使后面也有满足条件的，长度也会更长，所以需要将j从前面弹出】；
//    第2步完成了之后，当前的i也要放入数据结构，那么如果数据结构中有前缀和j比前缀和i大，j也可以不用看了。
//            【因为即使后面有满足条件的，与i作差肯定也满足条件，并且长度更短，所以需要将大于等于i的从后面弹出】。
//    前面后面都要弹出，压入的元素满足单调性，所以使用单调队列。

    public static void main(String[] args) {
        int[] nums = {84, -37, 32, 40, 95};
        System.out.println(new Solution862().shortestSubarray(nums, 167));
    }
}