package com.leetcode2.org.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigNum {
    public static void main(String[] args) {
        BigDecimal limit = new BigDecimal("1e100");
        ; // 设置搜索范围的上限
        System.out.println("limit" + limit);

        for (BigDecimal a = BigDecimal.ONE; a.compareTo(limit) <= 0; a = a.add(BigDecimal.ONE)) {
            for (BigDecimal b = BigDecimal.ONE; b.compareTo(limit) <= 0; b = b.add(BigDecimal.ONE)) {
                for (BigDecimal c = BigDecimal.ONE; c.compareTo(limit) <= 0; c = c.add(BigDecimal.ONE)) {
                    BigDecimal denominatorBC = b.add(c);
                    BigDecimal denominatorBA = b.add(a);
                    BigDecimal denominatorAC = c.add(a);
                    int scale = 10; // 设置小数位数

                    RoundingMode roundingMode = RoundingMode.HALF_UP; // 设置舍入模式
                    BigDecimal numerator = a.divide(denominatorBC, scale, roundingMode).add(b.divide(denominatorAC, scale, roundingMode)).add(c.divide(denominatorBA, scale, roundingMode));

                    System.out.println(numerator);
                    System.out.println("a: " + a);
                    if (BigDecimal.valueOf(4).equals(numerator)) {
                        System.out.println("a: " + a);
                        System.out.println("b: " + b);
                        System.out.println("c: " + c);
                        return;
                    }
                }
            }
        }
//        for (BigInteger a = BigInteger.ONE; a.compareTo(limit) <= 0; a = a.add(BigInteger.ONE)) {
//            for (BigInteger b = BigInteger.ONE; b.compareTo(limit) <= 0; b = b.add(BigInteger.ONE)) {
//                for (BigInteger c = BigInteger.ONE; c.compareTo(limit) <= 0; c = c.add(BigInteger.ONE)) {
//                    BigInteger denominatorBC = b.add(c);
//                    BigInteger denominatorBA = b.add(a);
//                    BigInteger denominatorAC = c.add(a);
//
//                    BigInteger numerator = (a.divide(denominatorBC)).add(b.divide(denominatorAC)).add(c.divide(denominatorBA));
//
//                    System.out.println(numerator);
//                    if (BigInteger.valueOf(4).equals(numerator)) {
//                        System.out.println("a: " + a);
//                        System.out.println("b: " + b);
//                        System.out.println("c: " + c);
//                        return;
//                    }
//                }
//            }
//        }
    }
}
