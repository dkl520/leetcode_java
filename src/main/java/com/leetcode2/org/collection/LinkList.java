package com.leetcode2.org.collection;

import java.util.LinkedList;

public class LinkList {
    public static void main(String[] args) {
        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add(11);
        linkedList.add(1);
        linkedList.add(21);
        linkedList.add(3);
        System.out.println(linkedList);
        Object root = linkedList.getFirst();
        System.out.println(root);
        System.out.println(root);
        linkedList.addFirst(0);
        linkedList.addLast(Integer.MAX_VALUE);
        System.out.println(linkedList);
        Integer first= (Integer) linkedList.removeFirst();
        Integer last =(Integer) linkedList.removeLast();

        System.out.println(first+"ffff");
        System.out.println(last+"lastttt");

    }
}
