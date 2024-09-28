package com.leetcode2.org.博弈论;

import java.util.stream.IntStream;

public class Solution1686 {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        boolean[] visited = new boolean[n];
        int bobValue = 0;
        int aliceValue = 0;
        while (n > 0) {
            int aliceIndex = getAliceValuesMaxValue(visited, aliceValues, bobValues);
            aliceValue += aliceValues[aliceIndex];
            visited[aliceIndex] = true;
            n--;
            if (n <= 0) break;
            int bobIndex = getBobMaxValue(visited, aliceValues, bobValues);
            bobValue += bobValues[bobIndex];
            visited[bobIndex] = true;
            n--;
        }
        if (aliceValue == bobValue) {
            return 0;
        }
        if (bobValue < aliceValue) {
            return 1;
        } else {
            return -1;
        }
    }

    int getBobMaxValue(boolean[] visited, int[] aliceValues, int[] bobValues) {
        int index = -1;
        int maxDistanceValue = Integer.MIN_VALUE;
        for (int i = 0; i < bobValues.length; i++) {
            if (visited[i]) continue;
            int maxBobValue = bobValues[i];
            int finalI = i;
            int nextAliceValue = IntStream.range(0, aliceValues.length)
                    .filter(in -> in != finalI && !visited[in])
                    .map(in -> aliceValues[in]).max().orElse(0);
            if (maxBobValue - nextAliceValue > maxDistanceValue) {
                index = i;
            }
            maxDistanceValue = Math.max(maxDistanceValue, maxBobValue - nextAliceValue);
        }
        return index;
    }

    int getAliceValuesMaxValue(boolean[] visited, int[] aliceValues, int[] bobValues) {
        int index = -1;
        int maxDistanceValue = Integer.MIN_VALUE;
        for (int i = 0; i < aliceValues.length; i++) {
            if (visited[i]) continue;
            int maxAliceValue = aliceValues[i];
            int finalI = i;

            int nextBobValue = IntStream.range(0, bobValues.length)
                    .filter(in -> in != finalI && !visited[in])
                    .map(in -> bobValues[in]).max().orElse(0);
            if (maxAliceValue - nextBobValue > maxDistanceValue) {
                index = i;
            }
            maxDistanceValue = Math.max(maxDistanceValue, maxAliceValue - nextBobValue);
        }
        return index;
    }


    public static void main(String[] args) {
        int[] aliceValues = {6, 7, 5, 6, 5, 6, 9,  3, 7,  3, 5, 6, 10, 3, 2, 7, 2, 5, 10, 2};
        int[] bobValues =   {8, 2, 9, 10, 3, 2, 1, 1, 10, 6, 9, 1, 1, 4, 10, 3, 7, 9, 6, 2};
        Solution1686 solution1686 = new Solution1686();
        System.out.println(solution1686.stoneGameVI(aliceValues, bobValues));
    }


}
