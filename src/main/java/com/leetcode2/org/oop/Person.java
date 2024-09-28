package com.leetcode2.org.oop;

public class Person {
    private int age;
    private String name;
    private char gender;

    Person(){

    }
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    static  void getJob(){
        System.out.println("得到一个工作");
    }
    void sleep(){
        System.out.println("人睡觉");
    }
    void eat(){
        System.out.println("人吃饭");
    }
     void console() {
        System.out.println("当前人的姓名：" + name + "  年龄:" + age + " 性别：" + gender);
    }
}
