package com.leetcode2.org.mathTools;
public class TrapRainWater {
    public static void main(String[] args) {
        int[] table = new int[]{2, 4, 5, 3, 1, 2, 4, 5, 6};
        RainWater rainWater = new RainWater(table);
        System.out.println("计算的接雨水的值为:"+rainWater.getSumArea());
    }

}

class RainWater {
    private int maxIndex = 0;
    private int sumArea = 0;
    private int lefMax = 0;
    private int rightMax = 0;
    private int max = 0;
    private int[] table;

    RainWater(int[] table) {
        this.table = table;
        this.lookMax(table);
        this.calcLeftArea(table);
        this.calcRightArea(table);
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }

    public int getSumArea() {
        return sumArea;
    }

    public void setSumArea(int sumArea) {
        this.sumArea = sumArea;
    }

    public int getLefMax() {
        return lefMax;
    }

    public void setLefMax(int lefMax) {
        this.lefMax = lefMax;
    }

    public int getRightMax() {
        return rightMax;
    }

    public void setRightMax(int rightMax) {
        this.rightMax = rightMax;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int[] getTable() {
        return table;
    }

    public void setTable(int[] table) {
        this.table = table;
    }

    void lookMax(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= max) {
                this.max = arr[i];
                this.maxIndex = i;
            }
        }
    }

    void calcLeftArea(int[] arr) {
        for (int i = 0; i < maxIndex; i++) {
            if (arr[i] > lefMax) {
                lefMax = arr[i];
            } else {
                this.sumArea += lefMax - arr[i];
            }
        }
    }

    void calcRightArea(int[] arr) {
        for (int i = arr.length - 1; i > maxIndex; i--) {
            if (arr[i] > rightMax) {
                rightMax = arr[i];
            } else {
                this.sumArea += rightMax - arr[i];
            }
        }
    }
}

