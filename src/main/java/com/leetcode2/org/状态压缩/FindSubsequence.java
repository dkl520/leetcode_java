package com.leetcode2.org.状态压缩;

import java.util.ArrayList;
import java.util.List;

public class FindSubsequence {
    List<List<Integer>> find(int[] list) {
        int n = list.length;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 1; i < (1 << n); i++) {
            List<Integer> subSequence = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subSequence.add(list[j]);
                }
            }
            ans.add(subSequence);
        }
        return ans;
    }


    public static void main(String[] args) {

        int[] list = new int[25];
        for (int i = 0; i < list.length; i++) {
            list[i] = i;
        }
        long start = System.currentTimeMillis();


        FindSubsequence findSubsequence = new FindSubsequence();
        System.out.println(
                findSubsequence.find(list)
        );

        long end = System.currentTimeMillis();
        System.out.println("位算法时间：");
        System.out.println((end - start) / 100 + "ms");
    }
}
