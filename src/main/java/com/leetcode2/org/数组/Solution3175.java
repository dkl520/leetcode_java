package com.leetcode2.org.数组;

import java.util.*;

public class Solution3175 {
    public int findWinningPlayer(int[] skills, int k) {
        if (skills.length == 0) return 0;
        if (k>= skills.length ){
            return Arrays.stream(skills)
                    .boxed()
                    .mapToInt(Integer::intValue)
                    .reduce((i, j) -> skills[i] > skills[j] ? i : j)
                    .orElse(-1); // 返回 -1 表示数组为空
        }
        List<Integer> rows = new ArrayList<Integer>();


        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < skills.length; i++) {
            rows.add(skills[i]);
            map.put(skills[i], i);
        }

        int count = k;
        int max = rows.get(0);
        int oldNum = max;
        while (count > 0) {
            if (rows.get(0) < rows.get(1)) {
                max = rows.get(1);
                rows.add(rows.get(0));
                rows.remove(0);
            } else {
                max = rows.get(0);
                rows.add(rows.get(1));
                rows.remove(1);

            }
            if (max == oldNum) {
                count--;
            } else {
                count = k-1;
            }
            oldNum = max;
        }
        return  map.get(max);

    }

    public static void main(String[] args) {
        int[] skills = {4, 2, 6, 3, 9};
        int k = 2;
        System.out.println(new Solution3175().findWinningPlayer(skills, k));


    }


}
