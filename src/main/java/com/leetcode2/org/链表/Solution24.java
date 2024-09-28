package com.leetcode2.org.链表;

public class Solution24 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = head;
        ListNode curr = head.next;
        ListNode pre = dummy;
        while (tail.next != null) {
            ListNode next = curr.next;
            curr.next = tail;
            tail.next = next;
            pre.next = curr;
            if (tail.next != null) {
                pre = tail;
                tail = next;
                curr = tail.next;
            }
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        int[] arrs = {1, 2, 3, 4};
        ListNode head = new ListNode(0);
        ListNode proxyHead = head;
        for (int num : arrs) {
            proxyHead.next = new ListNode(num);
            proxyHead = proxyHead.next;
        }
        head = head.next;
        Solution24 solution24 = new Solution24();
        ListNode result = solution24.swapPairs(head);
        System.out.println(result);
    }
}
