package com.leetcode2.org.状态压缩;


import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class FindSubsequence2BitSet {
    List<List<Integer>> find(int[] list) {
        int n = list.length;
        List<List<Integer>> ans = new ArrayList<>();

        // 总的子集数量为 2^n - 1（排除空集）
        for (int i = 1; i < (1 << n); i++) {
            // 创建BitSet并正确设置位
            BitSet bitSet = new BitSet(n);

            // 通过位运算设置BitSet
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    bitSet.set(j);
                }
            }

            // 根据BitSet生成子序列
            List<Integer> subSequence = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (bitSet.get(j)) {
                    subSequence.add(list[j]);
                }
            }

            ans.add(subSequence);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] list = new int[25];
        for (int i = 0; i < list.length; i++) {
            list[i] = i;
        }
        long start = System.currentTimeMillis();
        FindSubsequence2BitSet finder = new FindSubsequence2BitSet();
        List<List<Integer>> result = finder.find(list);
//        System.out.println("子序列：" + result);
//        System.out.println("子序列数量：" + result.size());
        long end = System.currentTimeMillis();
        System.out.println("位算法时间：");
        System.out.println((end - start) / 100 + "ms");
    }
}

//关键修改点：
//        1. 使用位运算 `(i & (1 << j)) != 0` 来确定哪些位需要在 BitSet 中设置
//2. 通过两次遍历实现子序列生成
//   - 第一次遍历：根据位运算设置 BitSet
//   - 第二次遍历：根据 BitSet 生成子序列
//
//这个版本既保留了 BitSet 的使用，又正确实现了子序列查找的逻辑。
//
//主要优势：
//        - 保留 BitSet 的位操作特性
//- 清晰展示位运算和 BitSet 的结合
//- 时间复杂度仍然是 O(2^n * n)
//
//希望这个实现符合你的要求！