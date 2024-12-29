package com.leetcode2.org.双指针;

public class Solution42 {

    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int result = 0;
        int lenLeft = height[left];
        int lenRight = height[right];
        while (left < right) {
            int minNum = Math.min(lenLeft, lenRight);
            if (lenLeft < lenRight) {
                left++;
                lenLeft = Math.max(lenLeft, height[left]);
                if (minNum > height[left]) {
                    result += minNum - height[left];
                }

            } else {
                right--;
                lenRight = Math.max(lenRight, height[right]);
                if (height[right] < minNum) {
                    result += minNum - height[right];
                }

            }
        }
        return result;


    }

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new Solution42().trap(height));
    }


}
