package com.leetcode2.org.贪心;

import java.util.*;

public class Solution3143 {
    static class Point {
        int[] position;
        char type;
        int rank;

        public Point(int[] position, char type) {
            this.position = position;
            this.type = type;
            this.rank = Math.max(Math.abs(position[0]), Math.abs(position[1]));
        }

        public int getRank() {
            return rank;
        }
    }

    public int maxPointsInsideSquare(int[][] points, String s) {
        if (s.isEmpty() || s.length()==1) return s.length();
        Queue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.rank - o2.rank;
            }
        });
        Set<Integer> rankList = new TreeSet<>();
        for (int i = 0; i < points.length; i++) {
            int[] p = points[i];
            Point point = new Point(p, s.charAt(i));
            rankList.add(point.getRank());
            queue.add(point);
        }
        StringBuilder sBuilder = new StringBuilder();
        for (Integer rank : rankList) {
            StringBuilder small = new StringBuilder();
            while (!queue.isEmpty() && queue.peek().rank == rank) {
                Point point = queue.poll();
                char type = point.type;
                if (sBuilder.indexOf(String.valueOf(type)) >= 0  || small.indexOf(String.valueOf(type)) >= 0) {
                    return sBuilder.length();
                }
                small.append(type);
            }
            sBuilder.append(small);
        }
        return sBuilder.length();
    }

    public static void main(String[] args) {
        int[][] points = {
                {-1000000000, -1000000000},
                {-1000000000, 1000000000}
        };
        String s = "zy";
        System.out.println(new Solution3143().maxPointsInsideSquare(points, s));
    }
}
