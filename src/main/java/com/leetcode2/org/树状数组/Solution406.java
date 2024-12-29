package com.leetcode2.org.树状数组;

import java.util.Arrays;

public class Solution406 {

    // 树状数组类，支持二分查找
    static class BinaryIndexTree {
        private int[] cnt;
        private final int n;

        // 构造方法，初始化树状数组的大小
        public BinaryIndexTree(int n) {
            this.n = n;
            cnt = new int[n + 1]; // 增加一个哨兵位，方便操作
        }

        // 查询[0, index)的和
        public int queryUntil(int index) {
            int ans = 0;
            while (index > 0) {
                ans += cnt[index];
                index -= Integer.lowestOneBit(index); // 向上取最低的1位
            }
            return ans;
        }

        // 在指定索引上加上差值diff
        public void add(int index, int diff) {
            ++index; // 索引调整为1-based
            while (index <= n) {
                cnt[index] += diff;
                index += Integer.lowestOneBit(index); // 向上取最低的1位
            }
        }
    }

    // 重构队列方法
    public int[][] reconstructQueue(int[][] people) {
        // 按照身高升序，若身高相同，则按人数降序
        Arrays.sort(people, (o1, o2) -> {
            return o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o2[1], o1[1]);
        });

        int n = people.length;
        BinaryIndexTree binaryIndexTree = new BinaryIndexTree(n); // 初始化树状数组
        int[][] ans = new int[n][];

        for (int[] person : people) {
            int left = 0;
            int right = n;
            int mid;
            // 二分查找第一个位置，使得 `mid - binaryIndexTree.queryUntil(mid)` >= person[1] + 1
            while (left < right) {
                mid = (left + right) >>> 1;
                int cnt = mid - binaryIndexTree.queryUntil(mid); // mid到n的前缀和
                if (cnt < person[1] + 1) { // 位置不够，需要继续右移
                    left = ++mid;
                } else {
                    right = mid;
                }
            }
            // 将当前人的位置放在ans的对应位置
            ans[left - 1] = Arrays.copyOf(person, person.length); // left - 1 是实际的位置
            binaryIndexTree.add(left - 1, 1); // 在树状数组中标记位置
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] people = {
                {7, 0},
                {4, 4},
                {7, 1},
                {5, 0},
                {6, 1},
                {5, 2}
        };
        Solution406 solution406 = new Solution406();
        System.out.println(Arrays.deepToString(solution406.reconstructQueue(people)));
    }
}
