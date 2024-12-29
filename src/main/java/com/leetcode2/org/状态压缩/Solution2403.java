package com.leetcode2.org.状态压缩;

public class Solution2403 {
    static record Status(int gain, long days) {
    }

    public long minimumTime(int[] power) {
        int size = power.length;
        Status[] dp = new Status[1 << size];
        dp[0] = new Status(1, 0); // 初始状态

        for (int state = 0; state < (1 << size); state++) {
            if (dp[state] == null) continue; // 跳过无效状态

            int currentGain = dp[state].gain;
            long currentDays = dp[state].days;

            for (int j = 0; j < size; j++) {
                if ((state & (1 << j)) == 0) { // 未选择该任务
                    int nextState = state | (1 << j);
                    int curNeed = power[j];
                    long newDays = currentDays + (long) Math.ceil((double) curNeed / currentGain);
                    Status nextStatus = new Status(currentGain + 1, newDays);

                    // 更新 dp[nextState]
                    if (dp[nextState] == null || dp[nextState].days > newDays || (dp[nextState].days == newDays && dp[nextState].gain < currentGain + 1)) {
                        dp[nextState] = nextStatus;
                    }
                }
            }
        }

        return dp[(1 << size) - 1].days;
    }
}
