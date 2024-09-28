package com.leetcode2.org.双指针;

import java.util.Arrays;

public class Solution881 {
    public int numRescueBoats(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people);
        int left = 0;
        int right = n - 1;
        int count = 0;
        while (left <= right) {
            if (people[right] == limit) {
                count++;
                right--;
                continue;
            }
            if (people[left] + people[right] <= limit) {
                count++;
                right--;
                left++;
            } else {
                right--;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] people = {3, 2, 2, 1};
//        int limit = 3;
        int[] people = {3, 5, 3, 4};
        int limit = 5;
        System.out.println(new Solution881().numRescueBoats(people, limit));
    }

}
