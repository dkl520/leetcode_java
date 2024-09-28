package com.leetcode2.org.树状数组;

import java.util.*;

public class Solution683 {
    static class BinaryIndexTree {
        List<Integer> list = new ArrayList<Integer>();
        int size = 0;

        public BinaryIndexTree(int num) {
            this.list = new ArrayList<>(Collections.nCopies(num + 1, 0));
            this.size = num + 1;
        }

        void update(int rank, int num) {
            while (rank < this.size) {
                list.set(rank, list.get(rank) + num);
                rank += rank & -rank;
            }
        }

        int query(int rank) {
            int result = 0;
            while (rank > 0) {
                result += list.get(rank);
                rank -= rank & -rank;
            }
            return result;
        }
    }

    int kEmptySlots(int[] bulbs, int k) {
        int size = bulbs.length;
        BinaryIndexTree tree = new BinaryIndexTree(size);
        int dis = k + 1;
        for (int i = 0; i < bulbs.length; i++) {
            int rank = bulbs[i];
            if (rank - dis >= 1) {
                if (tree.query(rank - dis) - tree.query(rank - dis - 1) > 0) return i + 1;
            }
            if (rank + dis <= size) {
                if (tree.query(rank + dis) - tree.query(rank + dis - 1) > 0) return i + 1;
            }
            tree.update(rank, 1);
        }
        return -1;
    }

    public static void main(String[] args) {
//        int
        int[] bulbs = {1, 3, 2};
        int k = 1;
        Solution683 solution = new Solution683();
        System.out.println(
                solution.kEmptySlots(bulbs, k)
        );

    }
}
