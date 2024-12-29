package com.leetcode2.org.大厂.citadel;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvenSubarray {

    int getEvenSubarray(int[] list, int k) {

        int countOdd = 0;
        int result = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        int index = 0;

        while (index < list.length) {
            if (list[index] % 2 == 1) {
                countOdd++;
            }
            deque.addLast(list[index]);
            while (!deque.isEmpty() && countOdd > k) {
                int prev = deque.pollFirst();
                if (prev % 2 == 1) {
                    countOdd--;
                }
            }
            if (deque.getLast() == list[index]) {
                result += deque.size();
            }
            index++;
        }
        return result;

    }


    public static void main(String[] args) {
        int[] list = new int[]{1, 2, 3, 4};

        int k = 1;
        EvenSubarray evenSubarray = new EvenSubarray();
        System.out.println(evenSubarray.getEvenSubarray(list, k));


    }


}
