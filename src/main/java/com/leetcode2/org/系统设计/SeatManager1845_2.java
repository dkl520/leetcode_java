package com.leetcode2.org.系统设计;

import java.util.TreeSet;

public class SeatManager1845_2 {
    TreeSet<Integer> unSeatedList;

    public SeatManager1845_2(int n) {
        this.unSeatedList = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            unSeatedList.add(i);
        }
    }

    public int reserve() {
        Integer seatPosition = this.unSeatedList.pollFirst();
        if (seatPosition == null) {
            return -1;
        }
        return seatPosition;
    }

    public void unreserve(int seatNumber) {
        this.unSeatedList.add(seatNumber);
    }
}
