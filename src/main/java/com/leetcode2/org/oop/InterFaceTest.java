package com.leetcode2.org.oop;

public class InterFaceTest {

    public void performActions() {
        System.out.println(Flyable.MAX_SPEED);
        System.out.println(Flyable.MIN_SPEED);

        Bullet b1 = new Bullet();
        b1.fly();
        b1.attack();
    }


    public static void main(String[] args) {
        InterFaceTest test = new InterFaceTest();
        test.performActions();
    }

    interface Flyable {
        int MAX_SPEED = 7900;
        int MIN_SPEED = 0; // Changed to a valid positive value
        void fly();       // Corrected the case of 'Void' to 'void'
    }

    interface Attackable {
        void attack();
    }

    abstract class Plane implements Flyable {
    }

    class Bullet implements Flyable, Attackable {
        @Override
        public void fly() {
            System.out.println("让子弹飞");
        }

        public void attack() {
            System.out.println("子弹击穿");
        }
    }
}
