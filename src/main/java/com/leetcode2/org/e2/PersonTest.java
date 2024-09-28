package com.leetcode2.org.e2;

public class PersonTest {
    public static void main(String[] args) {
//        Person p1 = new Person();
//        p1.name = "tom";
//        p1.eat();
////        p1.show();
//        Student s1 = new Student();
//        s1.name = "tom2";
//        s1.show();
////        s1.age
//        System.out.println(s1.getName());
//        System.out.println(s1.getClass());
//        System.out.println(s1.getClass().getClass());//类名
//        System.out.println(s1.getClass().getSuperclass());//  超级类 类的父类
//        s1.eat();
            Person p1= new Person();

            Person p2 = new Man();
                p2.eat();
                Man m1= (Man)p2;
//                p2.walk();
              System.out.println(p2.id);



    }
}
