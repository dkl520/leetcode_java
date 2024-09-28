package com.leetcode2.org.链表;

class Solution2_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 创建一个新的链表来存储结果，并初始化一个哨兵节点(dummy)指向它
        ListNode listNodeSum = new ListNode(0);
        // 初始化进位数(addNum)为0
        int addNum = 0;
        ListNode dummy = listNodeSum;

        // 当l1或l2不为空时，循环继续
        while (l1 != null || l2 != null) {
            // 获取当前节点的值，如果为空则值为0
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            // 计算当前位的和，包括进位数
            int sum = val1 + val2 + addNum;
            // 计算当前位的实际值(个位数)和新的进位数(十位数)
            int mod = sum % 10;
            addNum = sum / 10;
            // 创建一个新的节点存储当前位的值
            ListNode numNode = new ListNode(mod);
            // 将新的节点链接到结果链表的末尾
            dummy.next = numNode;
            dummy = dummy.next;

            // 移动到下一个节点
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // 如果最后有进位数，创建一个新的节点存储它
        if (addNum != 0) {
            ListNode numNode = new ListNode(addNum);
            dummy.next = numNode;
        }

        // 返回结果链表的下一个节点(跳过哨兵节点)
        return listNodeSum.next;
    }
}
