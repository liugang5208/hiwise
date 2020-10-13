package com.sky.hiwise.design.lesson.collection;


import com.sky.hiwise.design.lesson.basic.Student;
import com.sky.hiwise.design.lesson.basic.TestEnum;

import java.lang.Object;
import java.util.*;

public class SetTest {

    public static void main(String[] args) {
        testEnumSet();
    }

    /**
     * Collection接口是List、Set和Queue接口的父接口，
     * 该接口里定义的方法既可用于操作Set集合，也可用于操作List和Queue集合。
     * boolean add(E e)
     该方法用于集合内添加一个元素，如果添加成功集合对象被添加操作改变了，返回true;
     boolean remove(Object o)
     删除集中中指定元素o,当集合中包含一个或多个o元素时，该方法只会删除第一个符合条件的元素，删除成功返回true;
     boolean contains(Object o)
     判断集合是否包含元素o，如果包含返回true;
     boolean containsAll(Collection<?> c)
     判断集合是否包含集合c中的所有元素，如果包含返回true;
     boolean addAll(Collection<? extends E>c)
     该方法把集合c里的多有元素添加到指定集合里，如果添加成功，返回true;
     boolean removeAll(Collection<?> c)
     从集合中删除集合c里面的所有元素（相当于用调用该方法的集合减集合c）,如果成功返回true;
     boolean retainAll(Collection<?> c)
     该方法用于从集合中移除未包含在指定c中的所有元素;
     void clear()
     清除集合中的所有元素，将集合长度变为0;
     boolean equals(Object o)
     该方法是判断由调用该方法的对象与对象o是否相等;
     int hashCode()
     返回对象的hashCode()值（根据对象的地址或者字符串或者数字算出来的int类型的数值）;
     boolean isEmpty()
     判断集合是否为空（判断容器是否为空）;
     int size()
     返回集合的长度;
     Iterator<E>iterator()
     集合调用该方法返回一个迭代器，该方法用于遍历集合;
     Object[] toArray()
     将集合转化成数组;
     <T> T[] toArray(T[] a)
     将集合转化成指定泛型的数组;
     */
    public static void testCollection() {
// List 集合允许重复且有序
        Collection c = new ArrayList();
        // c.add("Java");
        c.add("Java");
        c.add("C");
        c.add("C#");
        c.add("C++");
        c.add(5);// 虽然集合内不能存放基本数据类型的值，但是Java支持自动装箱
        System.out.println(c);// 输出 [Java, Java, C, C++,5]
        System.out.println(c.contains(5));// true
        c.remove(5);
        System.out.println(c);// 输出 [Java, Java, C, C++]

        // Set 集合是不允许有重复的且是无序的
        Collection mSet = new HashSet();
        mSet.add("Java");
        mSet.add("Java");
        mSet.add("C");
        mSet.add("C++");
        System.out.println(mSet);// 输出 [Java, C++, C]
        // c 集合是否完全包含mSet集合中的值
        System.out.println(c.containsAll(mSet));// true
        //c.removeAll(mSet);// 用c集合减去mSet集合里的所有元素
        System.out.println(c);// 输出 [C#]
        //c.clear();// 清空c集合中的所有元素
        System.out.println(c);// 输出 []
        mSet.retainAll(c);// 控制mSet集合里只剩下c集合里也包含的元素
        System.out.println(mSet);// 输出 []

        // System.out.println(集合); 能输出 [Java, C++, C....]是因为Collection重写了toString()方法。

        String [] d = (String[]) c.toArray(new String[c.size()]);
        for (String string : d) {
            //System.out.println(string);// 输出
        }

        Object[] o = c.toArray();
        for (Object object : o) {
            if(object instanceof String){
                String s = (String) object;
                //System.out.println(s);// 输出
            }
        }
        testIterator(c);
    }

    /**
     * Iterator
     Iterator 是Java集合框架的成员，但它与Collection系列、Map系列的集合不一样：Collection 系列集合、Map系列集合主要用于盛装其他对象，而Iterator则是主要用于遍历（即迭代访问）Collection 集合中的元素，Iterator对象也被称为迭代器。
     Iterator接口隐藏了各种Collection 实现类的底层细节，向应用程序提供遍历Collection集合元素的统一编程接口。
     Iterator提供的方法：
     boolean hashNext()
     如果被遍历的集合元素还没有被遍历完，返回true
     Object next()
     返回集合中的下一个元素
     void remove()
     删除集合里上一次next(）返回的元素
     void forEachRemaining(Consumer action)
     这是Java 8 新增的默认方法，该方法可使用Lambda表达式来遍历集合元素。
     */
    public static void testIterator(Collection c) {

        Iterator it = c.iterator();
        while(it.hasNext()){
            //String s = (String) it.next();
            System.out.println(it.next());
        }
        /**
         * Iterator 必须依附于Collection对象，若有一个Iterator对象，则必然有一个与之关联的Collection对象。
         Iterator提供了两个方法来迭代访问Collection集合里的元素，并可通过remove()方法删除集合中上一次next()方法返回的集合元素。
         当Iterator迭代访问Collection集合元素时，Collection集合里的元素不能改变，
         只有通过Iterator的remove（）删除上一次next()方法返回的集合元素才可以：否则将引发：java.util.ConcurrentModificationException异常。
         */
//        Iterator it2 = c.iterator();
//        while(it2.hasNext()){
//            String s = (String) it2.next();
//            if(s.equalsIgnoreCase("C#")){
//                c.remove(s);
//            }
//        }
    }

    public static void iterator(Collection c) {

        Iterator it = c.iterator();
        while (it.hasNext()) {
            Student s = (Student) it.next();
            System.out.println(s.getAge());
        }
    }

    public static void testHashSet() {
        Set<String> mSet = new HashSet<>();
        mSet.add("123");
        mSet.add("123");
        mSet.add("123w");
        mSet.add("12d3");
        mSet.add("1s2d3");
        mSet.add("12dd3");
        mSet.add("12fsd3");

        testIterator(mSet);

        Set<String> m1Set = new TreeSet<>();
        mSet.add("123");
        m1Set.add("123");
        m1Set.add("123w");
        m1Set.add("12d3");
        m1Set.add("1s2d3");
        m1Set.add("12dd3");
        m1Set.add("12fsd3");

        testIterator(m1Set);

//        Set t1Set = new TreeSet<>();
//        t1Set.add("123");
//        t1Set.add(456);
//        iterator(t1Set);
    }

    public static void testHashSet1() {
        Set<Student> mSet = new HashSet<>();
        Student s1 = new Student(1, "test1");
        Student s2 = new Student(2, "test2");
        mSet.add(s1);
        mSet.add(s2);

        System.out.println("s1 hashCode:" + s1.hashCode());
        System.out.println("s2 hashCode:" + s2.hashCode());
        testIterator(mSet);

        Set<Student> tSet = new TreeSet<>();
        tSet.add(s1);
        tSet.add(s2);
        iterator(tSet);

//        TreeSet ts =  new TreeSet(new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                Student m1 = (Student)o1;
//                Student m2 = (Student)o2;
//                return m1.getAge() >  m2.getAge() ? -1 : m1.getAge() < m2.getAge() ? 1 : 0;
//            }
//        });
//        ts.add(new Student(5, "test1"));
//        ts.add(new Student(3, "test2"));
//        ts.add(new Student(9, "test3"));
//        System.out.println(ts);
    }

    public static void testLinkedHashSet() {
        LinkedHashSet lhs = new LinkedHashSet();
        lhs.add("abc");
        lhs.add("efg");
        lhs.add("hij");
        System.out.println(lhs);
        lhs.remove(new String("efg"));
        lhs.add("efg");
        System.out.println(lhs);
    }

    public static void testEnumSet() {
        EnumSet es1 = EnumSet.allOf(TestEnum.class);
        System.out.println(es1);
        EnumSet es2 = EnumSet.noneOf(TestEnum.class);
        es2.add(TestEnum.SUCCESS);
        es2.add(TestEnum.FAIL);
        System.out.println(es2);

        EnumSet es3 = EnumSet.of(TestEnum.SUCCESS, TestEnum.FAIL);
        System.out.println(es3);
        EnumSet es4 = EnumSet.range(TestEnum.WAIT_CALL, TestEnum.FAIL);
        System.out.println(es4);
        //从其补充初始化枚举设置此枚举set
        EnumSet es5 = EnumSet.complementOf(es4);
        System.out.println(es5);
    }
}
