package com.leetcode2.org.数组;

import java.util.Arrays; // 导入Arrays类
import java.util.Comparator; // 导入Comparator类

public class Solution452_2 {
    public int findMinArrowShots(int[][] p) {
        // 按照气球的左端点进行排序
        Arrays.sort(p, Comparator.comparingInt(a -> a[0]));

        int res = 1; // 至少需要一支箭
        int l = p[0][0], r = p[0][1]; // 当前箭的爆炸范围

        // 从第二个气球开始遍历
        for (int i = 1; i < p.length; i++) {
            // 如果当前气球的左端点小于等于当前箭的右端点
            if (p[i][0] <= r) { // 当前气球可以被当前箭击破
                // 更新当前箭的覆盖范围
                r = Math.min(r, p[i][1]); // 更新右端点
            } else {
                // 当前箭无法击破这个气球
                res++; // 使用新的一支箭
// 更新新箭的爆炸范围
                r = p[i][1];
            }
        }
        return res; // 返回箭的数量
    }
}
