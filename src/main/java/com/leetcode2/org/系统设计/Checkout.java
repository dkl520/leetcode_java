package com.leetcode2.org.系统设计;

import java.util.LinkedList;
import java.util.Queue;

public class Checkout {
    Queue<Integer> orderQueue;

    public Checkout() {
        orderQueue = new LinkedList<>();
    }

    public int get_max() {
        int max = -1;
        for (Integer good : orderQueue) {
            // 处理每个元素
           max= Math.max(max,good);
        }
        return max;
    }

    public void add(int value) {
        orderQueue.add(value);
    }

    public int remove() {
        if (orderQueue.isEmpty()) {
            return -1;
        }
        return orderQueue.poll();
    }
}