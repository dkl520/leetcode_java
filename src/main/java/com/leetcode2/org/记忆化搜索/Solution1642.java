package com.leetcode2.org.记忆化搜索;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Solution1642 {


    public int furthestBuilding(int[] heights, int bricks, int ladders) {

        return dfs(heights, bricks, ladders, 0);

    }

    int dfs(int[] heights, int bricks, int ladders, int cur) {
        if (cur == heights.length - 1) {
            return heights.length - 1;
        }

        int max = cur;
        for (int i = cur; i < heights.length; i++) {
            max = Math.max(max, i);
            if (i == heights.length - 1) return i;
            if (heights[i + 1] > heights[i]) {
                if (bricks >= heights[i + 1] - heights[i]) {
                    max = Math.max(max, dfs(heights, bricks - (heights[i + 1] - heights[i]), ladders, i + 1));
                }
                if (ladders > 0) {
                    max = Math.max(max, dfs(heights, bricks, ladders - 1, i + 1));
                }
                break;
            }


        }

        return max;


    }

    public static void main(String[] args) throws IOException {
        int[] heights = new int[0];
        int bricks = 0;
        int ladders = 0;
        Solution1642 solution1642 = new Solution1642();
        File file = new File("src/txt/1642.text");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        if ((line = reader.readLine()) != null) {
            bricks = Integer.parseInt(line.trim()); // 读取第一行并赋值给 bricks
        }
        if ((line = reader.readLine()) != null) {
            ladders = Integer.parseInt(line.trim()); // 读取第二行并赋值给 ladders
        }
        if ((line = reader.readLine()) != null) {
            heights = Arrays.stream(line.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray(); // 读取第三行并赋值给 heights
        }
        
        System.out.println(solution1642.furthestBuilding(heights, bricks, ladders));
    }
}