package com.leetcode2.org.回溯;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import org.apache.commons.collections4.ListUtils;
public class leetcode152 {

    /*  public int maxProduct(int[] nums) {

      }*/
    public static void main(String[] args) {

        Integer[] nums = {2, 3, -2, 4};

        List<List<Integer>> list = new ArrayList<>();

        List<List<Integer>> cur = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            Integer curNum = nums[i];
            for (int j = 0; j < cur.size(); j++) {
                List<Integer> nextCur = cur.get(j);
                nextCur.add(curNum);
            }
            cur.add(new ArrayList<>(List.of(curNum)));
            list.addAll(cur.stream()
                    .map(ArrayList::new)
                    .toList());
            System.out.println(list);
        }
//        reduce(Integer.MIN_VALUE, Math::max);
//       Integer result = list.stream().map((arr) -> arr.stream().reduce(1, (acc, v) -> acc * v)).reduce(Integer.MIN_VALUE,(acc,v)->Math.max(acc,v));
        Integer result = list.stream().map((arr) -> arr.stream().reduce(1, (acc, v) -> acc * v)).reduce(Integer.MIN_VALUE, Math::max);

        System.out.println(result);
//        trackBack(nums, cur, list);


    }

    static void trackBack(int[] nums, List<Integer> cur, List<Integer> results) {


    }

}
