package com.leetcode2.org.博弈论;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1510 {
    // 判断玩家是否能赢
    public boolean winnerSquareGame(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1; // 初始化dp数组，1是必赢的
        outer: // 这是一个标签，用于在嵌套循环中直接跳出到外层循环
        for (int i = 2; i <= n; i++) {
            List<Integer> squareNums = getSquareNum(i); // 获取小于等于i的所有平方数
            for (int squareNum : squareNums) {
                // 如果当前数字减去一个平方数后的状态是必败态（即dp[i - squareNum] % 2 == 0），
                // 则当前状态是必胜态，更新dp[i]，并跳出循环
                if (dp[i - squareNum] % 2 == 0) {
                    dp[i] = dp[i - squareNum] + 1;
                    continue outer;
                }
                // 如果当前数字减去一个平方数后的状态是必胜态，
                // 则尝试更新dp[i]（实际上这里的更新是多余的，因为只要找到一个必败态就可以确定当前是必胜态）
                if (dp[i - squareNum] % 2 == 1) {
                    dp[i] = dp[i - squareNum] + 1;
                }
            }
        }
        // 如果dp[n]是奇数，则返回true，表示当前玩家能赢；否则返回false
        return dp[n] % 2 != 0;
    }

    // 获取小于等于n的所有平方数，并按降序排列
    List<Integer> getSquareNum(int n) {
        List<Integer> squareNum = new ArrayList<Integer>();
        for (int i = 1; i < n; i++) {
            int num = i * i;
            if (num <= n) {
                squareNum.add(num);
            } else {
                break;
            }
        }
        Collections.reverse(squareNum); // 降序排列
        return squareNum;
    }

    public static void main(String[] args) {
        Solution1510 solution = new Solution1510();
        System.out.println(solution.winnerSquareGame(8)); // 测试
    }
}