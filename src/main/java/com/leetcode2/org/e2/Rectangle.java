package com.leetcode2.org.e2;

public class Rectangle extends GeometricObject {
    private double with;
    private double height;

    public Rectangle(String color, double weight, double with, double height) {
        super(color, weight);
        this.with = with;
        this.height = height;
    }

    @Override
    public double findArea() {
        return with*height;
    }

    public double getWith() {
        return with;
    }

    public void setWith(double with) {
        this.with = with;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
