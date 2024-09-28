package com.leetcode2.org.enum2;

public class Employee {
    private String name;
    private int age;
    private double Salary;
    private  int id;

    public Employee() {

    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Employee(String name, int age, double salary, int id) {
        this.name = name;
        this.age = age;
        Salary = salary;
        this.id = id;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", Salary=" + Salary +
                ", id=" + id +
                '}';
    }
}
