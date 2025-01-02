package com.leetcode2;


import com.leetcode2.org.系统设计.MyCalendarTwo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyCalendarTwoTest {


    void bookSingleEvent() {
        MyCalendarTwo calendar = new MyCalendarTwo();
        assertTrue(calendar.book(10, 20));
    }


    void bookNonOverlappingEvents() {
        MyCalendarTwo calendar = new MyCalendarTwo();
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(20, 30));
    }


    void bookOverlappingEvents() {
        MyCalendarTwo calendar = new MyCalendarTwo();
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(15, 25));
    }


    void bookTripleOverlappingEvents() {
        MyCalendarTwo calendar = new MyCalendarTwo();
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(15, 25));
        assertFalse(calendar.book(17, 22));
    }

    @Test
    void bookEdgeCaseEvents() {
        MyCalendarTwo calendar = new MyCalendarTwo();
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(50, 60));
        assertTrue(calendar.book(10, 40));
        assertFalse(calendar.book(5, 15));
        assertTrue(calendar.book(5, 10));
        assertTrue(calendar.book(25, 55));
    }
}