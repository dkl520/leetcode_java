package com.leetcode2.org.大厂.citadel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GlobalMaximum {
    double getGlobalMaximum(int[] arr, int num) {
        List<int[]> l = new ArrayList<>();
        int start = 0;
        trackBack(start, l, num, new int[num], 0, arr);

//        l.stream().mapToInt(pair ->  Arrays.stream(pair).reduce(1e8, (acc,v,i,pair)-> acc = Math.min(acc, Math.abs( v- pair[i+1]  )  )   )   )
        double max = Integer.MIN_VALUE;
        for (int[] pair : l) {
            double min = 1e8;
            for (int i = 1; i < pair.length; i++) {
                int curDifferences = Math.abs(pair[i] - pair[i - 1]);
                min = Math.min(min, curDifferences);
            }
            max = Math.max(max, min);
        }
        return max;
    }

    void trackBack(int start, List<int[]> pairs, int count, int[] pair, int indexPair, int[] loopArr) {
        if (count == indexPair) {
            pairs.add(Arrays.copyOf(pair, pair.length));
            return;
        }
        for (int i = start; i < loopArr.length; i++) {
            pair[indexPair] = loopArr[i];
            trackBack(i + 1, pairs, count, pair, indexPair + 1, loopArr);
        }
    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 9};
        GlobalMaximum gm = new GlobalMaximum();
        System.out.println(gm.getGlobalMaximum(arr, 3));

    }
}
