package com.leetcode2.org.树状数组;


public class Solution2167 {
    public int minimumTime(String s) {
        int n = s.length();
//        s.replace()
        int[] sufix = new int[n + 1];

        int[] pre = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                sufix[i] = sufix[i + 1];
            } else {
                sufix[i] = Math.min(n - i, sufix[i + 1] + 2);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '0') {
                pre[i] = pre[i - 1];
            } else {
                pre[i] = Math.min(pre[i - 1] + 2, i );
            }
            ans = Math.min(pre[i] + sufix[i], ans);

        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "1100101";
        Solution2167 solution2167 = new Solution2167();
        System.out.println(
                solution2167.minimumTime(s)
        );
    }

}
