package com.leetcode2.org.链表;

public class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 创建一个虚拟节点(dummy node)，指向head。这样可以处理删除头节点的情况。
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 定义两个指针：fast和slow，均指向虚拟节点
        ListNode fast = dummy;
        ListNode slow = dummy;
        // 让fast指针先走n步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // 同时移动fast和slow，直到fast指针到达链表末尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;

        }
        // 此时slow指针指向的节点的下一个节点即为要删除的节点
        // 删除该节点
        slow.next = slow.next.next;
        // 返回链表的头节点
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
        Solution19 solution19 = new Solution19();
        ListNode newListNode = solution19.removeNthFromEnd(head, 2);
        System.out.println(newListNode);
    }
}
