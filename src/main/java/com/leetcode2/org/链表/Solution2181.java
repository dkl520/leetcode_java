package com.leetcode2.org.链表;

import java.util.List;

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
public class Solution2181 {
    public ListNode mergeNodes(ListNode head) {

        if (head == null || head.next == null) return head;
        int sum = 0;
        ListNode cur = new ListNode(0);
        ListNode pre = cur;
        while (head != null) {
            if (head.val != 0) {
                sum += head.val;
            } else {
                cur.next = new ListNode(sum);
                cur = cur.next;
                sum = 0;
            }
            head = head.next;
        }
        return pre.next.next;
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(0, 3, 1, 0, 4, 5, 2, 0);
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (Integer i : list) {
            ListNode node = new ListNode(i);
            head.next = node;
            head = head.next;
        }

        System.out.println(new Solution2181().mergeNodes(cur.next));


    }


}
