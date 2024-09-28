package com.leetcode2.org.堆;

import java.util.*;

public class calculateTikTokShoppingCost {


    public static long calculateTikTokShoppingCost2(int vouchersCount, List<Integer> prices) {

        PriorityQueue<Long> priorityQueue = new PriorityQueue<>((a, b) -> Math.toIntExact(b - a));
        for (Integer price : prices) {
            priorityQueue.offer(Long.valueOf(price));
        }

        while (vouchersCount > 0) {
            long cur = priorityQueue.poll();
            long newCur = cur / 2;
            priorityQueue.offer(newCur);
            vouchersCount--;
        }
        long result = 0;
        while (!priorityQueue.isEmpty()) {
            long cur = priorityQueue.poll();
            result += cur;
        }
        return result;
    }


    public static void main(String[] args) {
        List<Integer> prices = Arrays.asList(8, 2, 13);

        int vouchersCount = 3;

        System.out.println(
                calculateTikTokShoppingCost2(vouchersCount, prices)
        );

    }

}
