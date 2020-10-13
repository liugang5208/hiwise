package com.sky.hiwise.design.lesson.collection;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class QueueTest {

    public static void main(String[] args) {

        //testPriorityQueue();
        //testArrayDeque();
        testLinkedList();
    }

    /**
     * Queue： 基本上，一个队列就是一个先入先出（FIFO）的数据结构
     Queue接口与List、Set同一级别，都是继承了Collection接口。LinkedList实现了Deque接 口。
     Queue的实现
     1、没有实现的阻塞接口的LinkedList： 实现了java.util.Queue接口和java.util.AbstractQueue接口
     内置的不阻塞队列： PriorityQueue 和 ConcurrentLinkedQueue
     PriorityQueue 和 ConcurrentLinkedQueue 类在 Collection Framework 中加入两个具体集合实现。
     PriorityQueue 类实质上维护了一个有序列表。加入到 Queue 中的元素根据它们的天然排序（通过其 java.util.Comparable 实现）或者根据传递给构造函数的 java.util.Comparator 实现来定位。
     ConcurrentLinkedQueue 是基于链接节点的、线程安全的队列。并发访问不需要同步。因为它在队列的尾部添加元素并从头部删除它们，所以只要不需要知道队列的大 小，ConcurrentLinkedQueue 对公共集合的共享访问就可以工作得很好。收集关于队列大小的信息会很慢，需要遍历队列。
     2)实现阻塞接口的：
     java.util.concurrent 中加入了 BlockingQueue 接口和五个阻塞队列类。它实质上就是一种带有一点扭曲的 FIFO 数据结构。不是立即从队列中添加或者删除元素，线程执行操作阻塞，直到有空间或者元素可用。
     五个队列所提供的各有不同：
     * ArrayBlockingQueue ：一个由数组支持的有界队列。
     * LinkedBlockingQueue ：一个由链接节点支持的可选有界队列。
     * PriorityBlockingQueue ：一个由优先级堆支持的无界优先级队列。
     * DelayQueue ：一个由优先级堆支持的、基于时间的调度队列。
     * SynchronousQueue ：一个利用 BlockingQueue 接口的简单聚集（rendezvous）机制。
     */
    public static void testPriorityQueue() {
        PriorityQueue pq = new PriorityQueue();
        pq.offer(6);
        pq.offer(-3);
        pq.offer(30);
        pq.offer(13);
        System.out.println(pq);
        System.out.println(pq.poll());
    }

    /**
     * ArrayDeque类是双端队列的实现类，类的继承结构如下面，继承自AbastractCollection（该类实习了部分集合通用的方法，其实现了Collection接口），其实现的接口Deque接口中定义了双端队列的主要的方法，比如从头删除，从尾部删除，获取头数据，获取尾部数据等等。
     ArrayDeque基本特征
     就其实现而言，ArrayDeque采用了循环数组的方式来完成双端队列的功能。
     1. 无限的扩展，自动扩展队列大小的。（当然在不会内存溢出的情况下。）
     2. 非线程安全的，不支持并发访问和修改。
     3. 支持fast-fail.
     4. 作为栈使用的话比比栈要快.
     5. 当队列使用比linklist要快。
     6. null元素被禁止使用。
     */
    public static void testArrayDeque() {
        //栈
        ArrayDeque stack = new ArrayDeque();
        stack.push("java 1");
        stack.push("java 2");
        stack.push("java 3");
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);

        //队列
        ArrayDeque queue = new ArrayDeque();
        queue.offer("java 4");
        queue.offer("java 5");
        queue.offer("java 6");
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
    }

    public static void testLinkedList() {
        LinkedList books = new LinkedList<String>();
        //字符串加入队列尾部
        books.offer("book 1");
        //字符串加入栈顶部
        books.push("book 2");
        //字符串加入队列头部相当于栈顶部
        books.offerFirst("book 3");
        Iterator it = books.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        //访问并不删除栈顶元素
        System.out.println(books.peekFirst());
        //访问并不删除队列最后一个元素
        System.out.println(books.peekLast());
        //栈顶元素弹出栈
        System.out.println(books.pop());
        System.out.println(books);
        //访问并删除队列最后一个元素
        System.out.println(books.pollLast());
        System.out.println(books);
    }


}
