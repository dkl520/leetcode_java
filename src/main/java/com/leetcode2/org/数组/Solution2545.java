package com.leetcode2.org.数组;


import java.util.Arrays;

public class Solution2545 {


    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> b[k] - a[k]);

        return score;
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 1, 2, 4};
        Arrays.sort(arr, (o1, o2) -> {
            if (o1 == 1) return -1;
            if (o2 == 1) return 1;
            return o2.compareTo(o1);
//            compare(o1, o2) 的返回值意义：
//            - 返回负数：表示o1应该排在o2前面
//                    - 返回正数：表示o1应该排在o2后面
//                    - 返回0：表示o1和o2相等// 正常升序排序
        });
        System.out.println(Arrays.toString(arr));

    }
}
