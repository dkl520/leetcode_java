package com.leetcode2.org.oop;

public class Employy {
    private int age;
    private String name;
    private char gender;

    public Employy(String name,int age, char gender) {
        this.name= name;
        this.age= age;
        this.gender=gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
