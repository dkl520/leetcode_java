package com.leetcode2.org.大厂;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GetMaxThreeNum {
    int calc(List<Integer> list) {
        List<int[]> results = new ArrayList<>();
        int[] resultOne = new int[3];  // 用于存储当前的组合
        trackBack(list, results, 0, 0, resultOne);  // 开始回溯
        return results.stream().mapToInt(arr -> arr[0] * 100 + arr[1] * 10 + arr[2]).max().orElse(-1);
    }

    void trackBack(List<Integer> list, List<int[]> results, int startList, int startArray, int[] resultOne) {
        if (startArray == 3) {
            results.add(Arrays.copyOf(resultOne, 3));  // 当达到 3 个元素时，将当前组合加入结果集
            return;  // 结束当前递归
        }

        // 从 startList 开始，避免重复选择相同元素
        for (int i = startList; i < list.size(); i++) {
            resultOne[startArray] = list.get(i);  // 选择当前元素
            trackBack(list, results, i + 1, startArray + 1, resultOne);  // 递归选择下一个元素
            resultOne[startArray] = 0;  // 回溯后，重置当前选择的元素
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>(List.of(7, 2, 3, 3, 4, 9));
        GetMaxThreeNum obj = new GetMaxThreeNum();
        System.out.println(obj.calc(list));
    }
}
