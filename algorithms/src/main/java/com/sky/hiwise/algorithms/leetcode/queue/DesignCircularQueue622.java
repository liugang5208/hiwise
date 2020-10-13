package com.sky.hiwise.algorithms.leetcode.queue;

public class DesignCircularQueue622 {

    /**
     * 设计你的循环队列实现。 循环队列是一种线性数据结构，
     * 其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
     * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。
     * 在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。
     * 但是使用循环队列，我们能使用这些空间去存储新的值。
     * 你的实现应该支持如下操作：
     * MyCircularQueue(k): 构造器，设置队列长度为 k 。
     * Front: 从队首获取元素。如果队列为空，返回 -1 。
     * Rear: 获取队尾元素。如果队列为空，返回 -1 。
     * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
     * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
     * isEmpty(): 检查循环队列是否为空。
     * isFull(): 检查循环队列是否已满。
     */
    /** Initialize your data structure here. Set the size of the queue to be k. */
    private int capacity;
    private int count;
    private int[] data;
    private int headIndex;
    public DesignCircularQueue622(int k) {
        data = new int[k];
        capacity = k;
        headIndex = count = 0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (count + 1 > capacity) {
            return false;
        }
        data[(headIndex + count) % capacity] = value;
        count ++;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (count == 0) {
            return false;
        }
        headIndex = (headIndex + 1) % capacity;
        count --;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (count == 0) {
            return -1;
        }
        return data[headIndex];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (count == 0) {
            return -1;
        }
        return data[(headIndex + count - 1 ) % capacity];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return count == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return capacity == count;
    }
}
