package com.leetcode2.org.大厂.tiktok;

import java.util.Arrays;

public class TiktokDelayMinimization {

    //    int get(int n, int[] delay, int k) {
//        Deque<Integer> queue = new ArrayDeque<>();
//        int[] dp = new int[n + 1];
//        for (int i = 0; i < k; i++) {
//            int cur = delay[i];
//            while (!queue.isEmpty() && delay[queue.peekLast()] > cur) {
//                queue.pollLast();
//            }
//            queue.offerLast(i);
//            dp[i] = delay[i];
//        }
//        for (int i = k; i < n; i++) {
//            int cur = delay[i];
//            if (!queue.isEmpty() && i - queue.peek() > k) {
//                queue.poll();
//            }
//            dp[i] = delay[queue.peek()] + cur;
//            while (!queue.isEmpty() && delay[queue.peekLast()] > cur) {
//                queue.pollLast();
//            }
//            queue.offerLast(i);
//        }
//        return  dp[n-1];
//
//    }
    int get(int n, int[] delay, int k) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= i - k && j >= 0; j--) {
                dp[i] = Math.min(dp[j] + delay[i - 1], dp[i]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        int[] delay = new int[]{1, 4, 2, 6, 2};
        int k = 2;
        TiktokDelayMinimization t = new TiktokDelayMinimization();
        System.out.println(t.get(n, delay, k));
    }


}
