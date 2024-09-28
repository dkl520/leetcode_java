package com.leetcode2.org.栈;

import java.util.*;

public class Solution853 {
    static class Car {
        int position;
        int speed;
        int index;

        public Car(int position, int speed, int index) {
            this.position = position;
            this.speed = speed;
            this.index = index;
        }
    }

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        List<Car> cars = new ArrayList<Car>();
        for (int i = 0; i < n; i++) {
            Car car = new Car(position[i], speed[i], i);
            cars.add(car);
        }
        cars.sort((a, b) -> a.position - b.position);
        Stack<Car> stack = new Stack<Car>();
        int count = cars.size();
        for (Car car : cars) {
            while (!stack.isEmpty() && stack.peek().speed > car.speed) {
                Car preCar = stack.peek();
                double time = (double) (car.position - preCar.position) / (preCar.speed - car.speed);
                int distance = (int) Math.ceil(time * car.speed + car.position);
                if (distance <= target) {
                    stack.pop();
                    count--;
                } else {
                    break;
                }
            }
            stack.push(car);
        }
        return count;
    }

    public static void main(String[] args) {
//        int target = 12;
//        int[] position = {10, 8, 0, 5, 3};
//        int[] speed = {2, 4, 1, 1, 3};
        int target = 10;
        int[] position = {6, 8};
        int[] speed = {3, 2};
        Solution853 solution = new Solution853();
        System.out.println(solution.carFleet(target, position, speed));
    }
}
