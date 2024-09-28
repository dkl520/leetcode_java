package com.leetcode2.org.collection;

import java.util.AbstractList;

public class CustomLinkedList<E> extends AbstractList<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        Node<E> current = getNode(index);
        return current.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = getNode(size - 1);
            current.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);
        if (index == 0) {
            E removedData = head.data;
            head = head.next;
            size--;
            return removedData;
        } else {
            Node<E> previous = getNode(index - 1);
            Node<E> current = previous.next;
            E removedData = current.data;
            previous.next = current.next;
            size--;
            return removedData;
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}