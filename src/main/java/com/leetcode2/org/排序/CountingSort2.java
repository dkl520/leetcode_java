package com.leetcode2.org.排序;


import java.util.Arrays;
import java.util.stream.IntStream;

public class CountingSort2 {
    /**
     * 对字符串中的字符进行计数排序，并返回排序后的字符串。
     * 注意：本实现只考虑了'A'到'z'之间的字符，并忽略了大小写和非字母字符。
     *
     * @param str 输入的字符串
     * @return 排序后的字符串
     */
    String sort(String str) {
        int[] count = new int[128]; // 创建一个长度为128的数组，用于计数ASCII字符集中的字符

        // 遍历字符串中的每个字符，统计'A'到'z'之间的字符出现次数
        str.chars()
                .filter(c -> c >= 'A' && c <= 'z') // 过滤出'A'到'z'之间的字符
                .forEach(c -> count[c]++); // 计数

        // 使用IntStream生成'A'到'z'的字符流，过滤出出现次数大于0的字符
        // 然后根据出现次数进行降序排序，如果次数相同则按字符的ASCII码升序排序
        // 最后将排序后的字符拼接成字符串并返回
        return IntStream.range('A', 'z' + 1)
                .filter(c -> count[c] > 0) // 过滤出现次数大于0的字符
                .boxed() // 将IntStream转换为Stream<Integer>
                .sorted((a, b) -> count[b] - count[a] != 0 ? count[b] - count[a] : a - b) // 自定义排序规则
                .collect(StringBuilder::new, // 初始化StringBuilder用于拼接字符
                        (sb, c) ->{
                            sb.append(
                                    String.valueOf((char) c.intValue()).repeat(count[c])
                            );
                        }
                      , // 将每个字符添加到StringBuilder中
                        StringBuilder::append) // 合并两个StringBuilder
                .toString(); // 将StringBuilder转换为字符串
    }

    public static void main(String[] args) {
        String str = "This is a sample string";
        CountingSort2 countingSort = new CountingSort2();
        // 输出排序后的字符串，排序依据是字符的出现次数（降序），如果次数相同则按字符的ASCII码升序排序
        System.out.println(countingSort.sort(str));
        System.out.println((int)'A' );
    }
}