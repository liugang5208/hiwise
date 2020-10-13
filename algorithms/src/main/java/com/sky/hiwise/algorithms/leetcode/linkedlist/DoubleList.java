package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class DoubleList {

    private int size;
    private Node head;
    private Node tail;

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addFirst(Node x) {
        x.next = head.next;
        x.prev = head;
        head.next.prev = x;
        head.next = x;
        size++;
    }

    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    public Node removeLast() {
        if (head == tail.prev) {
            return null;
        }
        Node last = tail.prev;
        remove(last);
        return last;
    }

    public int getSize() {
        return size;
    }

}
