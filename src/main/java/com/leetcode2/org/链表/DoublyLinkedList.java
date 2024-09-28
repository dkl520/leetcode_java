package com.leetcode2.org.链表;

import com.leetcode2.org.algorithm.Node;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    private int size;
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;
        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        }else{
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;

        }
        size++;
    }
    public T removeFirst() {
       if (isEmpty()){
         throw new NoSuchElementException("List is empty");
       }else{
           T data = head.data;
           head = head.next;
           if (head == null){
               tail = null;
           }else{
               head.prev = null;
           }
           size--;
           return data;
       }
    }

    public T removeLast() {
        if (isEmpty()){
            throw new NoSuchElementException("List is empty");
        }
        T data = tail.data;
        tail = tail.prev;
        if (tail == null){
            head = null;
        }else{
            tail.next = null;
        }
        size--;
        return data;
    }

    public boolean isEmpty(){
        return  size==0;
    }
    public int size(){
        return size;
    }
}
