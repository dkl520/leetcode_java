package com.leetcode2.org.图论;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution997 {
    public int findJudge(int n, int[][] trust) {

        int[][] degreesList = new int[n + 1][2];

        for (int[] trueList : trust) {
            degreesList[trueList[1]][1]++;
            degreesList[trueList[0]][0]++;
        }
//        for (int i = 1; i < degreesList.length; i++) {
//            if (degreesList[i][0] == 0 && degreesList[i][1] == n - 1) {
//                return i;
//            }
//        }
//
//        return -1;
        return IntStream.range(1,degreesList.length)
                .filter(i->degreesList[i][0]==0 && degreesList[i][1] ==n-1 )
                .findFirst()
                .orElse(-1);

    }

    public static void main(String[] args) {
        int n = 3;
        int[][] trust = {
                {1, 3},
                {2, 3}
        };
        System.out.println(new Solution997().findJudge(n, trust));
    }
}
