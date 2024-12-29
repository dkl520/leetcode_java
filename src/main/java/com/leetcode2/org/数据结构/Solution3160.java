package com.leetcode2.org.数据结构;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution3160 {
    public int[] queryResults(int limit, int[][] queries) {
        int n = queries.length;
        int[] result = new int[n];

        Map<Integer, Integer> mapIndex = new HashMap<>();
        Map<Integer, Set<Integer>> mapColor = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int[] query = queries[i];
            int curColor = mapIndex.getOrDefault(query[0], 0);
            if (curColor != 0) {
                mapColor.get(curColor).remove(query[0]);
                if (mapColor.get(curColor).isEmpty()) {
                    mapColor.remove(curColor);
                }
            }

            mapIndex.put(query[0], query[1]);
            mapColor.computeIfAbsent(query[1], (k) -> new HashSet<>()).add(query[0]);
            result[i] = mapColor.size();
        }
        return result;
    }


}
