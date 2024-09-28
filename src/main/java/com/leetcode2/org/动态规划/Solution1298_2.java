package com.leetcode2.org.动态规划;

import java.util.*;

public class Solution1298_2 {
    static class Box {
        int status;
        int[] keys;
        int index;
        int candies;
        List<Box> containedBoxes;

        public Box(int status, int[] keys, int candies, int index) {
            this.status = status;
            this.keys = keys;
            this.candies = candies;
            this.index = index;
            this.containedBoxes = new ArrayList<>();
        }

        public int getIndex() {
            return index;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int[] getKeys() {
            return keys;
        }

        public int getCandies() {
            return candies;
        }

        public List<Box> getContainedBoxes() {
            return containedBoxes;
        }
    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int length = status.length;
        List<Box> boxes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Box box = new Box(status[i], keys[i], candies[i], i);
            boxes.add(box);
        }
        for (int i = 0; i < length; i++) {
            Box box = boxes.get(i);
            for (int j = 0; j < containedBoxes[i].length; j++) {
                box.containedBoxes.add(boxes.get(containedBoxes[i][j]));
            }
        }
        List<Box> haveBox = boxes.stream().filter(box -> Arrays.stream(initialBoxes).anyMatch(i -> i == box.getIndex())).toList();
        Queue<Box> queue = new PriorityQueue<>((a, b) -> b.getStatus() - a.getStatus());
        queue.addAll(haveBox);
        int candiesSum = 0;
        while (!queue.isEmpty()) {
            Box box = queue.poll();
            if (box.getStatus() == 0) break;
            if (box.getStatus() == 1) {
                candiesSum += box.getCandies();
                Arrays.stream(box.getKeys()).forEach(index -> boxes.get(index).setStatus(1));
                queue.addAll(box.getContainedBoxes());
            }
        }
        return candiesSum;
    }

    public static void main(String[] args) {
        int[] status = {1, 0, 1, 0};
        int[] candies = {7, 5, 4, 100};
        int[][] keys = {{}, {}, {1}, {}};
        int[][] containedBoxes = {{2, 1}, {3}, {}, {}};
        int[] initialBoxes = {0};
        Solution1298 solution1298 = new Solution1298();
        System.out.println(solution1298.maxCandies(status, candies, keys, containedBoxes, initialBoxes));
    }
}