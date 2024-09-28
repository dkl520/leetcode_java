package com.leetcode2.org.数组;

import java.util.*;

public class Solution2848 {

    public int numberOfPoints(List<List<Integer>> nums) {

//        nums.sort((a, b) -> a.get(0) < b.get(0) ? -1 : 1);
        nums.sort(Comparator.comparingInt(a -> a.get(0)));


//        nums.sort(Comparator.comparingInt((List<Integer> a) -> a.get(0)).reversed());

        List<Integer> cur = nums.get(0);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i < nums.size(); i++) {
            List<Integer> next = nums.get(i);
            if (next.get(0) <= cur.get(1)) {
                cur.set(1, Math.max(cur.get(1), next.get(1)));
            } else {
                res.add(cur);
                cur = next;
            }
        }
        res.add(cur);
        int result = 0;
        for (List<Integer> l : res) {
            result += l.get(1) - l.get(0) + 1;
        }
        return result;

    }


}
