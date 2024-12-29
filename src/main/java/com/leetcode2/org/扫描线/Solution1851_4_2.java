package com.leetcode2.org.扫描线;

import java.util.*;

public class Solution1851_4_2 {
    public static int[] minInterval(int[][] intervals, int[] queries) {
        Map<Integer, List<int[]>> dic = new TreeMap<>();
        int[] ans = new int[queries.length];

        // 构建事件映射
        for (int[] interval : intervals) {
            dic.computeIfAbsent(interval[0], k -> new ArrayList<>()).add(new int[]{0, interval[1] - interval[0] + 1});
            dic.computeIfAbsent(interval[1] , k -> new ArrayList<>()).add(new int[]{2, interval[1] - interval[0] + 1});
        }
        for (int i = 0; i < queries.length; i++) {
            dic.computeIfAbsent(queries[i], k -> new ArrayList<>()).add(new int[]{1, i});
        }

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        dic.values().forEach(list -> {
            list.sort(Comparator.comparingInt(a -> a[0])); // 按事件类型排序
            for (int[] event : list) {
                if (event[0] == 0) { // 插入区间
                    treeMap.put(event[1], treeMap.getOrDefault(event[1], 0) + 1);
                } else if (event[0] == 1) { // 查询最小值
                    if (event[1] == 5) {
                        System.out.println(111);
                    }
                    ans[event[1]] = treeMap.isEmpty() ? -1 : treeMap.firstKey();
                } else { // 删除区间
                    int count = treeMap.get(event[1]);
                    if (count <= 1) {
                        treeMap.remove(event[1]);
                    } else {
                        treeMap.put(event[1], count - 1);
                    }
                }
            }
        });

        return ans;
    }

    public static void main(String[] args) {
        Solution1851_4_2 solution = new Solution1851_4_2();
        int[][] intervals = {{1, 4}, {2, 4}, {3, 6}, {4, 4}};
        int[] queries = {2, 3, 4, 5};


        System.out.println(Arrays.toString(solution.minInterval(intervals, queries))); // 输出结果
    }

}
