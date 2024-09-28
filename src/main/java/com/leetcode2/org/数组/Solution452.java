package com.leetcode2.org.数组;

import java.util.Arrays;

public class Solution452 {

    public int findMinArrowShots(int[][] points) {
        // 如果没有气球，则不需要箭
        if (points.length == 0) return 0;
        // 按气球的右边界进行排序
        Arrays.sort(points, (p1, p2) -> p1[1] < p2[1] ? -1 : 1);
        int res = 1; // 初始化箭的数量
        int pre = points[0][1]; // 记录第一个气球的右边界
        // 从第二个气球开始遍历
        for (int i = 1; i < points.length; i++) {
            // 如果当前气球的左边界大于之前气球的右边界
            if (points[i][0] > pre) {
                res++; // 需要新增一支箭
                pre = points[i][1]; // 更新右边界
            }
        }
        return res; // 返回箭的数量
    }
    public static void main(String[] args) {

    }
}
