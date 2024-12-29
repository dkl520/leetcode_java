package com.leetcode2.org.数组;
import java.util.Arrays;
public class Solution3394 {
    /**
     * 检查是否可以通过至少三次水平或垂直切割将所有矩形分割开
     *
     * @param n 矩形的数量
     * @param rectangles 矩形的数组，每个矩形由四个整数 [a, b, c, d] 表示：
     *                   - 左下角坐标为 (a, b)
     *                   - 右上角坐标为 (c, d)
     * @return 如果可以分割开，返回 true；否则返回 false
     */
    boolean checkValidCuts(int n, int[][] rectangles) {
        int m = rectangles.length; // 获取矩形的数量
        int[][] a = new int[m][2]; // 用于存储矩形的水平区间 [a, c]
        int[][] b = new int[m][2]; // 用于存储矩形的垂直区间 [b, d]
        // 遍历每个矩形，将其左右边界和上下边界分别存储在 `a` 和 `b` 中
        for (int i = 0; i < m; i++) {
            int[] rect = rectangles[i]; // 获取当前矩形的坐标
            a[i][0] = rect[0]; // 水平区间的左端点 a
            a[i][1] = rect[2]; // 水平区间的右端点 c
            b[i][0] = rect[1]; // 垂直区间的下端点 b
            b[i][1] = rect[3]; // 垂直区间的上端点 d
        }
        // 检查是否存在至少三条水平切割线或垂直切割线
        return check(a) || check(b);
    }
    /**
     * 检查给定的区间是否可以通过至少三次完整切割
     *
     * @param intervals 一个区间数组，表示多个区间的左右边界
     * @return 如果可以通过至少三次完整切割，返回 true；否则返回 false
     */
    private boolean check(int[][] intervals) {
        // 按照区间的左端点进行从小到大的排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int cnt = 0; // 记录完整切割的次数
        int maxR = 0; // 记录当前覆盖区间的最大右端点
        // 遍历所有区间
        for (int[] interval : intervals) {
            if (interval[0] >= maxR) { // 如果当前区间的左端点大于等于最大右端点，表示新切割开始
                cnt++; // 切割次数增加
            }
            // 更新最大右端点，扩展当前覆盖的区间范围
            maxR = Math.max(maxR, interval[1]);
        }
        // 如果切割次数大于等于 3，返回 true
        return cnt >= 3;
    }
}
