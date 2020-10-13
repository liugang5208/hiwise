package com.sky.hiwise.design.lesson.collection;

import java.util.*;

public class CollectionsTest {

    public static void main(String[] args) {

        test();
    }

    /**
     * 我们之前学习了Collection，那么它和Collections有什么区别呢。
     Collections类概述
     针对集合进行操作的工具类，都是静态方法。
     Collection和Collections的区别
     Collection:是单列集合的顶层接口，有子接口List和Set。
     Collections:是针对集合操作的工具类，有对集合进行排序和二分查找的方法
     Collections成员方法
     - public static < T> void sort(List list)：排序 默认情况下是自然顺序。
     - public static < T> int binarySearch(List< ?> list,T key):二分查找
     - public static < T> T max(Collection< ?> coll):最大值
     - public static void reverse(List< ?> list):反转
     - public static void shuffle(List< ?> list):随机置换
     void swap(List list, int i , int j),交换两个索引位置的元素
     void rotate(List list, int distance),旋转。当distance为正数时，将list后distance个元素整体移到前面。当distance为负数时，将 list的前distance个元素整体移到后
     */

    public static void test() {
        ArrayList nums =  new ArrayList();
        nums.add(8);
        nums.add(-3);
        nums.add(2);
        nums.add(9);
        nums.add(-2);
        nums.add(-1);
        System.out.println(nums);
        Collections.reverse(nums);
        System.out.println(nums);
        Collections.sort(nums);
        System.out.println(nums);
        Collections.shuffle(nums);
        System.out.println(nums);

        Collections.sort(nums, (Object o1, Object o2) -> {
            String s1 = String.valueOf(o1);
            String s2 = String.valueOf(o2);
            return s1.compareTo(s2);
        });

//        Collections.sort(nums, new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                String s1 = String.valueOf(o1);
//                String s2 = String.valueOf(o2);
//                return s1.compareTo(s2);
//            }
//        });
        System.out.println(nums);

        /**
         * 查找，替换操作
         int binarySearch(List list, Object key), 对List进行二分查找，返回索引，注意List必须是有序的
         int max(Collection coll),根据元素的自然顺序，返回最大的元素。 类比int min(Collection coll)
         int max(Collection coll, Comparator c)，根据定制排序，返回最大元素，排序规则由Comparatator类控制。类比int min(Collection coll, Comparator c)
         void fill(List list, Object obj),用元素obj填充list中所有元素
         int frequency(Collection c, Object o)，统计元素出现次数
         int indexOfSubList(List list, List target), 统计targe在list中第一次出现的索引，找不到则返回-1，类比int lastIndexOfSubList(List source, list target).
         boolean replaceAll(List list, Object oldVal, Object newVal), 用新元素替换旧元素。
         */
        System.out.println(Collections.max(nums));
        System.out.println(Collections.min(nums));
        Collections.replaceAll(nums, -1, -7);
        System.out.println(Collections.frequency(nums, -3));
        Collections.sort(nums);
        System.out.println(nums);
        System.out.println(Collections.binarySearch(nums, -2));
    }


    /**
     * Collections中几乎对每个集合都定义了同步控制方法，例如 SynchronizedList(), SynchronizedSet()等方法，来将集合包装成线程安全的集合。下面是Collections将普通集合包装成线程安全集合的用法，
     */
    public static void test1() {
        Collection c = Collections.synchronizedCollection(new ArrayList());
        List list = Collections.synchronizedList(new ArrayList());
        Set s = Collections.synchronizedSet(new HashSet());
        Map m = Collections.synchronizedMap(new HashMap());
    }

    /**
     * 设置不可变（只读）集合
     Collections提供了三类方法返回一个不可变集合，
     emptyXXX(),返回一个空的只读集合（这不知用意何在？）
     singleXXX()，返回一个只包含指定对象，只有一个元素，只读的集合。
     unmodifiablleXXX()，返回指定集合对象的只读视图。
     */
    public static void test2() {
        List lt = Collections.emptyList();
        Set st = Collections.singleton("avs");
        Map mp = new HashMap();
        mp.put("a",100);
        mp.put("b", 200);
        mp.put("c",150);
        Map readOnlyMap = Collections.unmodifiableMap(mp);

        //下面会报错
        lt.add(100);
        st.add("sdf");
        mp.put("d", 300);
    }


}
