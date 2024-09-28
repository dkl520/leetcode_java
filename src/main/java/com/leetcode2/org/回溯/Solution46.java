package com.leetcode2.org.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution46 {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> results = new ArrayList<>();
        List<Integer> listNum = Arrays.stream(nums).boxed().toList();
//        List<Integer> listNum = new ArrayList<>(Arrays.asList(nums));
        List<Integer> listOneCell = new ArrayList<>();
        recrusionPermute(results, listNum, listOneCell, nums.length);
        return results;
    }

    public void recrusionPermute(List<List<Integer>> res, List<Integer> nums, List<Integer> listOneCell, int l) {
        if (listOneCell.size() == l) {
            res.add(new ArrayList<>(listOneCell));
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            listOneCell.add(nums.get(i));
            List<Integer> newNums = new ArrayList<>(nums);
            newNums.remove(nums.get(i));
            recrusionPermute(res, newNums, listOneCell, l);
            listOneCell.remove(listOneCell.size() - 1);
        }
    }


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        int[] res = new int[10];
        for (int i = 0; i < res.length; i++) {
            res[i] = i;
        }
        Solution46 solution46 = new Solution46();
        List<List<Integer>> allList = solution46.permute(res);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time taken: " + duration + " milliseconds");
//        System.out.println(allPermutations);
//        System.out.println(allList);

    }

}
