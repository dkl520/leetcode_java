package com.leetcode2.org.algorithm;

import java.util.ArrayList;

public class Pack {
    public static int copeWith(ArrayList<Treasure> listTreasure, int w) {
        int[][] dp = new int[listTreasure.size() + 1][w + 1];
        for (int i = 1; i <= listTreasure.size(); i++) {
            Treasure t = listTreasure.get(i-1);
            for (int j = 0; j <= w; j++) {

                if (t.getWeight() > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(
                            dp[i - 1][j],
                            dp[i - 1][j - t.getWeight()] + t.getValue()
                    );
                }
            }
        }
        return dp[listTreasure.size()][w];
    }
    public static void main(String[] args) {
        Treasure t1 = new Treasure(100, 500);
        Treasure t2 = new Treasure(120, 70);
        Treasure t3 = new Treasure(150, 69);
        Treasure t4 = new Treasure(200, 39);
        Treasure t5 = new Treasure(50, 10);

        ArrayList<Treasure> listTreasure = new ArrayList();
        listTreasure.add(t1);
        listTreasure.add(t2);
        listTreasure.add(t3);
        listTreasure.add(t4);
        listTreasure.add(t5);
        int weight = 300;
        int result = copeWith(listTreasure, weight);
        System.out.println(result);
    }
}





