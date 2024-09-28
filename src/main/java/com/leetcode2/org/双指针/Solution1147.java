package com.leetcode2.org.双指针;

public class Solution1147 {

    public int longestDecomposition(String text) {

        int left = 1;
        int leftSliceP = 0;
        int right = text.length() - 1;
        int rightSliceP = text.length();
        int count = 0;
        int line = 1;
        while (left <= right) {
            String subLeft = text.substring(leftSliceP, left);
            String subRight = text.substring(right, rightSliceP);
            if (subLeft.equals(subRight)) {
                leftSliceP = left;
                rightSliceP = right;
                count += 2;
            }
            if (left == right && !subLeft.equals(subRight)) {
                count += 1;
            }
            left++;
            right--;
        }

        return text.length() % 2 == 1 ? count + 1 : count;

    }

    public static void main(String[] args) {
//        String text = "antaprezatepzapreanta";
        String text = "elvtoelvto";
        Solution1147 solution1147 = new Solution1147();
        System.out.println(solution1147.longestDecomposition(text));
    }


}
