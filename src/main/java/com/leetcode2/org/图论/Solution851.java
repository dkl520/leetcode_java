package com.leetcode2.org.图论;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution851 {
    List<List<Integer>> dist;

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        List<List<Integer>> list = new ArrayList<>();
        dist = new ArrayList<>();
        for (int i = 0; i < quiet.length; i++) {
            list.add(new ArrayList<>());
            dist.add(new ArrayList<>());
        }
        int[] result = new int[quiet.length];
        for (int[] rank : richer) {
            list.get(rank[1]).add(rank[0]);
        }

        for (int i = 0; i < quiet.length; i++) {
            dfs(i, list, quiet);
            result[i] = dist.get(i).get(0);
        }
        return result;
    }

    List<Integer> dfs(int start, List<List<Integer>> list, int[] quiet) {
        if (!dist.get(start).isEmpty()) {
            return dist.get(start);
        }
        if (list.get(start).isEmpty()) {
            dist.get(start).add(start);
            return dist.get(start);
        }
        for (int neighbor : list.get(start)) {
            List<Integer> curPerson = dist.get(start);
            curPerson.addAll(dfs(neighbor, list, quiet));
        }
        dist.get(start).add(start);
        int minQuiet = Integer.MAX_VALUE;
        int index = -1;
        for (int j = 0; j < dist.get(start).size(); j++) {
            int curPerson = dist.get(start).get(j);
            if (minQuiet > quiet[curPerson]) {
                minQuiet = quiet[curPerson];
                index = curPerson;
            }
        }
        dist.get(start).clear();
        dist.get(start).add(index);
        return dist.get(start);
    }

    public static void main(String[] args) {
        Solution851 solution = new Solution851();
        int[][] richer = {
                {1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}
        };
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
        System.out.println(Arrays.toString(
                solution.loudAndRich(richer, quiet)
        ));
    }

}