package com.leetcode2.org.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TiktokSegment {

    int calcdifferenceSet(int[] segment, int[][] discConnectSet) {

        Arrays.sort(discConnectSet, (a, b) -> a[0] - b[0]);
        List<int[]> allSet = new ArrayList<int[]>();
        for (int i = 0; i < segment.length; i++) {
            int[] set = new int[2];
            for (int j = i + 1; j < segment.length; j++) {
                set = new int[]{i, j};
            }
            allSet.add(set);
        }
        int result = 0;

//        allSet.stream().filter((set)-> )
        for (int[] set : allSet) {
            boolean bol = true;
            for (int[] disc : discConnectSet) {
                if (disc[0] >= set[0] && disc[0] < set[1]) {
                    bol = false;
                    break;
                }
            }
            result += bol ? 1 : 0;
        }
        return result + segment.length;
    }


    public static void main(String[] args) {
        int[] segment = {1, 2, 3, 4};
        int[][] discConnectSet = {
                {1, 2},
                {2, 3}
        };
        TiktokSegment tiktokSegment = new TiktokSegment();
        System.out.println(tiktokSegment.calcdifferenceSet(segment, discConnectSet));
    }

}
