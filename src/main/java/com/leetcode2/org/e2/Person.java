package com.leetcode2.org.e2;

public class Person {
    String name;
    private int age;
    int id=1001;

    public void eat() {
        System.out.println("人吃饭");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void sleep() {
        System.out.println("人睡觉奥");
    }
   private void show(){
       System.out.println("age:"+age);
    }
}
