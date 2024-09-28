package com.leetcode2.org.动态规划;

import java.util.*;

public class Solution1298 {
    static class Box {
        int status;
        int[] keys;
        int index;
        int candies;
        Box[] containedBoxes;

        public Box(int status, int[] keys, int candies, int index) {
            this.status = status;
            this.keys = keys;
            this.candies = candies;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
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

        public void setKeys(int[] keys) {
            this.keys = keys;
        }

        public int getCandies() {
            return candies;
        }

        public void setCandies(int candies) {
            this.candies = candies;
        }

        public Box[] getContainedBoxes() {
            return containedBoxes;
        }

        public void setContainedBoxes(Box[] containedBoxes) {
            this.containedBoxes = containedBoxes;
        }
    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int length = status.length;
        List<Box> boxes = new ArrayList<Box>();
        for (int i = 0; i < length; i++) {
            Box box = new Box(status[i], keys[i], candies[i], i);
            boxes.add(box);
        }
        for (int i = 0; i < length; i++) {
            Box box = boxes.get(i);
            List<Box> listBoxes = new ArrayList<Box>();
            for (int j = 0; j < containedBoxes[i].length; j++) {
                listBoxes.add(boxes.get(containedBoxes[i][j]));
            }
            if (!listBoxes.isEmpty()) {
                box.setContainedBoxes(listBoxes.toArray(Box[]::new));
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
                if (box.getContainedBoxes() != null) {
                    queue.addAll(Arrays.stream(box.getContainedBoxes()).toList());
                }
            }
        }
        return candiesSum;
    }

    public static void main(String[] args) {
        // 转换 status 列表
        int[] status = {1, 0, 1, 0};
        // 转换 candies 列表
        int[] candies = {7, 5, 4, 100};
        // 转换 keys 列表
        int[][] keys = {
                {},        // 空列表
                {},        // 空列表
                {1},       // 包含一个元素 1 的列表
                {}         // 空列表
        };
        int[][] containedBoxes = {
                {1, 2},    // 包含元素 1 和 2 的列表
                {3},       // 包含一个元素 3 的列表
                {},        // 空列表
                {}         // 空列表
        };

        // 转换 initialBoxes 列表
        int[] initialBoxes = {0};
        Solution1298 solution1298 = new Solution1298();
        System.out.println(solution1298.maxCandies(status, candies, keys, containedBoxes, initialBoxes));

    }
}
