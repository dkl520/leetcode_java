package com.leetcode2.org.博弈论;

import java.util.Arrays;

public class Solution1563 {

    public int stoneGameV(int[] stoneValue) {

        int[] sliceStones = calcIndex(stoneValue);
        int sum = Arrays.stream(sliceStones).sum();
        while (sliceStones.length > 0) {
            sliceStones = calcIndex(sliceStones);
            sum += Arrays.stream(sliceStones).sum();
        }
        return sum;
    }

    int[] calcIndex(int[] stoneValue) {
        int leftSum = 0;
        int index = -1;
        int sum = Arrays.stream(stoneValue).sum();
        int isLeft = -1;
        int maxDistance = Integer.MAX_VALUE;
        for (int i = 0; i < stoneValue.length; i++) {
            leftSum += stoneValue[i];
            if (maxDistance > Math.abs(sum - leftSum - leftSum)) {
                maxDistance = Math.abs(sum - leftSum - leftSum);
                index = i;
                if (sum - leftSum - leftSum == 0) {
                    isLeft = 0;
                } else if (sum - leftSum - leftSum > 0) {
                    isLeft = 1;
                } else {
                    isLeft = 2;
                }
            }
        }
        if (isLeft == 0) {
            double leftVariance = calculateVariance(Arrays.copyOfRange(stoneValue, 0, index + 1));
            double rightVariance = calculateVariance(Arrays.copyOfRange(stoneValue, index + 1, stoneValue.length));
            if (leftVariance > rightVariance) {
                return Arrays.copyOfRange(stoneValue, index + 1, stoneValue.length);
            }
            if (leftVariance<rightVariance){

                return Arrays.copyOfRange(stoneValue, 0, index + 1);
            }
            if (leftVariance == rightVariance) {

                if (index < (stoneValue.length - 1) / 2) {
                    return Arrays.copyOfRange(stoneValue, index + 1, stoneValue.length);
                }
                return Arrays.copyOfRange(stoneValue, 0, index + 1);


            }


        }

        if (isLeft == 1) {
            return Arrays.copyOfRange(stoneValue, 0, index + 1);
        }
        return Arrays.copyOfRange(stoneValue, index + 1, stoneValue.length);

    }

    // 方法用于计算数组的方差
    public static double calculateVariance(int[] nums) {
        // 计算均值
        double sum = 0;
        for (int num : nums) {
            sum += num;
        }
        double mean = sum / nums.length;

        // 计算方差
        double sumOfSquaredDifferences = 0;
        for (int num : nums) {
            sumOfSquaredDifferences += Math.pow(num - mean, 2);
        }
        return sumOfSquaredDifferences / nums.length;
    }

    public static void main(String[] args) {

        int[] stoneValue = {1,4,2,3};
        System.out.println(new Solution1563().stoneGameV(stoneValue));

    }

}
