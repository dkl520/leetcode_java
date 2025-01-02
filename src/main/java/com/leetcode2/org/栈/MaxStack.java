package com.leetcode2.org.栈;

import java.util.Comparator;
import java.util.TreeSet;


public class MaxStack {
    TreeSet<int[]> treeIndex;
    TreeSet<int[]> treeValue;
    Comparator<int[]> comp = (a, b) -> {
        return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
    };
    int cnt = 0;

    public MaxStack() {

        this.treeIndex = new TreeSet<>(comp);
        this.treeValue = new TreeSet<>(comp);
        this.cnt = 0;
    }


    public int pop() {
        int[] pair = this.treeIndex.pollLast();
        this.treeValue.remove(new int[]{pair[1], pair[0]});

        return pair[1];
    }


    public int popMax() {
        int[] pair = this.treeValue.pollLast();
        this.treeIndex.remove(new int[]{pair[1], pair[0]});

        return pair[0];
    }


    public void push(int x) {
        this.treeIndex.add(new int[]{cnt, x});
        this.treeValue.add(new int[]{x, cnt});
        this.cnt++;

    }

    public int peekMax() {
        return this.treeValue.last()[0];
    }

    public int top() {
        return this.treeIndex.last()[1];
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        maxStack.push(5);

        System.out.println(maxStack.top());      // 输出: 5
        System.out.println(maxStack.popMax());  // 输出: 5
        System.out.println(maxStack.top());      // 输出: 1
        System.out.println(maxStack.peekMax()); // 输出: 5
        System.out.println(maxStack.pop());     // 输出: 1
        System.out.println(maxStack.top());      // 输出: 5


    }


}


/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

