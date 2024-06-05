package com.sky.hiwise.algorithms.leetcode.queue;

import sun.awt.util.IdentityLinkedList;

import java.util.ArrayDeque;
import java.util.Deque;

public class FaultyKeyboard2810 {

    public static void main(String[] args) {
        System.out.println(finalString("interesting"));
    }

    public static String finalString(String s) {
        boolean head = false;
        Deque<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != 'i') {
                if (head) {
                    queue.offerFirst(ch);
                } else {
                    queue.offerLast(ch);
                }
            } else {
                head = !head;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (head) {
            while (!queue.isEmpty()) {
                sb.append(queue.pollLast());
            }
        } else {
            while (!queue.isEmpty()) {
                sb.append(queue.pollFirst());
            }
        }
        return sb.toString();
    }
}
