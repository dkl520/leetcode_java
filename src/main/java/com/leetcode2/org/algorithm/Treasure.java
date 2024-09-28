package com.leetcode2.org.algorithm;

public class Treasure {
    private   int weight;
    private   int value;

    public Treasure(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Treasure{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}
