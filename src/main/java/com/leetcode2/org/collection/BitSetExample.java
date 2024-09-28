package com.leetcode2.org.collection;

import java.util.BitSet;

public class BitSetExample {
    public static void main(String[] args) {
        // 示例: 将 BitSet 转换为整数
        BitSet a = BitSet.valueOf(new long[]{5});  // 二进制表示：0101
        BitSet b = BitSet.valueOf(new long[]{3});  // 二进制表示：0011

        System.out.println(a);
           a.and(b);
        System.out.println(a);
        System.out.println(b);

        BitSet c= BitSet.valueOf(new long[]{1,2,3});
        BitSet d= BitSet.valueOf(new long[]{1,2,3});
//        c.and(d);
        System.out.println("BitSet c: " + c);
        System.out.println("c[0] (64 bits): " + Long.toBinaryString(c.toLongArray()[0]));
        System.out.println("c[1] (64 bits): " + Long.toBinaryString(c.toLongArray()[1]));
        System.out.println("c[2] (64 bits): " + Long.toBinaryString(c.toLongArray()[2]));
        System.out.println(c);
    }
}
