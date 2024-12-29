package com.leetcode2.org.周赛1214;

public class M3 {
    public static int solution(int N, int M) {


        int MOD = 998244353;
        int scores = 0;
        int goodC = 0;
        int badC = 0;
        for (int i = 1; i <= N; i++) {
            if (checkGood(M, i)) {
                goodC++;
            } else {
                badC++;
            }
        }
        return (goodC % MOD) * (badC % MOD) % MOD;

    }

    static boolean checkGood(int M, int num) {
        String strN = "" + num;
        int count = 0;
        for (int i = 0; i < strN.length(); i++) {
            count += Integer.parseInt(strN.charAt(i) + "");
        }
        return (num - count) <= M;
    }

    public static void main(String[] args) {
        System.out.println(solution(731165848, 803544483) == 18);
        System.out.println(solution(20, 5) == 99);
        System.out.println(solution(100, 3) == 819);
    }
}
