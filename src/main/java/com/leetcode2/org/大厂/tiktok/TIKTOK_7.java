//package com.leetcode2.org.大厂.tiktok;
//
//public class TIKTOK_7 {
//
//    int calcPerfectVideo(int[] videos) {
//        int n = videos.length;
//        int[] dp = new int[n + 1];
//
//        for (int i = 1; i <= n; i++) {
//
//            for (int j = i - 1; j >= 0; j--) {
//                if (videos[j] > videos[i]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//
//                }
//            }
//        }
//
//    }
//
//
//    public static void main(String[] args) {
//        int[] videos = {1, 10, 5, 4, 7, 3};
//        TIKTOK_7 tikTok = new TIKTOK_7();
//        System.out.println(
//                tikTok.calcPerfectVideo(videos)
//        );
//
//
//    }
//}
