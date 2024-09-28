package com.leetcode2.org.链表;

public class Solution142 {
    public ListNode detectCycle(ListNode head) {

        if (head == null || head.next == null) {
            return null; // 如果链表为空或只有一个节点，直接返回null
        }
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        // 如果没有环，返回null
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;

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
