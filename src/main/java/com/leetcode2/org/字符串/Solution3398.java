package com.leetcode2.org.字符串;

public class Solution3398 {

    public int minLength(String s, int numOps) {
        if (checkoutAlternating(0, numOps, s) || checkoutAlternating(1, numOps, s)) return 1;

        int start = 2;
        int end = s.length();
        int result = end; // 初始化结果为最大长度
        while (start <= end) { // 注意条件改为 start <= end
            int mid = start + (end - start) / 2; // 取中点
            if (check(mid, numOps, s)) {
                result = mid; // 更新结果，尝试更短的长度
                end = mid - 1; // 缩小搜索范围，寻找更小的满足条件的长度
            } else {
                start = mid + 1; // 增大搜索范围
            }
        }
        return result; // 最后返回找到的最小值
    }

    public boolean checkoutAlternating(int x, int numOps, String s) {
        int n = s.length();
        int steps = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) % 2 != (i + x) % 2) steps++;
        }
        return steps <= numOps;
    }

    boolean check(int count, int numOps, String s) {
        int n = s.length();

        int step = 0;
        for (int left = 0, right = 0; right < n; right++) {
            if (right == n - 1 || s.charAt(right) != s.charAt(right + 1)) {
                int len = right - left + 1;
                step += Math.max(len / (count + 1), 0);
                left = right + 1;
            }
        }
        return step <= numOps;
    }

    public static void main(String[] args) {
        String s = "000";
        int numOps = 0;
        Solution3398 solution3398 = new Solution3398();
        System.out.println(solution3398.minLength(s, numOps));

    }


}
