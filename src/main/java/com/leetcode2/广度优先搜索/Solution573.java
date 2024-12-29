package com.leetcode2.广度优先搜索;

import java.util.ArrayList;
import java.util.List;

public class Solution573 {
    int calcDis(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }


    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        List<Integer> treeNutsDis = new ArrayList<Integer>();
        List<Integer> squirrelNutsDis = new ArrayList<>();
        for (int[] nut : nuts) {
            treeNutsDis.add(calcDis(nut, tree));
            squirrelNutsDis.add(calcDis(nut, squirrel));
        }
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < squirrelNutsDis.size(); i++) {
            int dis = squirrelNutsDis.get(i) + treeNutsDis.get(i);
            for (int j = 0; j < treeNutsDis.size(); j++) {
                if (i != j) {
                    dis += treeNutsDis.get(j) * 2;
                }
            }
            minDis = Math.min(minDis, dis);
        }
        return minDis;
    }



    public int minDistance2(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        List<Integer> treeNutsDis = new ArrayList<Integer>();
        List<Integer> squirrelNutsDis = new ArrayList<>();
        for (int[] nut : nuts) {
            treeNutsDis.add(calcDis(nut, tree));
            squirrelNutsDis.add(calcDis(nut, squirrel));
        }
        int dis = 0;
        for (Integer treeNutsDi : treeNutsDis) {
            dis += treeNutsDi * 2;
        }
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < squirrelNutsDis.size(); i++) {
            minDis = Math.min(minDis, squirrelNutsDis.get(i) - treeNutsDis.get(i));
        }
        return minDis + dis;

    }

    public static void main(String[] args) {
        int[] squirrel = new int[]{0, 1};
        int[] tree = new int[]{3, 2};
        int[][] nuts = {
                {2, 0}, {4, 1}, {0, 4}, {1, 3},
                {1, 0}, {3, 4}, {3, 0}, {2, 3},
                {0, 2}, {0, 0}, {2, 2}, {4, 2},
                {3, 3}, {4, 4}, {4, 0}, {4, 3},
                {3, 1}, {2, 1}, {1, 4}, {2, 4}
        };
        Solution573 solution = new Solution573();
        System.out.println(solution.minDistance(5, 5, tree, squirrel, nuts));
    }

}
