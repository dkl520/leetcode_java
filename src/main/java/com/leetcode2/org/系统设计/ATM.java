package com.leetcode2.org.系统设计;

public class ATM {
    private long[] cnt;   // 每张钞票剩余数量
    private int[] value;  // 每张钞票面额

    public ATM() {
        // 初始化钞票数量和面额
        cnt = new long[]{0, 0, 0, 0, 0};
        value = new int[]{20, 50, 100, 200, 500};
    }

    // 存入钞票
    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < 5; ++i) {
            cnt[i] += banknotesCount[i];
        }
    }

    // 取出指定金额的钞票
    public int[] withdraw(int amount) {
        int[] res = new int[5]; // 保存每种面额取出的数量
        // 模拟尝试取出钞票的过程
        for (int i = 4; i >= 0; --i) {
            res[i] = (int) Math.min(cnt[i], amount / value[i]); // 计算当前面额的张数
            amount -= res[i] * value[i]; // 减去对应金额
        }
        if (amount > 0) {
            // 无法完成操作，返回 [-1]
            return new int[]{-1};
        } else {
            // 可以完成操作，更新 ATM 中的钞票数量
            for (int i = 0; i < 5; ++i) {
                cnt[i] -= res[i];
            }
            return res;
        }
    }
}

/**
 * Your ATM object will be instantiated and called as such:
 * ATM obj = new ATM();
 * obj.deposit(banknotesCount);
 * int[] param_2 = obj.withdraw(amount);
 */