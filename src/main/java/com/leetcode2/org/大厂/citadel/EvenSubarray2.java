package com.leetcode2.org.大厂.citadel;


public class EvenSubarray2 {

    public int getEvenSubarray(int[] list, int k) {
        int left = 0, countOdd = 0, result = 0;
        // 遍历数组，用 right 指针扩展窗口
        for (int right = 0; right < list.length; right++) {
            if (list[right] % 2 == 1) {
                countOdd++; // 当前元素是奇数，增加奇数计数
            }
            // 如果奇数个数超过 k，移动左指针缩小窗口
            while (countOdd > k) {
                if (list[left] % 2 == 1) {
                    countOdd--; // 移除左边的奇数时，更新计数
                }
                left++;
            }
            // 以 right 为结尾的合法子数组的数量
            result += (right - left + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4};
        int k = 1;

        EvenSubarray evenSubarray = new EvenSubarray();
        System.out.println(evenSubarray.getEvenSubarray(list, k)); // 输出: 8
    }
}
