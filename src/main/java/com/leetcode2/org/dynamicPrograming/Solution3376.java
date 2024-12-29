package com.leetcode2.org.dynamicPrograming;

import java.util.List;

public class Solution3376 {

    static record Status(int x, long days) {
    }

    public int findMinimumTime(List<Integer> strength, int k) {

        int size = strength.size();
        Status[] dp = new Status[1 << size];
        dp[0] = new Status(1, 0); // 初始状态

        for (int state = 0; state < (1 << size); state++) {
            if (dp[state] == null) continue; // 跳过无效状态
            int currentGain = dp[state].x;
            long currentDays = dp[state].days;
            for (int j = 0; j < size; j++) {
                if ((state & (1 << j)) == 0) { // 未选择该任务
                    int nextState = state | (1 << j);
                    int curNeed = strength.get(j);
                    long newDays = currentDays + (long) Math.ceil((double) curNeed / currentGain);
                    Status nextStatus = new Status(currentGain + k, newDays);

                    // 更新 dp[nextState]
                    if (dp[nextState] == null || dp[nextState].days > newDays || (dp[nextState].days == newDays && dp[nextState].x < currentGain + 1)) {
                        dp[nextState] = nextStatus;
                    }
                }
            }
        }
        return (int) dp[(1 << size) - 1].days;
    }

    public static void main(String[] args) {
        Solution3376 s = new Solution3376();
        System.out.println(
                s.findMinimumTime(List.of(7, 3, 6, 18, 22, 50), 4)
        );


    }


}
