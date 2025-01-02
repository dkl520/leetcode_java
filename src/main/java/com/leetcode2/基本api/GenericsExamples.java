package com.leetcode2.基本api;
//泛型
public class GenericsExamples<T> {
    private T value;

    public GenericsExamples(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public static void main(String[] args) {
        GenericsExamples<String> example = new GenericsExamples<>("Hello");
        System.out.println(example.getValue()); // 输出: Hello
    }
}
