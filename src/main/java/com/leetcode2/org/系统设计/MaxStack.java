package com.leetcode2.org.系统设计;

import java.util.*;

public class MaxStack {
    private Deque<Integer> stack;
    private TreeMap<Integer, List<Integer>> treeMap; // 值 -> 该值在栈中的索引列表
    private int idx; // 用于生成唯一索引
    private Set<Integer> removeLast;

    public MaxStack() {
        stack = new LinkedList<>();
        treeMap = new TreeMap<>();
        removeLast = new HashSet<>();
        idx = -1;
    }

    public void push(int x) {
        idx++;
        stack.addLast(x);

        treeMap.computeIfAbsent(x, k -> new ArrayList<>()).add(idx);
    }

    public int pop() {
        while (removeLast.contains(idx)) {
            removeLast.remove(idx);
              this.stack.removeLast();
            idx--;
        }
        idx--;
        int val = stack.removeLast();
        List<Integer> indices = treeMap.get(val);
        indices.remove(indices.size() - 1);
        if (indices.isEmpty()) {
            treeMap.remove(val);
        }
        return val;
    }

    public int top() {
        while (removeLast.contains(idx)) {
            removeLast.remove(idx);
            this.stack.removeLast();
            idx--;
        }
        return stack.getLast();
    }

    public int peekMax() {
        return treeMap.lastKey();
    }

    public int popMax() {
        int max = treeMap.lastKey();
        List<Integer> indices = treeMap.get(max);
        int lastIndex = indices.remove(indices.size() - 1);

        // 从栈中移除最大值
        if (lastIndex == this.stack.size() - 1) {
            this.stack.removeLast();
            idx--;
        } else {
            removeLast.add(lastIndex);
        }

        // 如果没有更多的最大值，从TreeMap中移除
        if (indices.isEmpty()) {
            treeMap.remove(max);
        }

        return max;
    }

}