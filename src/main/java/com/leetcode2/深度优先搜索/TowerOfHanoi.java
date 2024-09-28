package com.leetcode2.深度优先搜索;

import java.util.Stack;

public class TowerOfHanoi {

    // 打印移动步骤并将盘子从一个栈移动到另一个栈
    private static void move(Stack<Integer> from, Stack<Integer> to, char fromRod, char toRod) {
        int disk = from.pop();
        to.push(disk);
        System.out.println("Move disk " + disk + " from " + fromRod + " to " + toRod);
    }

    // 递归解决汉诺塔问题
    public static void solveHanoi(int n, Stack<Integer> from, Stack<Integer> aux, Stack<Integer> to, char fromRod, char auxRod, char toRod) {
        // 递归终止条件：只有一个盘子时，直接移动到目标柱
        if (n == 1) {
            move(from, to, fromRod, toRod);
            return;
        }

        // 将前 n-1 个盘子从源柱移动到辅助柱
        solveHanoi(n - 1, from, to, aux, fromRod, toRod, auxRod);

        // 将第 n 个盘子从源柱移动到目标柱
        move(from, to, fromRod, toRod);

        // 将前 n-1 个盘子从辅助柱移动到目标柱
        solveHanoi(n - 1, aux, from, to, auxRod, fromRod, toRod);
    }

    public static void main(String[] args) {
        int n = 5; // 盘子的数量
        Stack<Integer> rodA = new Stack<>();
        Stack<Integer> rodB = new Stack<>();
        Stack<Integer> rodC = new Stack<>();
        // 将盘子放入源柱，盘子从大到小 (5到1)
        for (int i = n; i >= 1; i--) {
            rodA.push(i);
        }
        // 打印初始状态
        System.out.println("Initial state:");
        printRods(rodA, rodB, rodC);
        // 解决汉诺塔问题
        solveHanoi(n, rodA, rodB, rodC, 'A', 'B', 'C');
        // 打印最终状态
        System.out.println("Final state:");
        printRods(rodA, rodB, rodC);
    }

    // 打印三个杆子的状态
    private static void printRods(Stack<Integer> rodA, Stack<Integer> rodB, Stack<Integer> rodC) {
        System.out.println("Rod A: " + rodA);
        System.out.println("Rod B: " + rodB);
        System.out.println("Rod C: " + rodC);
    }
}
