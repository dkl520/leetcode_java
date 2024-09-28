package com.leetcode2.org.图论;

import java.util.*;

class Solution2477 {
    public long minimumFuelCost(int[][] roads, int seats) {
        Map<Integer, Set<Integer>> edgeLine = new HashMap<>();
        int[] personList = new int[roads.length + 1];

        Queue<Integer> listbfs = new ArrayDeque <>();
        listbfs.add(0);
        while (!listbfs.isEmpty()) {
            int cur = listbfs.poll();
            if (edgeLine.containsKey(cur)) {
                continue;
            }
            List<Integer> nextList = Arrays.stream(roads).filter(road -> Arrays.stream(road).anyMatch(i -> i == cur))
                    .mapToInt(road -> road[0] == cur ? road[1] : road[0]).filter(value -> !edgeLine.containsKey(value))
                    .boxed().toList();

            edgeLine.computeIfAbsent(cur, k -> new HashSet<>()).addAll(nextList);
            listbfs.addAll(nextList);
        }
        explore(0, edgeLine, personList);
        Queue<Integer> countBFS = new ArrayDeque <>(edgeLine.get(0));
        int costs = 0;
        while (!countBFS.isEmpty()) {
            int cur = countBFS.poll();
            int personNum = personList[cur];
            if (personNum > seats) {
                costs += personNum / seats;
                costs += personNum % seats > 0 ? 1 : 0;
            } else {
                costs++;
            }
            countBFS.addAll(edgeLine.get(cur));
        }

        return costs;
    }

    void explore(int start, Map<Integer, Set<Integer>> edgeLine, int[] count) {
        Set<Integer> set = edgeLine.get(start);
        for (Integer i : set) {
            explore(i, edgeLine, count);
            count[start] += count[i];
        }
        count[start]++;
    }



    public static void main(String[] args) {
        int[][] roads = {
                {3, 1},
                {3, 2},
                {1, 0},
                {0, 4},
                {0, 5},
                {4, 6}
        };
        int seats = 2;
        System.out.println(new Solution2477().minimumFuelCost(roads, seats));

    }
}