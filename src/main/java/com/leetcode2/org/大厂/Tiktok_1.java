package com.leetcode2.org.大厂;

import java.util.*;

public class Tiktok_1 {
    int getMaxSum(int[] views, int[] likes) {

        Queue<Integer> viewQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int view : views) {
            viewQueue.add(view);
        }

        // Step 1: Convert int[] to Integer[]
        Integer[] likesArray = Arrays.stream(likes).boxed().toArray(Integer[]::new);

        // Step 2: Sort in descending order using Comparator.reverseOrder()
        Arrays.sort(likesArray, Collections.reverseOrder());

        // Step 3: Convert Integer[] back to int[]
        likes = Arrays.stream(likesArray).mapToInt(Integer::intValue).toArray();
        int result = 0;
        for (int like : likes) {
            while (!viewQueue.isEmpty()) {
                int view = viewQueue.poll();
                if (view < like) {
                    result += like;
                    break;
                }
            }
        }
        return  result;


    }

    public static void main(String[] args) {
        int[] views = new int[]{2, 3, 4, 5, 6};
        int[] likes = new int[]{4, 5, 6, 7, 3};
        Tiktok_1 t = new Tiktok_1();
        System.out.println(t.getMaxSum(views, likes));
    }


}
