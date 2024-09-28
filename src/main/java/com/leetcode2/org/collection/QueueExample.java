package com.leetcode2.org.collection;

import java.util.*;

public class QueueExample {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList= new LinkedList<>();
        linkedList.push(11);
        linkedList.add(22);
        System.out.println(linkedList);
        Queue<String> queue= new LinkedList<>();
        queue.offer("Alice");
        queue.add("Bob");
        Queue<String> queue1= new LinkedList<>();
        queue1.offer("Alice");
        queue1.offer("Bbbb");
        queue1.offer("Bob");
        List<String> list1= new ArrayList<>(Arrays.asList("Alice","Bbbb"));
//        Queue <String>queue2= new LinkedList<>(queue1);
        Queue<String>queue2 = new LinkedList<>(list1);
//        queue2.element()
        System.out.println(queue2.element()+"elelment");


       Boolean containsAll= queue1.containsAll(queue);
        System.out.println(containsAll);
        System.out.println(queue2);

    }
}
