package com.leetcode2.org.滑动窗口;

public class Solution2079 {
    public int wateringPlants(int[] plants, int capacity) {
        int steps = 0;
        int n = plants.length;
        int curCapacity = capacity;
        for (int i = 0; i < n; i++) {
            if (curCapacity >= plants[i]) {
                steps += 1;
                curCapacity -= plants[i];
            } else {
                steps += i + i + 1;
                curCapacity = capacity - plants[i];
            }
        }
        return steps;
    }

    public static void main(String[] args) {
//        int[] plants = {2, 2, 3, 3};
//        int capacity = 5;
        int[] plants = {3, 2, 4, 2, 1};
        int capacity = 6;
        System.out.println(new Solution2079().wateringPlants(plants, capacity));
    }


}
