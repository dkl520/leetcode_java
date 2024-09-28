package com.leetcode2.org.链表;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public ListNode sortList(ListNode head) {
        ListNode rollNode = head;
        ArrayList<Integer> list = new ArrayList<>();
        do {
            list.add(rollNode.val);
            rollNode = rollNode.next;
        } while (rollNode != null);

        Collections.sort(list);
        rollNode = head;
        for (Integer v : list) {
            rollNode.val = v;
            rollNode = rollNode.next;
        }
        return  head;
    }

    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(3, 1, 2, 4));
        ListNode topS = new ListNode(-1);

        ListNode root = topS;
        for (Integer v : l1) {
            root.next = new ListNode();
            root.next.val = v;
            root = root.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        ListNode head = topS.next;
        ListNode rollNode = head;
        do {
            list.add(rollNode.val);
            rollNode = rollNode.next;
        } while (rollNode != null);

        Collections.sort(list);
        rollNode = head;
        for (Integer v : list) {
            rollNode.val = v;
            rollNode = rollNode.next;
        }


    }
}
