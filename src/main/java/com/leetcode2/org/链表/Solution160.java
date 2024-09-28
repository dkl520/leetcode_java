package com.leetcode2.org.链表;

public class Solution160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        // If the two lists intersect, the pointers will meet at the intersection point.
        // If the two lists do not intersect, the pointers will both reach the end (null) at the same time.
        while (pointerA != pointerB) {
            // If pointerA reaches the end of its list, redirect it to headB.
            // Otherwise, move it to the next node.
            pointerA = (pointerA == null) ? headB : pointerA.next;

            // If pointerB reaches the end of its list, redirect it to headA.
            // Otherwise, move it to the next node.
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }

        // Either both pointers meet at the intersection node or both become null.
        return pointerA;
    }

    public static void main(String[] args) {

    }
}
