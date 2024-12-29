package com.leetcode2.org.贪心;

import java.util.Arrays;

public class Solution3128_2 {


    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Integer[] horizontalCuts = Arrays.stream(horizontalCut).boxed().toArray(Integer[]::new);
        Integer[] verticalCuts = Arrays.stream(verticalCut).boxed().toArray(Integer[]::new);

        Arrays.sort(horizontalCuts, (a, b) -> Integer.compare(b, a));

        Arrays.sort(verticalCuts, (a, b) -> Integer.compare(b, a));

        int row = 1;
        int col = 1;
        int h = 0;
        int v = 0;

        int costAll = 0;

        while (h < horizontalCuts.length || v < verticalCuts.length) {

            if (h < horizontalCuts.length && v < verticalCuts.length) {


                if (horizontalCuts[h] < verticalCuts[v]) {
                    costAll += row * verticalCuts[v];
                    col++;
                    v++;
                } else {
                    costAll += col * horizontalCuts[h];
                    row++;
                    h++;
                }

            } else if (h < horizontalCuts.length) {
                costAll += col * horizontalCuts[h];
                row++;
                h++;

            } else {
                costAll += row * verticalCuts[v];
                col++;
                v++;
            }

        }
        return costAll;

    }

    public static void main(String[] args) {
        // 定义 m 和 n
        int m = 7;
        int n = 4;

        // 定义 horizontalCut 和 verticalCut 数组
        int[] horizontalCut = {13, 6, 12, 14, 4, 7};
        int[] verticalCut = {14, 15, 11};


        Solution3128_2 solution3128_2 = new Solution3128_2();
        solution3128_2.minimumCost(3, 2, horizontalCut, verticalCut);
    }
}
