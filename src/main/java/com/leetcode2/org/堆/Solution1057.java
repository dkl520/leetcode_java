package com.leetcode2.org.堆;

import java.util.*;

public class Solution1057 {
    static record Pair(int workerId, int[] workerPos, int bikeId, int[] bikePos, int DIS) {
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        Queue<Pair> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.DIS == o2.DIS) {
                if (o1.workerId == o2.workerId) {
                    return o1.bikeId - o2.bikeId;
                }
                return o1.workerId - o2.workerId;
            }
            return o1.DIS - o2.DIS;
        });

        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                Pair pair = new Pair(i, workers[i], j, bikes[j], calcDis(workers[i], bikes[j]));
                queue.offer(pair);
            }
        }
        int count = 0;
        int[] ans = new int[workers.length];
        Set<Integer> worksIds = new HashSet<>();
        Set<Integer> bikesIds = new HashSet<>();
        while (!queue.isEmpty() && count < workers.length) {
            Pair pair = queue.poll();
            if (worksIds.contains(pair.workerId) || bikesIds.contains(pair.bikeId) ) {
                continue;
            }
            count++;
            ans[pair.workerId] =pair.bikeId  ;
            worksIds.add(pair.workerId);
            bikesIds.add(pair.bikeId);
        }
        return  ans;


    }

    int calcDis(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    public static void main(String[] args) {
        int[][] workers = {{0, 0}, {1, 1}, {2, 0}};
        int[][] bikes = {{1, 0}, {2, 2}, {2, 1}};

        Solution1057 solution1057 = new Solution1057();
        int[] result = solution1057.assignBikes(workers, bikes);
        System.out.println(Arrays.toString(result));

    }
}
