package com.leetcode2.org.数组;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Solution2535 {
    public int differenceOfSum(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        List<Integer> strs = Arrays.stream(nums)  // 将整数数组 nums 转换为一个流（Stream），用于后续操作
                .mapToObj(Integer::toString)         // 将每个整数转换为字符串形式，返回的是一个字符串流（Stream<String>）
                .flatMap(str ->                      // 对每个字符串进行处理，返回一个包含字符的流，并展平结果
                        str.chars()                      // 将字符串转换为 IntStream，每个字符的 ASCII 码或 Unicode 码作为 int 值
                                .mapToObj(ch ->              // 将每个字符的 int 值转换为对象（这里是数字）
                                        Character.digit(ch, 10)  // 使用 Character.digit(ch, 10) 将字符转换为相应的数字
                                )
                )
                .toList();                           // 将展平的流转换为一个 List<Integer> 列表，最终结果是每个整数的数字列表
        return Math.abs(sum - strs.stream().mapToInt(Integer::valueOf).sum());
    }

    public static void main(String[] args) {
        Solution2535 solution = new Solution2535();
        System.out.println(solution.differenceOfSum(new int[]{1, 15, 6, 3}));
    }
}
