package com.leetcode2.org.数值运算;

public class Solution201 {

    public int rangeBitwiseAnd(int left, int right) {

        String[] binaryString = new String[right - left + 1];
        String cur = Integer.toBinaryString(left);
        for (int i = left + 1; i <= right; i++) {
            String next = Integer.toBinaryString(i);

        }


        return 11;

//        return result;

    }

    public static void main(String[] args) {
        int left = 5;
        int right = 7;
        System.out.println(new Solution201().rangeBitwiseAnd(left, right));


    }


}
