package com.leetcode2.org;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SolutionQ3 {
    public int minLength(String s, int numOps) {
        int n = s.length();

        List<Integer> list = new ArrayList<>();
        int count = 0;
        for (int i = 1; i < n; i++) {
            count++;
            char pre = s.charAt(i - 1);
            char cur = s.charAt(i);
            if (pre != cur) {
                list.add(count);
                count = 0;
            }
        }
       list.add(count+1);

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        queue.addAll(list);

        while (numOps > 0) {
            int cur = queue.poll();
            int newCur = cur / 2;
            queue.offer(newCur);
            numOps--;
        }
        int result = queue.poll();
        return result == 0 ? 1 : result;

    }

    public static void main(String[] args) {
        String s = "0101";
        SolutionQ3 q = new SolutionQ3();
        System.out.println(
                q.minLength(s, 0)
        );

    }
}
