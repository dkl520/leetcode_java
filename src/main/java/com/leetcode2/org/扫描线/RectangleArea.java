package com.leetcode2.org.扫描线;

import java.util.*;

class Solution {
    public int rectangleArea(int[][] rectangles) {
        // 定义一个常量MOD，用于后面的取模运算
        final int MOD = 1000000007;
        // 获取矩形的数量
        int n = rectangles.length;
        // 使用一个集合来存储所有矩形的上下边界
        Set<Integer> set = new HashSet<Integer>();
        for (int[] rect : rectangles) {
            set.add(rect[1]); // 添加下边界
            set.add(rect[3]); // 添加上边界
        }
        // 将集合转换为列表，并对边界进行排序
        List<Integer> hbound = new ArrayList<Integer>(set);
        Collections.sort(hbound);
        // 获取边界的数量
        int m = hbound.size();
        // 创建一个数组用于记录每个水平边界上的覆盖情况
        int[] seg = new int[m - 1];
        // 创建一个列表，用于存储所有的扫描线事件
        List<int[]> sweep = new ArrayList<int[]>();
        for (int i = 0; i < n; ++i) {
            // 添加矩形的左边界进入事件
            sweep.add(new int[]{rectangles[i][0], i, 1});
            // 添加矩形的右边界离开事件
            sweep.add(new int[]{rectangles[i][2], i, -1});
        }

        // 对扫描线事件进行排序，先按x坐标，再按进入/离开事件，最后按矩形索引排序
        Collections.sort(sweep, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });

        // 初始化答案变量
        long ans = 0;

        // 遍历所有的扫描线事件
        for (int i = 0; i < sweep.size(); ++i) {
            int j = i;
            // 处理所有在同一x坐标上的事件
            while (j + 1 < sweep.size() && sweep.get(i)[0] == sweep.get(j + 1)[0]) {
                ++j;
            }
            // 如果已经到了最后一个事件，则退出循环
            if (j + 1 == sweep.size()) {
                break;
            }
            // 对于当前x坐标上的所有矩形，更新其在每个水平边界上的覆盖情况
            for (int k = i; k <= j; ++k) {
                int[] arr = sweep.get(k);
                int idx = arr[1], diff = arr[2];
                int left = rectangles[idx][1], right = rectangles[idx][3];
                for (int x = 0; x < m - 1; ++x) {
                    if (left <= hbound.get(x) && hbound.get(x + 1) <= right) {
                        seg[x] += diff;
                    }
                }
            }

            // 计算当前x坐标区间内的覆盖面积
            int cover = 0;
            for (int k = 0; k < m - 1; ++k) {
                if (seg[k] > 0) {
                    cover += (hbound.get(k + 1) - hbound.get(k));
                }
            }
            // 累加覆盖面积到答案中，并乘以当前x坐标区间的长度
            ans += (long) cover * (sweep.get(j + 1)[0] - sweep.get(j)[0]);
            // 更新i的值，跳过已经处理过的事件
            i = j;
        }
        // 返回答案，并取模得到最终结果
        return (int) (ans % MOD);
    }
}


public class RectangleArea {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1
        int[][] rectangles1 = {{0, 0, 2, 2}, {1, 0, 2, 3}, {1, 0, 3, 1}};
        System.out.println("Test Case 1: " + solution.rectangleArea(rectangles1)); // Expected output: 6

        // Test Case 2
        int[][] rectangles2 = {{0, 0, 3, 3}, {2, 2, 5, 5}};
        System.out.println("Test Case 2: " + solution.rectangleArea(rectangles2)); // Expected output: 9

        // Test Case 3
        int[][] rectangles3 = {{0, 0, 1, 1}, {1, 0, 2, 1}, {0, 1, 2, 2}};
        System.out.println("Test Case 3: " + solution.rectangleArea(rectangles3)); // Expected output: 2

        // Test Case 4
        int[][] rectangles4 = {{0, 0, 1000000000, 1000000000}};
        System.out.println("Test Case 4: " + solution.rectangleArea(rectangles4)); // Expected output: 49
    }
}
