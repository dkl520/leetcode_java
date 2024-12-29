package com.leetcode2.org.大厂.tiktok;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TIKTOK_6 {

    int calcSubArray(int[] arr, int k) {
        List<List<Integer>> listAll = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = new ArrayList<Integer>();
            for (int j = i; j < arr.length; j++) {
                list.add(arr[j]);
                listAll.add(new ArrayList<>(list));
            }
        }
        List<Integer> result = new ArrayList<>(
                listAll.stream().map(
                li -> li.stream().reduce(0, (acc, v) -> acc | v)
        ).toList());

        result.sort(Collections.reverseOrder());
        int count = k;
        while (!result.isEmpty() && count-1 > 0) {
             result.remove(0);
             count--;
        }
        return result.isEmpty() ? -1 : result.get(0);

    }


    public static void main(String[] args) {
        TIKTOK_6 t = new TIKTOK_6();
        int[] arr = new int[]{3, 2, 7, 1};
        int k = 5;
        System.out.println(t.calcSubArray(arr, k));
    }

}

//
//public class EngagementScore {
//    // 找出所有连续子数组并计算互动强度
//    public static List<Integer> findAllSubarrayStrengths(int[] arr) {
//        List<Integer> strengths = new ArrayList<>();
//        int n = arr.length;
//
//        // 遍历所有可能的起始位置和结束位置
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                // 计算当前子数组的OR值
//                int strength = calculateOrStrength(arr, i, j);
//                strengths.add(strength);
//            }
//        }
//        return strengths;
//    }
//
//    // 计算子数组的OR值
//    private static int calculateOrStrength(int[] arr, int start, int end) {
//        int result = arr[start];
//        for (int i = start + 1; i <= end; i++) {
//            result |= arr[i];
//        }
//        return result;
//    }
//
//    // 找出第k大的互动强度
//    public static int findKthLargestStrength(int[] arr, int k) {
//        // 获取所有互动强度
//        List<Integer> strengths = findAllSubarrayStrengths(arr);
//
//        // 排序（从大到小）
//        strengths.sort(Collections.reverseOrder());
//
//        // 返回第k大的值
//        return strengths.get(k - 1);
//    }
//
//    // 打印所有子数组及其互动强度（用于调试）
//    public static void printAllSubarrays(int[] arr) {
//        int n = arr.length;
//        System.out.println("所有子数组及其互动强度：");
//
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                System.out.print("[");
//                for (int k = i; k <= j; k++) {
//                    System.out.print(arr[k]);
//                    if (k < j) System.out.print(", ");
//                }
//                System.out.print("] = ");
//                System.out.println(calculateOrStrength(arr, i, j));
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        // 测试用例
//        int[] arr = {3, 2, 7, 1};
//        int k = 5;
//
//        // 打印所有子数组及其互动强度
//        printAllSubarrays(arr);
//
//        // 获取并打印第k大的互动强度
//        int result = findKthLargestStrength(arr, k);
//        System.out.println("\n第 " + k + " 大的互动强度是：" + result);
//
//        // 打印所有互动强度（从大到小排序）
//        List<Integer> allStrengths = findAllSubarrayStrengths(arr);
//        allStrengths.sort(Collections.reverseOrder());
//        System.out.println("\n所有互动强度从大到小排序：" + allStrengths);
//    }
//}
//









