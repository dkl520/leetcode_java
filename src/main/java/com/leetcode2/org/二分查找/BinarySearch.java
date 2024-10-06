package com.leetcode2.org.二分查找;

public class BinarySearch {
    int search(int[] list, int target) {
        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (list[mid] == target) {
                return mid;
            }
            if (list[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 8;
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.search(list, target));
    }
}
