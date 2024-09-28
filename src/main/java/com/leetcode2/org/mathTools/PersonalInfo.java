package com.leetcode2.org.mathTools;
import java.math.BigDecimal;

class PersonalInfo {
    public static void main(String[] args) {
        System.out.println("姓名：康师傅");
        System.out.println();
        System.out.println("性别：男");
        System.out.println("家庭住址：北京程序员聚集地：回龙观");

        int age =12;
        char gender;
        gender ='男';
        System.out.println("age+="+age);

        byte b1=12;
        byte b2 =127;
//        byte b3= 128;
        int s1= 1111;
        long l1= 32324434;
        long l2= 121231323l;
        System.out.println(l2);

        float f2 =1232.122f;
        System.out.println(f2);
        float f3 =3434.43435f;
        System.out.println(f2+f3);
        BigDecimal d1 = new BigDecimal("0.1");
        BigDecimal d2 = new BigDecimal("0.2");
        BigDecimal multiple1 = new BigDecimal("1213232342.324343");
        BigDecimal multiple2= new BigDecimal("8954385.2322323");
//        System.out.println(d1.multiply(d2));
        System.out.println(multiple1.multiply(multiple2));

//        System.out.println("Hello world!， 你好中国");
    }
}
