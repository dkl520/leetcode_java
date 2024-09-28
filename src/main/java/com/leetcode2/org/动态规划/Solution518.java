package com.leetcode2.org.动态规划;

//给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
//
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
//
// 假设每一种面额的硬币有无限个。
//
// 题目数据保证结果符合 32 位带符号整数。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：amount = 5, coins = [1, 2, 5]
//输出：4
//解释：有四种方式可以凑成总金额：
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
//
//
// 示例 2：
//
//
//输入：amount = 3, coins = [2]
//输出：0
//解释：只用面额 2 的硬币不能凑成总金额 3 。
//
//
// 示例 3：
//
//
//输入：amount = 10, coins = [10]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 300
// 1 <= coins[i] <= 5000
// coins 中的所有值 互不相同
// 0 <= amount <= 5000
//
//
// Related Topics 数组 动态规划 👍 1308 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution518 {
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        int[] newCoins = Arrays.copyOf(coins, coins.length + 1);
        System.arraycopy(newCoins, 0, newCoins, 1, coins.length);
        coins = newCoins;
        int cl = coins.length;
        int[][] dp = new int[amount + 1][cl];
        dp[0][0] = 1;
        for (int i = 0; i <= amount; i++) {
            for (int j = 1; j < cl; j++) {
                if (coins[j] > i) {
                    dp[i][j] = dp[i][j - 1];
                    continue;
                }
                if (coins[j] == i) {
                    dp[i][j] = 1 + dp[i][j - 1];
                    continue;
                }
                if (coins[j] < i) {
                    int len = i / coins[j];
                    for (int k = 0; k <= len; k++) {
                        dp[i][j] += dp[i - k * coins[j]][j - 1];
                    }
                }
            }
        }
        return dp[amount][cl - 1];

    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};
        System.out.println(new Solution518().change(amount, coins));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
