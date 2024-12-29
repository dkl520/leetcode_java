package com.leetcode2.周赛.zs130;

import java.util.*;

public class SolutionQ2 {
    static record Point(int[] pos, char tag, int rank) {
    }

    ;

    public int maxPointsInsideSquare(int[][] points, String s) {
        if (points.length == 0) return 0;
        Queue<Point> queue = new PriorityQueue<>((p1, p2) -> {
            return Math.max(Math.abs(p1.pos[0]), Math.abs(p1.pos[1])) - Math.max(Math.abs(p2.pos[0]), Math.abs(p2.pos[1]));

        });
        for (int i = 0; i < points.length; i++) {
            int[] curP = points[i];
            char curTag = s.charAt(i);
            queue.offer(new Point(curP, curTag, Math.max(Math.abs(curP[0]), Math.abs(curP[1]))));
        }
        Set<Character> set = new HashSet<>();
        int curRank = 0;
        if (queue.peek() != null) {
            curRank = queue.peek().rank;
        }
        Set<Character> setC = new HashSet<>();
        boolean isPaused = false;
        while (!queue.isEmpty()) {
            Point curPoint = queue.poll();
            Set<Character> setTemp = new HashSet<>(setC);
            if (curRank != curPoint.rank) {

                if (!Collections.disjoint(setTemp, set)) {
                    isPaused = true;
                    break;
                }
                set.addAll(setC);
                setC.clear();
                curRank = curPoint.rank;
            }
            if (setC.contains(curPoint.tag)) {
                isPaused =true;
                break;
            }
            setC.add(curPoint.tag);
        }
        if (!isPaused) {
            if (Collections.disjoint(setC, set)) {

                set.addAll(setC);
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        SolutionQ2 solutionQ2 = new SolutionQ2();
        // 定义二维数组
//        int[][] points = {
//                {2, 2},
//                {-1, -2},
//                {-4, 4},
//                {-3, 1},
//                {3, -3}
//        };
//
//// 定义字符串
//        String s = "abdca";

        int[][] points = {
                {1, 1},
                {-2, -2},
                {-2, 2}
        };

        // 定义字符串 s
        String s = "abb";
        solutionQ2.maxPointsInsideSquare(points, s);

    }
}
