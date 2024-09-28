package com.leetcode2.org.mathTools;
public class doubleArray {
    public static void main(String[] args) {
        int[][] data = new int[][]{{12, 121, 32, 0, 0, 0}, {}, {32, 434, 675, 32, 76, 78,}, {12, 435, 76, 87, 87, 11}, {32, 67, 23, 675, 71, 71, 93}};
        System.out.println(data.length);
        System.out.println(data[0].length);
        int[][] arr1 = new int[3][2];
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            int[] slice = data[i];
            for (int j = 0; j < slice.length; j++) {
                int num = slice[j];
                sum += num;
            }
        }
        System.out.println(sum);
    }
}
