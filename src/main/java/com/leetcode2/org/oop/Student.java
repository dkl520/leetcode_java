package com.leetcode2.org.oop;

public class Student extends Person{
    String school;

    public Student(String name,int age, String school){
//        super();

        super(name,age);
        this.school=school;

    }
    public void eat(){
        System.out.println("学生多吃有营养的食物");
    }

    public void sleep(){
        System.out.println("学生要保证睡眠");
    }



}
