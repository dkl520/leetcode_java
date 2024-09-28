package com.leetcode2.数组;

import java.util.*;

public class Solution66 {

//    public int[] plusOne(int[] digits) {
//        Deque<Integer> list = new ArrayDeque<>();
//        int carry = 0;
//        for (int i = digits.length - 1; i >= 0; i--) {
//            if (i == digits.length - 1) {
//                if (digits[i] != 9) {
//                    list.addFirst(digits[i] + carry + 1);
//                    carry = 0;
//                } else {
//                    list.addFirst(0);
//                    carry = 1;
//                }
//                continue;
//            }
//            if (digits[i] != 9) {
//                list.addFirst(digits[i] + carry);
//                carry = 0;
//            } else {
//                if (carry == 1) {
//                    list.addFirst(0);
//                } else {
//                    list.addFirst(digits[i]);
//                }
//            }
//        }
//        if (carry == 1) list.addFirst(1);
//        return list.stream().mapToInt(i -> i).toArray();
//    }

    public int[] plusOne(int[] digits) {
        Deque<Integer> list = new ArrayDeque<>();
        int n = digits.length;
        int[] digitsPlus = new int[n + 1];  // 创建新数组，长度比 a1 多 1
        System.arraycopy(digits, 0, digitsPlus, 1, n);  // 将 a1 的元素从索引 1 开始复制到 a2
        int carry = 0;
        digitsPlus[n] += 1;
        while (n >= 0) {
            int num = digitsPlus[n];
            if (num == 10) {
                list.addFirst(0);
                digitsPlus[n - 1] += 1;
            } else {
                list.addFirst(num);
            }
            n--;
            if (n == 0 && digitsPlus[n] == 0) break;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] digits = new int[]{1, 2, 4};
        Solution66 solution66 = new Solution66();
        System.out.println(Arrays.toString(solution66.plusOne(digits)));
    }

}
