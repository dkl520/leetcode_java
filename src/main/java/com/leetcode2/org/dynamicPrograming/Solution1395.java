package com.leetcode2.org.dynamicPrograming;

public class Solution1395 {

    public int numTeams(int[] rating) {
        int n = rating.length;
        int[] dp = new int[n + 1];
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            int cur = rating[i];
            int leftSmall = 0;
            int leftBig = 0;
            for (int j = i - 1; j >= 0; j--) {  //left
                if (cur < rating[j]) {
                    leftBig++;
                } else if (cur > rating[j]) {
                    leftSmall++;
                }
            }
            int rightSmall = 0;
            int rightBig = 0;
            for (int j = i + 1; j < n; j++) {
                if (cur < rating[j]) {
                    rightBig++;
                } else if (cur > rating[j]) {
                    rightSmall++;
                }
            }
            result += leftSmall * rightBig + leftBig * rightSmall;
        }
        return result;
    }
}
