package com.leetcode2.org.动态规划;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution473 {

    LinkedList[] sideList = new LinkedList[4];


    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) return false;

        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) return false;

        int sideLength = sum / 4;
        Arrays.sort(matchsticks); // 优化，先尝试放入最长的火柴
        reverse(matchsticks); // 逆序排列
        Arrays.setAll(sideList, i -> new LinkedList<>());
        int[] sides = new int[4];


        return dfs(matchsticks, sides, 0, sideLength);
    }

    private boolean dfs(int[] matchsticks, int[] sides, int index, int sideLength) {
        if (index == matchsticks.length) {
            return sides[0] == sideLength && sides[1] == sideLength &&
                    sides[2] == sideLength && sides[3] == sideLength;
        }

        for (int i = 0; i < 4; i++) {
            if (sides[i] + matchsticks[index] <= sideLength) {
                sides[i] += matchsticks[index];
                sideList[i].add(matchsticks[index]);
                boolean next = dfs(matchsticks, sides, index + 1, sideLength);
                if (next) {
                    return true;
                }
                sides[i] -= matchsticks[index];
                sideList[i].removeLast();
            }
            // 如果当前边已经为0，意味着本次尝试失败，后续边也不需要再尝试
            if (sides[i] == 0) break;
        }
        return false;
    }

    private void reverse(int[] matchsticks) {
        int i = 0, j = matchsticks.length - 1;
        while (i < j) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        Solution473 solution473 = new Solution473();

        // 示例 1
//        int[] matchsticks1 = {1, 1, 2, 2, 2};
//        System.out.println(solution473.makesquare(matchsticks1)); // 输出: true

        // 示例 2
        int[] matchsticks2 = {5, 5, 4, 3, 2, 1};
        System.out.println(solution473.makesquare(matchsticks2)); // 输出: false
    }
}
