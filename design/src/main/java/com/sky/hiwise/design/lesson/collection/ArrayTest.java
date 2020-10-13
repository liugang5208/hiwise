package com.sky.hiwise.design.lesson.collection;

import java.util.Arrays;

public class ArrayTest {

    public static void main(String[] args) {

        test();
//        test1();

        //test2();
    }

    public static void test() {
        //声明数组变量
        //dataType[] arrayRefVar;   // 首选的方法
        //或 dataType arrayRefVar[];  // 效果相同，但不是首选方法
        //String[] strList;
        String[] strList = new String[2];
        strList[0] = "123";
        strList[1] = "456";
        String[] strList1 = {"12", "abc", "qw23", "34sef"};

        System.out.println("strList1 数组长度：" + strList1.length);
        // 打印所有数组元素
        for (int i = 0; i < strList1.length; i++) {
            System.out.println(strList1[i] + " ");
        }

        strList1 = strList;
        System.out.println("strList1 数组长度：" + strList1.length);
        // 打印所有数组元素
        for (String element: strList1) {
            element = "1";
            System.out.println(element);
        }
    }

    public static void test1() {
        //多维数组
        //type arrayName = new type[arraylenght1][arraylenght2];
        //type 可以为基本数据类型和复合数据类型，arraylenght1 和 arraylenght2 必须为正整数，arraylenght1 为行数，arraylenght2 为列数。
        String s[][] = new String[2][];
        s[0] = new String[2];
        s[1] = new String[3];
        s[0][0] = new String("Good");
        s[0][1] = new String("Luck");
        s[1][0] = new String("to");
        s[1][1] = new String("you");
        s[1][2] = new String("!");

        for (String [] ele : s) {
            for (String ele1 : ele) {
                System.out.println(ele1);
            }
        }

        // 确定每行的元素个数，第一行有2个元素，第二行有3个元素，
        // 数组结构 = {{"E1", "E2"}, {"E1", "E2", "E3"}}
        String s1[][] = {{"E1", "E2"}, {"E1", "E2", "E3"}};
        for (String [] ele : s1) {
            for (String ele1 : ele) {
                System.out.println(ele1);
            }
        }
    }

    /**
     * Arrays 类
     java.util.Arrays 类能方便地操作数组，它提供的所有方法都是静态的。
     具有以下功能：
     给数组赋值：通过 fill 方法。
     对数组排序：通过 sort 方法,按升序。
     比较数组：通过 equals 方法比较数组中元素值是否相等。
     查找数组元素：通过 binarySearch 方法能对排序好的数组进行二分查找法操作。
     */
    public static void test2() {
        String str = "helloworld";
        char[] data = str.toCharArray();// 将字符串转为数组

        int[] array = new int[5];
        //填充数组
        Arrays.fill(array, 5);
        System.out.println("填充数组：Arrays.fill(array, 5)：");
        output(array);

        // 将数组的第2和第3个元素赋值为8
        Arrays.fill(array, 2, 4, 8);
        System.out.println("将数组的第2和第3个元素赋值为8：Arrays.fill(array, 2, 4, 8)：");
        output(array);

        int[] array1 = { 7, 8, 3, 2, 12, 6, 3, 5, 4 };
        // 对数组的第2个到第6个进行排序进行排序
        Arrays.sort(array1, 2, 7);
        System.out.println("对数组的第2个到第6个元素进行排序进行排序：Arrays.sort(array,2,7)：");
        output(array1);

        // 对整个数组进行排序
        Arrays.sort(array1);
        System.out.println("对整个数组进行排序：Arrays.sort(array1)：");
        output(array1);

        // 比较数组元素是否相等
        System.out.println("比较数组元素是否相等:Arrays.equals(array, array1):"
                + "\n" + Arrays.equals(array, array1));

        int[] array2 = array1.clone();
        System.out.println("克隆后数组元素是否相等:Arrays.equals(array1, array2):"
                + "\n" + Arrays.equals(array1, array2));

        // 使用二分搜索算法查找指定元素所在的下标（必须是排序好的，否则结果不正确）
        Arrays.sort(array1);
        System.out.println("元素3在array1中的位置：Arrays.binarySearch(array1, 3)："
                + "\n" + Arrays.binarySearch(array1, 3));

        // 如果不存在就返回负数
        System.out.println("元素9在array1中的位置：Arrays.binarySearch(array1, 9)："
                + "\n" + Arrays.binarySearch(array1, 9));
    }

    public static int[] output(int[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
        return array;
    }
}
