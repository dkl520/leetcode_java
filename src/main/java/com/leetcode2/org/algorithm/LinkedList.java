package com.leetcode2.org.algorithm;

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedList {
    ListNode head;

    public LinkedList() {
        this.head = null;
    }

    public void addNode(int val) {
        ListNode newNode = new ListNode(val);
        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void reverseList() {
        ListNode prev = null;
        ListNode next = null;
        ListNode current = head;
        while (current != null) {
            prev = current.next;
            current.next = next;
            next = current;
            current = prev;
        }
        head = next;
    }

    public void printList() {
        ListNode current = head;
        System.out.println("LinkedList:");
        while (current != null) {
            System.out.println(current.val + "");
            current = current.next;
        }
        System.out.println();
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 如果list1或list2为空，直接返回非空链表
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        // 创建一个哑节点，它的值为0，用于方便链表操作
        ListNode dummy = new ListNode(0);
        // 定义一个指针，指向哑节点，方便遍历链表
        ListNode current = dummy;
        // 当list1和list2都不为空时，进行合并操作
        while (list1 != null && list2 != null) {
            // 如果list1的值小于等于list2的值，将list1加入到链表末尾
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                // 否则，将list2加入到链表末尾，并更新list2的指针位置
                current.next = list2;
                list2 = list2.next;
            }
            // 更新current的指针位置，指向新加入的节点后面
            current = current.next;
        }
        // 如果list1还有剩余节点，将其加入到链表末尾
        // 如果list2还有剩余节点，将其加入到链表末尾
        current.next = list1 != null ? list1 : list2;
        // 返回合并后的链表的头节点（注意是哑节点的下一个节点）
        return dummy.next;
    }
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        // 添加节点
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        // 打印链表
        LinkedList linkedList2 = new LinkedList();
        linkedList2.addNode(3);
        linkedList2.addNode(2);
        linkedList2.addNode(1);
        linkedList.mergeTwoLists(linkedList.head, linkedList2.head);
        linkedList.printList();
        linkedList.reverseList();
        linkedList.printList();
    }
}


