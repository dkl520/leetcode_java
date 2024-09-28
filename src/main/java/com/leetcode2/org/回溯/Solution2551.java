package com.leetcode2.org.回溯;

import java.util.*;
import java.util.stream.Collectors;

public class Solution2551 {

    public long putMarbles(int[] weights, int k) {
        List<Deque<int[]>> results = new ArrayList<>();
        int clapboard = k - 1;
        Deque<int[]> resultStyle = new ArrayDeque<>();
        trackBack(weights, k, resultStyle, results);
        if (results.size() == 1) return 0;
        List<Integer> resultStyleSum = new ArrayList<>(results.stream().map(deque -> deque.stream().reduce(0, (acc, v) -> {
            if (v.length == 0) {
                acc += v[0] * 2;
            } else {
                acc += v[0];
                acc += v[v.length - 1];
            }
            return acc;
        }, Integer::sum)).toList());
        Collections.sort(resultStyleSum);

        return (resultStyleSum.get(resultStyleSum.size() - 1) - resultStyleSum.get(0));
    }

    void trackBack(int[] weights, int k, Deque<int[]> resultStyle, List<Deque<int[]>> results) {
        if (k == 1) {
            resultStyle.add(weights);
            Deque<int[]> deepCopyDeque = resultStyle.stream()
                    .map(int[]::clone)
                    .collect(Collectors.toCollection(ArrayDeque::new));
            results.add(deepCopyDeque);
            resultStyle.removeLast();
            return;
        }
        for (int i = 0; i < weights.length - k + 1; i++) {
            int[] curSlice = Arrays.copyOfRange(weights, 0, i + 1);
            resultStyle.add(curSlice);
            int[] nextWeights = Arrays.copyOfRange(weights, i + 1, weights.length);
            trackBack(nextWeights, k - 1, resultStyle, results);
            resultStyle.removeLast();
            System.out.println(resultStyle);
        }
    }


    public static void main(String[] args) {
        int[] weights = {1, 3, 5, 1};
        int k = 2;
        System.out.println(new Solution2551().putMarbles(weights, k));

    }


}
