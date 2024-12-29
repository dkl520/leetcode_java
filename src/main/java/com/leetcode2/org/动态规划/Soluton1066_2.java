package com.leetcode2.org.动态规划;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Soluton1066_2 {

    private int findDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    public int assignBikes(int[][] workers, int[][] bikes) {
        Set<Integer> visitedBike = new HashSet<>();
        Map<String, Integer> memo = new HashMap<>();
        return dfs(workers, 0, bikes, visitedBike, memo);
    }

    int dfs(int[][] workers, int workerIndex, int[][] bikes, Set<Integer> visitedBike, Map<String, Integer> memo) {
        int min = Integer.MAX_VALUE;
        if (workerIndex == workers.length) return 0;
        String state = workerIndex + "-" + visitedBike;
        if (memo.containsKey(state)) {
            return memo.get(state); // 如果已计算，直接返回结果
        }

        for (int i = 0; i < bikes.length; i++) {
            if (visitedBike.contains(i)) {
                continue;
            }
            visitedBike.add(i);
            min = Math.min(min, findDistance(workers[workerIndex], bikes[i]) + dfs(workers, workerIndex + 1, bikes, visitedBike, memo));
            visitedBike.remove(i);
        }
        memo.put(state, min);
        return min;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int[][] workers = {
                {460, 458}, {596, 615}, {901, 893}, {456, 247}, {690, 492},
                {229, 149}, {74, 792}, {566, 205}, {660, 559}, {955, 855}
        };

        int[][] bikes = {
                {272, 92}, {316, 205}, {281, 371}, {938, 433}, {218, 310},
                {510, 853}, {365, 626}, {416, 168}, {365, 258}, {577, 936}
        };

        Soluton1066_2 sol = new Soluton1066_2();
        System.out.println(sol.assignBikes(workers, bikes));
        long endTime = System.currentTimeMillis();

        System.out.println((endTime - startTime) /1000 +"ms"   );
    }
}
