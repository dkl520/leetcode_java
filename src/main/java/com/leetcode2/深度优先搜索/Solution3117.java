package com.leetcode2.深度优先搜索;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.ArrayList;

public class Solution3117 {
    public int minimumValueSum(int[] nums, int[] andValues) {
        List<int[]> list = new ArrayList<int[]>();
        int[] result = new int[andValues.length];
        dfs(nums, andValues, list, 0, 0, -1, result);
        return 11;
    }

    void dfs(int[] nums, int[] andValues, List<int[]> list, int numsIndex, int andValueIndex, int claclNum, int[] result) {
        if (andValueIndex == andValues.length && numsIndex == nums.length) {
            int[] resultCopy = result.clone();
            list.add(resultCopy);
            return;
        }

        for (int i = numsIndex; i < nums.length; i++) {
            if (claclNum == -1) {
                claclNum = nums[numsIndex];
            } else {
                claclNum &= nums[numsIndex];
            }

            if (claclNum == andValues[andValueIndex]) {
                result[andValueIndex] = nums[numsIndex];
                dfs(nums, andValues, list, numsIndex + 1, andValueIndex + 1, -1, result);
            } else {
                dfs(nums, andValues, list, numsIndex + 1, andValueIndex, claclNum, result);
            }
//            claclNum = -1;
        }

    }

    public static void main(String[] args) {

        int[] nums = new int[]{1, 4, 3, 3, 2};
        int[] andValues = new int[]{0, 3, 3, 2};
        System.out.println(new Solution3117().minimumValueSum(nums, andValues));


    }


}
