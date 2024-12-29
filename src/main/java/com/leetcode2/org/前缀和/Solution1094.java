package com.leetcode2.org.前缀和;

import java.util.Arrays;

public class Solution1094 {

    public boolean carPooling(int[][] trips, int capacity) {
        int n = trips.length;
        int max = Arrays.stream(trips).mapToInt(trip -> trip[2]).max().orElse(-1);
        int []road= new int[max+1];
        int []roadR = new int [max+1];
        for (int[] trip : trips) {
            int personNum = trip[0];
            int start = trip[1];
            int end = trip[2];
            road[start] += personNum;
            road[end] -= personNum;
        }
        int startNum=0;
        for(int i=0;i<road.length;i++){
            startNum+=road[i];
            roadR[i]= startNum;
            if(roadR[i]>capacity){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[][] trips = {{2, 1, 5}, {3, 3, 7}};
        int capacity = 4;
        System.out.println(new Solution1094().carPooling(trips, capacity));
    }
}
