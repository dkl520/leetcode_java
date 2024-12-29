package com.leetcode2.org.状态压缩;

import java.util.ArrayList;
import java.util.List;

public class FindSubsequence3 {
        // 使用回溯算法来查找所有子序列
        List<List<Integer>> find(int[] list) {
            int n = list.length;  // 获取输入数组的长度
            List<List<Integer>> ans = new ArrayList<>();  // 用于存储所有子序列的结果
            List<Integer> subSequence = new ArrayList<>();  // 临时列表，用于存储当前的子序列
            // 从索引 0 开始，递归生成所有子序列
            backtrack(list, 0, subSequence, ans);
            return ans;
        }

        // 回溯方法：递归地找到所有子序列
        private void backtrack(int[] list, int start, List<Integer> subSequence, List<List<Integer>> ans) {
            // 每次递归时，都将当前的子序列添加到结果列表中
            ans.add(new ArrayList<>(subSequence));

            // 从当前索引 start 开始，尝试将后面的元素加入到子序列中
            for (int i = start; i < list.length; i++) {
                subSequence.add(list[i]);  // 将当前元素加入子序列
                backtrack(list, i + 1, subSequence, ans);  // 递归处理下一个元素
                subSequence.remove(subSequence.size() - 1);  // 回溯，移除当前元素，继续尝试其他可能的子序列
            }
        }

        public static void main(String[] args) {

            int[] list = new int[25];  // 测试数组
            for (int i = 0; i < list.length; i++) {
                list[i] = i;
            }
            long start = System.currentTimeMillis();
            FindSubsequence3 findSubsequence = new FindSubsequence3();  // 创建 FindSubsequence2 对象
            // 输出所有子序列
//            System.out.println(findSubsequence.find(list));
            long end = System.currentTimeMillis();
            System.out.println("回溯算法时间：");
            System.out.println((end - start) / 100 + "ms");

        }
    }
