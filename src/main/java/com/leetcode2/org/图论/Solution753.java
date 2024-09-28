package com.leetcode2.org.图论;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.List;

public class Solution753 {

    public String crackSafe(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();

        trackback(n, k, combinations, new ArrayList<Integer>());


        return "aa";
    }

    void trackback(int n, int k, List<List<Integer>> combinations, List<Integer> style) {
        if (style.size() == n) {
            combinations.add(new ArrayList<>(style));
            return;
        }
        for (int i = 0; i < k; i++) {
            style.add(i);
            trackback(n, k, combinations, style);
            style.remove(style.size() - 1);
        }


    }

    public static void main(String[] args) {
        int n = 2;
        int k = 2;
        Solution753 solution = new Solution753();
        System.out.println(solution.crackSafe(2, 2));
    }

}
