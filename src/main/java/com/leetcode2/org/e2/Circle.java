package com.leetcode2.org.e2;

public class Circle extends GeometricObject {
  private   double radius;
//  private  String color;
    double PI = 3.1415926;


    public Circle(String color, double weight, double radius) {
        super(color, weight);
        this.radius = radius;
    }
    @Override
    public double findArea(){
        return  radius*radius*PI;
    }
}
