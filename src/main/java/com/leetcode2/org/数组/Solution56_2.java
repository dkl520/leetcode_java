package com.leetcode2.org.数组;

import java.util.BitSet;

public class Solution56_2 {
    public int[][] merge(int[][] intervals) {
        // 创建一个 BitSet 用于存储区间
        BitSet bitSet = new BitSet();
        // 记录最大的区间值，用于控制 BitSet 范围
        int max = 0;

        // 遍历所有的区间
        for (int[] interval : intervals) {
            // 将区间的右边界 * 2 并加 1，表示为 BitSet 的结束位置
            int temp = interval[1] * 2 + 1;
            // 将区间 [start * 2, end * 2 + 1) 标记为 true
            bitSet.set(interval[0] * 2, temp, true);
            // 更新最大区间值
            max = Math.max(temp, max);
        }

        // 初始化索引和合并后区间的计数
        int index = 0, count = 0;

        // 遍历 BitSet 直到最大值
        while (index < max) {
            // 找到从 index 开始的下一个 true（即区间的开始）
            int start = bitSet.nextSetBit(index);
            // 找到从 start 开始的下一个 false（即区间的结束）
            int end = bitSet.nextClearBit(start);

            // 创建一个合并后的区间，注意需要将 start 和 end 从 *2 换算回原始区间值
            int[] item = {start / 2, (end - 1) / 2};
            // 将合并后的区间存入 intervals 数组，计数加一
            intervals[count++] = item;

            // 更新索引为 end，继续查找下一个区间
            index = end;
        }

        // 创建一个新的二维数组，用于存储最终的合并结果
        int[][] ret = new int[count][2];
        // 将合并后的区间复制到返回的结果数组中
        for (int i = 0; i < count; i++) {
            ret[i] = intervals[i];
        }

        // 返回合并后的区间数组
        return ret;
    }
}
