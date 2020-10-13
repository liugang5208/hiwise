package com.sky.hiwise.design.lesson.collection;

import java.util.*;

public class ListTest {

    public static void main(String[] args) {

        testStack();
    }

    /**
     * 增加
     void add(int index, E element) 指定位置添加元素
     boolean addAll(int index, Collection c) 指定位置添加集合
     2：删除
     E remove(int index) 删除指定位置元素
     3：修改
     E set(int index, E element)    返回的是需要替换的集合中的元素
     4：查找：
     E get(int index)             注意： IndexOutOfBoundsException
     int indexOf(Object o)         // 找不到返回-1
     lastIndexOf(Object o)
     5：求子集合
     List<E> subList(int fromIndex, int toIndex) // 不包含toIndex
     */
    public static void test() {
        //创建ArrayList对象
        ArrayList<String> al=new ArrayList<String>();
        al.add("初");
        al.add("识");
        al.add("Java");
        al.add("集合");
        al.add("框架");
        //遍历
        //iterator(al);

        //由于 subList 持有 List 同一个引用，所以对 subList 进行的操作也会影响到原有 Lis
        //List sub = al.subList(3, 5);
        //sub.clear();
        //iterator(al);

        ListIterator it = al.listIterator();
        while (it.hasNext()) {
            if (it.next().equals("Java")) {
                it.add("test");
            }
        }
        iterator(al);
    }

    public static void iterator(Collection c) {
        Iterator it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void testLinkedList() {
        LinkedList list = new LinkedList<>();
        list.add("test");
        list.add("test1");
        list.addFirst("12345");
        iterator(list);
    }

    /**
     * //入栈，添加一个元素到数组的最后一个
     public E push(E var1)
     //出栈，删除数组最后一个元素并返回
     public synchronized E pop()
     //获取最后一个元素，不删除
     public synchronized E peek()
     */
    public static void testStack() {
        Stack stack = new Stack();
        stack.push("1234");
        stack.push("3456");
        stack.push("5674");
        System.out.println(stack.pop());
    }

}
