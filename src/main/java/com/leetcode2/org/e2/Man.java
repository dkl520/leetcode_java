package com.leetcode2.org.e2;



public class Man extends Person {
    boolean isSmoking;
    int id=1002;
@Override
    public  void eat(){
        System.out.println("男人多吃肉");
    }

    public void walk(){
        System.out.println("男人笔挺的走路");
    }

    public void earnMoney(){
        System.out.println("男人挣钱杨家");
    }
}
