package com.leetcode2.org.thread;

public class ChineseTest {
    public static void main(String[] args) {
        Chinese c1 = new Chinese();
        c1.name = "姚明";
        c1.age = 40;
        Chinese c2 = new Chinese();
        c2.name = "刘翔";
        c2.age = 39;
        System.out.println(c1);
        System.out.println(c2);
        c1.nation="china";
        System.out.println(c1.nation);
        System.out.println(c2.nation);
    }


}


class Chinese {
    String name;
    int age;
    static String nation;

    @Override
    public String toString() {
        return "Chinese{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}