package com.leetcode2.org.扫描线;

import java.util.*;

public class Solution218_2 {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeSet<Integer> xLine = new TreeSet<>();
        int n = buildings.length;
        List<int[]> buildingsList = new ArrayList<>();
        Arrays.sort(buildings, Comparator.comparingInt(b -> b[0]));
        Map<Integer, List<int[]>> memo = new HashMap<>();
        for (int[] building : buildings) {
            xLine.add(building[0]);
            xLine.add(building[1]);
            memo.computeIfAbsent(building[0], List -> new ArrayList<>()).add(new int[]{0, building[2]});
            memo.computeIfAbsent(building[1], List -> new ArrayList<>()).add(new int[]{1, building[2]});
        }
        TreeMap<Integer, Integer> maps = new TreeMap<>();
        List<int[]> result = new ArrayList<>();

        for (int x : xLine) {
            List<int[]> list = memo.get(x);
            list.sort((a, b) -> {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    if (a[0] == 0) {
                        return b[1] - a[1];
                    }
                    return a[1] - b[1];

                }
            });

            for (int[] node : list) {
                if (node[0] == 0) {
                    Integer higherKey = maps.ceilingKey(node[1]);
                    if (higherKey == null) {
                        result.add(new int[]{x, node[1]});
                    }
                    maps.put(node[1], maps.getOrDefault(node[1], 0) + 1);
                } else {
                    if (maps.get(node[1]) == 1) {
                        maps.remove(node[1]);
                    } else {
                        maps.put(node[1], maps.get(node[1]) - 1);
                    }
                    if (maps.isEmpty()) {
                        result.add(new int[]{x, 0});
                    } else {
                        Integer higherKey = maps.ceilingKey(node[1]);
                        if (higherKey == null) {
                            result.add(new int[]{x, maps.lastKey()});
                        }
                    }
                }
            }
        }
        return result.stream().map(arr -> Arrays.stream(arr).boxed().toList()).toList();
    }

}
