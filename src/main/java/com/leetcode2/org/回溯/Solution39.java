package com.leetcode2.org.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution39 {


    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int start = 0;
        int[] filteredCandidates = Arrays.stream(candidates).filter(v -> v <= target).toArray();
        recrusion(filteredCandidates, target, res, new ArrayList<Integer>(), target, start);
        return res;
    }

    public void recrusion(int[] candidates, int target, List<List<Integer>> res, List<Integer> panelOne, int originT, int start) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(panelOne));
            target = originT;
            return;

        }

        for (int i = start; i < candidates.length; i++) {
            int number = candidates[i];
            panelOne.add(number);
            int nextTarget = target - number;
            recrusion(candidates, nextTarget, res, panelOne, originT, i);
            panelOne.remove(panelOne.size() - 1);

        }


    }

    public static void main(String[] args) {
        int[] candidates = {7,3,2};
        int target = 18;
        List<List<Integer>> result = new Solution39().combinationSum(candidates, target);
        System.out.println(result);

    }
}
