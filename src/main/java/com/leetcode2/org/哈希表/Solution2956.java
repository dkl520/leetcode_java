package com.leetcode2.org.哈希表;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution2956 {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        for (int i : nums1) {
            map1.put(i, 1);
        }
        for (int i : nums2) {
            map2.put(i, 1);
        }
        int ans1 = (int) Arrays.stream(nums1).filter(map2::containsKey).count();
        int ans2 = (int) Arrays.stream(nums2).filter(map1::containsKey).count();
        return new int[]{ans1, ans2};
    }

    public static void main(String[] args) {
        int [] nums1 = new int[]{2, 3, 2};
        int [] nums2 = new int[]{1, 2};
        Solution2956 solution2956 = new Solution2956();
        int[] res = solution2956.findIntersectionValues(nums1, nums2);
        System.out.println(Arrays.toString(res));


    }

}
