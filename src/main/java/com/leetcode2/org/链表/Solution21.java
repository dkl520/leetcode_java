package com.leetcode2.org.链表;

class Solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        current.next = list1 != null ? list1 : list2;

        return dummy.next;
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

        int[] arrs2 = {2, 2, 3, 7, 9};
        ListNode head2 = new ListNode(0);
        ListNode proxyHead2 = head2;
        for (int num : arrs2) {
            proxyHead2.next = new ListNode(num);
            proxyHead2 = proxyHead2.next;
        }
        head2 = head2.next;

        Solution21 solution21 = new Solution21();
        ListNode mergeTwoLists = solution21.mergeTwoLists(head, head2);

        System.out.println(mergeTwoLists);
    }
}
