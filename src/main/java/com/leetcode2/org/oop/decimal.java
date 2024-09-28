package com.leetcode2.org.oop;
import java.math.BigDecimal;
import java.math.BigInteger;

public class decimal {
    public static void main(String[] args) {
//        Decimal num=12;
        BigInteger num1 = new BigInteger("12");
        BigInteger num2 = new BigInteger("12");
//        double num1 = 12;
//        double num2 = 12;
        Employy e= new Employy("Jarvan",12,'男');
//           e.getAge(12);
        BigDecimal result = new BigDecimal(num1.pow(num2.intValue()));

        System.out.println("Result: " + result.toPlainString());
    }
}
