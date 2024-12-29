package com.leetcode2.org.系统设计;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyCircularDeque {
    Deque<Integer> deque;
    int n;

    public MyCircularDeque(int k) {
        deque = new ArrayDeque<>(k);
        n = k;
    }

    public boolean insertFront(int value) {
        return deque.offerFirst(value);
    }

    public boolean insertLast(int value) {
        return deque.offerLast(value);
    }

    public boolean deleteFront() {
        return deque.pollFirst() != null;

    }

    public boolean deleteLast() {
        return deque.pollLast() != null;
    }

    public int getFront() {
        return deque.peekFirst();
    }

    public int getRear() {
        return deque.peekLast();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public boolean isFull() {
        return deque.size() == n;
    }
}
