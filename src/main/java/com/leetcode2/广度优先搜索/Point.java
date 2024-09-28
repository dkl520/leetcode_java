package com.leetcode2.广度优先搜索;

public class Point {
    int x;
    int y;
    double distance;

    public Point(int x, int y, double distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", distance=" + distance +
                '}';
    }
}