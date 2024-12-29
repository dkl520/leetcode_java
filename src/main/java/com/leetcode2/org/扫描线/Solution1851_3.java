package com.leetcode2.org.扫描线;

import java.util.*;

public class Solution1851_3 {

    public int[] minInterval(int[][] intervals, int[] queries) {
        // 创建一个列表来存储所有的点（包括区间的左右边界）
        List<int[]> points = new ArrayList<>();
        // 创建一个优先队列（最小堆），根据高度排序
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 初始化堆，添加一个虚拟的高度为0的区间
        heap.offer(new int[]{0, Integer.MAX_VALUE});

        // 创建两个列表来存储位置和最大高度
        List<Integer> pos = new ArrayList<>();
        List<Integer> maxh = new ArrayList<>();
        pos.add(0);
        maxh.add(0);

        // 遍历所有的区间
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1] + 1; // r是排他的
            int h = 100_000_000 - (r - l); // 预处理高度
            points.add(new int[]{l, -h, r}); // 添加左边界
            points.add(new int[]{r, 0, Integer.MAX_VALUE}); // 添加右边界（高度为0）
        }

        // 对所有的点进行排序，首先按位置排序，然后按高度排序
        Collections.sort(points, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // 遍历所有的点
        for (int[] point : points) {
            int l = point[0], h = point[1], r = point[2];

            // 移除堆中所有右边界小于当前左边界的区间
            while (!heap.isEmpty() && l >= heap.peek()[1]) {
                heap.poll();
            }

            // 如果当前点是左边界，添加到堆中
            if (h < 0) {
                heap.offer(new int[]{h, r});
            }

            // 如果当前最大高度发生变化，更新位置和最大高度
            if (maxh.get(maxh.size() - 1) != -heap.peek()[0]) {
                pos.add(l);
                maxh.add(-heap.peek()[0]);
            }
        }

        // 处理查询
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            int index = upperBound(pos, q) - 1;
            int tmp = 100_000_000 - maxh.get(index);
            res[i] = tmp < 100_000_000 ? tmp : -1;
        }

        return res;
    }

    // 二分查找，找到第一个大于target的位置
    private int upperBound(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        Solution1851_3 solution = new Solution1851_3();
        int[][] intervals = {{1, 4}, {2, 4}, {3, 6}, {4, 4}};
        int[] queries = {2, 3, 4, 5};
        System.out.println(Arrays.toString(solution.minInterval(intervals, queries))); // 输出结果
    }
}

/*

这段代码实现了一个解决包含每个查询的最小区间问题的算法，使用了扫描线算法和有序集合。以下是详细的解释：
        初始化和预处理：
        创建一个列表 points 来存储所有的点（包括区间的左右边界）。
        创建一个优先队列 heap（最小堆），根据高度排序。初始化堆，添加一个虚拟的高度为0的区间。
        创建两个列表 pos 和 maxh 来存储位置和最大高度。初始位置为0，高度为0。
        遍历区间：
        遍历所有的区间，将每个区间的左边界和右边界（右边界+1，因为右边界是排他的）添加到 points 列表中。
        预处理高度 h 为 100_000_000 - (r - l)，这样可以确保高度是负数，便于在堆中排序。
        排序点：
        对所有的点进行排序，首先按位置排序，然后按高度排序。这样可以确保在处理每个点时，先处理左边界，再处理右边界。
        处理点：
        遍历所有的点，对于每个点：
        移除堆中所有右边界小于当前左边界的区间。
        如果当前点是左边界，将其添加到堆中。
        如果当前最大高度发生变化，更新位置和最大高度。
        处理查询：
        初始化结果数组 res，长度为查询数组的长度。
        遍历每个查询，使用二分查找找到第一个大于查询位置的位置索引，然后获取对应的最大高度，计算结果并存储在 res 中。
        返回结果：
        返回结果数组 res。*/
