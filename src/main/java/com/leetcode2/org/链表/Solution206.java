package com.leetcode2.org.链表;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
    class Solution206 {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode prev = null;
            ListNode next = null;
            ListNode current = head;

            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            return prev;
        }

    public static void main(String[] args) {
        int[] arrs = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(0);
        ListNode proxyHead = head;
        for (int num : arrs) {
            proxyHead.next = new ListNode(num);
            proxyHead = proxyHead.next;
        }
        head = head.next;
        Solution206 solution206 = new Solution206();

        ListNode endNode = solution206.reverseList(head);


    }
}








