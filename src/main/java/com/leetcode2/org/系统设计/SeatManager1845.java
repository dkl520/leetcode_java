package com.leetcode2.org.系统设计;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SeatManager1845 {
    static class Seat {
        int index;
        int reserved;

        public Seat(int index, int reserved) {
            this.index = index;
            this.reserved = reserved;
        }
    }

    TreeSet<Seat> seats;
    List<Seat> seatsList;

    public SeatManager1845(int n) {
        this.seats = new TreeSet<>((a, b) -> {
            if (a.reserved == b.reserved) {
                return a.index - b.index;
            }
            return a.reserved - b.reserved;
        });
        this.seatsList = new ArrayList<>(n + 1);
        this.seatsList.add(new Seat(0, 2));
        for (int i = 1; i <= n; i++) {
            Seat seat = new Seat(i, 0);
            this.seats.add(seat);
            this.seatsList.add(i, seat);
        }

    }

    public int reserve() {
        Seat seat = this.seats.pollFirst();
        if (seat != null && seat.reserved == 0) {
            seat.reserved = 1;
            this.seats.add(seat);
            return seat.index;
        }
        return -1;
    }

    public void unreserve(int seatNumber) {
        Seat seat = this.seatsList.get(seatNumber);
        this.seats.remove(seat);
        seat.reserved = 0;
        this.seats.add(seat);
    }

    public static void main(String[] args) {
        SeatManager1845 seatManager1845 = new SeatManager1845(5);


    }
}
