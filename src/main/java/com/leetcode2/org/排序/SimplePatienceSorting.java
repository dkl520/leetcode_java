package com.leetcode2.org.排序;

import java.util.*;

public class SimplePatienceSorting {
    public static int[] patienceSort(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;

        List<List<Integer>> piles = new ArrayList<>();

        for (int num : arr) {
            boolean placed = false;
            for (List<Integer> pile : piles) {
                if (num <= pile.get(pile.size() - 1)) {
                    pile.add(num);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                List<Integer> newPile = new ArrayList<>();
                newPile.add(num);
                piles.add(newPile);
            }
        }

        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>((a, b) -> a.get(a.size() - 1) - b.get(b.size() - 1));
        minHeap.addAll(piles);

        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            List<Integer> pile = minHeap.poll();
            result.add(pile.remove(pile.size() - 1));
            if (!pile.isEmpty()) {
                minHeap.offer(pile);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("原始数组: " + Arrays.toString(arr));

        int[] sortedArr = patienceSort(arr);
        System.out.println("排序后数组: " + Arrays.toString(sortedArr));
    }
}