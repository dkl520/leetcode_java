package com.leetcode2.广度优先搜索;

import java.util.ArrayList;
import java.util.List;

public class Solution2101 {

    public int maximumDetonation(int[][] bombs) {
        int max = 0;
        for (int[] bomb : bombs) {
            max = Math.max(max, bfs(bombs, bomb));
        }
        return max;

    }

    boolean clacDistance(int[] pos1, int[] pos2) {
        if ( Math.sqrt( Math.abs(pos1[0] - pos2[0]) * Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]) * Math.abs(pos1[1] - pos2[1]) ) < (pos1[2] + pos2[2])   ) {
            return true;
        }
        return false;
    }

    List<int[]> search(List<int[]> list, int[][] bombs) {
        List<int[]> result = new ArrayList<>();
        for (int[] bomb : bombs) {
            for (int[] start : list) {
                if (list.contains(bomb)) {
                    continue;
                }
                if (clacDistance(start, bomb)) {
                    result.add(bomb);
                }
            }
        }
        return result;
    }

    int bfs(int[][] bombs, int[] start) {
        List<int[]> result = new ArrayList<>();
        result.add(start);
        List<int[]> results = new ArrayList<>(result);
        while (!result.isEmpty()) {
            result = search(results, bombs);
            results.addAll(result);
        }
        return results.size();
    }

    public static void main(String[] args) {

        int[][] bombs = {
                {1,1,100},
                {100,100,1}
        };
        Solution2101 solution = new Solution2101();
        solution.maximumDetonation(bombs);



    }
}