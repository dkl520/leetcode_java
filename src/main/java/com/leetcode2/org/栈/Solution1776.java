package com.leetcode2.org.栈;

import java.util.*;

public class Solution1776 {
    static class Car {
        int position;
        int speed;
        int index;
        double time;

        public Car(int position, int speed, int index) {
            this.position = position;
            this.speed = speed;
            this.index = index;
            this.time = Double.MAX_VALUE;
        }

        public void setTime(double time) {
            this.time = time;
        }

        public double getTime() {
            return time;
        }
    }

    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        List<Car> carList = new ArrayList<Car>();
        for (int i = 0; i < n; i++) {
            int[] car = cars[i];
            Car carN = new Car(car[0], car[1], i);
            carList.add(carN);
        }
        double[] result = new double[n];
        Arrays.fill(result, -1);
//        carList.sort((a, b) -> a.position - b.position);
        List<Car> deque = new ArrayList<>();
//        for (Car car : carList) {
//            for (int i = 0; i < deque.size(); i++) {
//                Car preCar = deque.get(i);
//                if (car.speed < preCar.speed) {
//                    double time = (double) (car.position - preCar.position) / (preCar.speed - car.speed);
//                    preCar.setTime(Math.min(preCar.getTime(), time));
//                    result[preCar.index] = preCar.getTime();
//                }
//            }
//            deque.add(car);
//        }

        for (Car car : carList) {
            for (int i = deque.size() - 1; i >= 0; i--) {
                Car preCar = deque.get(i);
                if (car.speed < preCar.speed) {
                    double time = (double) (car.position - preCar.position) / (preCar.speed - car.speed);
                    preCar.setTime(Math.min(preCar.getTime(), time));
                    result[preCar.index] = preCar.getTime();
                } else {
                    break;
                }
            }
            deque.add(car);
        }
        return result;
    }

    public static void main(String[] args) {

        int[][] cars = {
                {3, 4},
                {5, 4},
                {6, 3},
                {9, 1}
        };
        Solution1776 solution = new Solution1776();
        System.out.println(Arrays.toString(solution.getCollisionTimes(cars)));
    }
}
