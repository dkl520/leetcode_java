
package com.leetcode2.org.链表;


 public class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head; // 如果链表为空或 k 小于等于 1，直接返回链表
        }
        ListNode dummy = new ListNode(0); // 创建一个虚拟节点，指向链表头节点
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null) {
            // 统计当前部分的节点数
            ListNode tail = curr;
            int count = 0;
            while (count < k && curr != null) {
                curr = curr.next;
                count++;
            }
            if (count == k) {
                // 翻转这 k 个节点
                prev.next = reverse(tail, k); // 连接翻转后的部分
                tail.next = curr; // 连接未翻转的部分
                prev = tail; // 更新 prev 节点
            } else {
                prev.next = tail; // 节点数不足 k 个，保持原有顺序
            }
        }
        return dummy.next; // 返回新的链表头节点
    }

    // 翻转从 start 开始的 k 个节点，返回翻转后的新头节点
    private ListNode reverse(ListNode start, int k) {
        ListNode prev = null;
        ListNode curr = start;
        while (k > 0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            k--;
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
        Solution25 solution25 = new Solution25();
        ListNode reverseNode = solution25.reverseKGroup(head, 2);
        System.out.println(reverseNode);
    }
}