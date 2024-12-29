package com.leetcode2.org.系统设计;

import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {
    List<Integer> bigList;
    List<Integer> mediumList;
    List<Integer> smallList;
    int big;
    int medium;
    int small;

    public ParkingSystem(int big, int medium, int small) {
        this.bigList = new ArrayList<>(big);
        this.mediumList = new ArrayList<>(medium);
        this.smallList = new ArrayList<>(small);
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    public boolean addCar(int carType) {
        return switch (carType) {
            case 1 -> {
                if (this.bigList.size() == this.big) {
                    yield false;
                }
                this.bigList.add(carType);
                yield true;
            }
            case 2 -> {
                if (this.mediumList.size() == this.medium) {
                    yield false;
                }
                this.mediumList.add(carType);
                yield true;
            }
            case 3 -> {
                if (this.smallList.size() == this.small) {
                    yield false;
                }
                this.smallList.add(carType);
                yield true;
            }
            default -> false;
        };
    }

    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem(2, 3, 4);
        parkingSystem.addCar(1);
        parkingSystem.addCar(1);
        parkingSystem.addCar(1);
        System.out.println(parkingSystem.addCar(1));

    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */