package com.leetcode2.org.链表;

public class Solution148 {
    public ListNode sortList(ListNode head) {
        // 基本情况：如果链表为空或只有一个节点，则无需排序，直接返回
        if (head == null || head.next == null) {
            return head;
        }
        // 1. 找到链表的中点（快慢指针法）
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 将链表切分成两部分
        prev.next = null;
        // 2. 递归地排序两个子链表
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        // 3. 合并两个已排序的子链表
        return merge(l1, l2);
    }

    // 合并两个有序链表
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        if (l1 != null) {
            tail.next = l1;
        } else {
            tail.next = l2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        int[] arrs = {3,2,1,4,8};
        ListNode head = new ListNode(0);
        ListNode proxyHead = head;
        for (int num : arrs) {
            proxyHead.next = new ListNode(num);
            proxyHead = proxyHead.next;
        }
        head = head.next;
        Solution148 solution148 = new Solution148();
        ListNode result = solution148.sortList(head);
        System.out.println(result);

    }
}
