package com.leetcode2.org.链表;

class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNodeSum = new ListNode(0);
        int addNum = 0;
        ListNode dummy = listNodeSum;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + addNum;
            int mod = sum % 10;
            ListNode numNode = new ListNode(mod);
            addNum = sum / 10;
            dummy.next = numNode;
            dummy = dummy.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null && l2 == null) {
            if (addNum != 0) {
                ListNode numNode = new ListNode(addNum);
                dummy.next = numNode;
            }
        }
        if (l1 == null) {
            while (l2 != null) {
                int sum = addNum + l2.val;
                int mod = sum % 10;
                ListNode numNode = new ListNode(mod);
                addNum = sum / 10;
                dummy.next = numNode;
                dummy = dummy.next;
                l2 = l2.next;
            }
            if (addNum != 0) {
                ListNode numNode = new ListNode(addNum);
                dummy.next = numNode;
            }
        }
        if (l2 == null) {
            while (l1 != null) {
                int sum = addNum + l1.val;
                int mod = sum % 10;
                ListNode numNode = new ListNode(mod);
                addNum = sum / 10;
                dummy.next = numNode;
                dummy = dummy.next;
                l1 = l1.next;
            }
            if (addNum != 0) {
                ListNode numNode = new ListNode(addNum);
                dummy.next = numNode;
            }
        }
        return listNodeSum.next;
    }

    public static void main(String[] args) {
        int[] arrs = {3, 7};
        ListNode head = new ListNode(0);
        ListNode proxyHead = head;
        for (int num : arrs) {
            proxyHead.next = new ListNode(num);
            proxyHead = proxyHead.next;
        }
        head = head.next;

        int[] arrs2 = {9, 2};
        ListNode head2 = new ListNode(0);
        ListNode proxyHead2 = head2;
        for (int num : arrs2) {
            proxyHead2.next = new ListNode(num);
            proxyHead2 = proxyHead2.next;
        }
        head2 = head2.next;

        Solution2 solution2 = new Solution2();
        ListNode addTwoNumbers = solution2.addTwoNumbers(head, head2);
        System.out.println(addTwoNumbers);
    }
}