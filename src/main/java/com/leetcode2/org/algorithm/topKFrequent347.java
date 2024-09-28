package com.leetcode2.org.algorithm;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class topKFrequent347 {

    public int[] topKFrequent(int[] nums, int k) {
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>();

        for (int num : nums) {
            if (sortedMap.containsKey(num)) {
                sortedMap.replace(num, sortedMap.get(num) + 1);
            } else {
                sortedMap.put(num, 1);
            }

        }
      return  nums;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 2, 1, 2, 3,3,3,1,1,2};
        int k = 2;
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>(new CustomerParator());

        for (int num : nums) {
            if (sortedMap.containsKey(num)) {
                sortedMap.replace(num, sortedMap.get(num) + 1);
            } else {
                sortedMap.put(num, 1);
            }

        }

        System.out.println(sortedMap);
//        return topKFrequent(nums, k);
    }

}

class  CustomerParator implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
        return  Integer.compare(o2.intValue(),o1.intValue());
    }

}
