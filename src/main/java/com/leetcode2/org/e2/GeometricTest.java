package com.leetcode2.org.e2;

public class GeometricTest {
    public  boolean equalsArea (GeometricObject g1, GeometricObject g2){
        return g1.findArea() == g2.findArea();
    }
    public void displayGeometricObject(GeometricObject g){
        System.out.println("几何图形的面积为："+g.findArea());
    }

    public static void main(String[] args) {

        GeometricTest test1= new GeometricTest();

        Circle c1= new Circle("red",1,2.3);
        Circle c2= new Circle("red",1,3.3);
        test1.displayGeometricObject(c1);
        test1.displayGeometricObject(c2);
    }
}
