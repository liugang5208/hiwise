package com.sky.hiwise.design.lesson.generic;

import java.util.*;

public class GenericTest {

    public static void main(String[] args) {
        Integer integer1 = new Integer(100);
        Integer integer2 = new Integer(100);
        System.out.println(integer1 == integer2);
        Integer int1 = Integer.valueOf("100");
        Integer int2 = Integer.valueOf("100");
        System.out.println(int1 == int2);
        Integer int3 = Integer.valueOf("300");
        Integer int4 = Integer.valueOf("300");
        System.out.println(int3 == int4);
        //test();
        //testMethod();
        //test3();
        //test4();
//        List<String> arrayList = new ArrayList<>();
//        arrayList.add(null);
//        System.out.println(arrayList);
    }

    /**
     * 泛型的本质是为了参数化类型（在不创建新的类型的情况下，通过泛型指定的不同类型来控制形参具体限制的类型）。
     * 也就是说在泛型使用过程中，操作的数据类型被指定为一个参数，这种参数类型可以用在类、接口和方法中，分别被称为泛型类、泛型接口、泛型方法
     */
//    public static void test() {
//        List arrayList = new ArrayList();
//        //List<String> arrayList = new ArrayList<String>();
//        arrayList.add("aaaa");
//        arrayList.add(100);
//
//        for(int i = 0; i< arrayList.size();i++){
//            String item = (String)arrayList.get(i);
//            System.out.println(item);
//        }
//
//        //Java7 泛型"菱形"写法
//        Map<String, String> map=new HashMap<String, String>();
//        Map<String, String> map1=new HashMap<>();
//    }

    /**
     * 泛型只在编译阶段有效
     */
    public static void test1() {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("泛型测试--类型相同");
        }
    }

    /**
     * 泛型类型用于类的定义中，被称为泛型类。通过泛型可以完成对一组类的操作对外开放相同的接口。
     * 最典型的就是各种容器类，如：List、Set、Map。
     */
    public static void testClass() {

        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.  后面的Integer可以省略
        Generic<Integer> genericInteger = new Generic<Integer>(123456);
    }


    /**
     * 我们知道Ingeter是Number的一个子类，同时在特性章节中我们也验证过Generic<Ingeter>与Generic<Number>实际上是相同的一种基本类型。
     * 那么问题来了，在使用Generic<Number>作为形参的方法中，能否使用Generic<Ingeter>的实例传入呢？
     * 在逻辑上类似于Generic<Number>和Generic<Ingeter>是否可以看成具有父子关系的泛型类型呢？
     */
    public static void test2() {
        //类型通配符
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);
        //showKeyValue1(gInteger);
        showKeyValue1(gNumber);

        //设置类型通配符上限
        List<Test1Generic> list = new ArrayList<>();
        list.add(new Test1Generic("test"));
        //List<Generic> List<Test1Generic> 并不是父子类型关系
        //genericPrint(list);
        genericPrint1(list);
        genericPrint2(list);

        /**
         * 通配符有三种使用方式 -
         上限通配符 - ? extends扩展类型。
         下限通配符 - ? super超级类型。
         无限通配符 - ?
         通配符指南
         上限通配符 - 如果变量属于类别，请使用带有通配符的extends关键字。
         下界通配符 - 如果一个变量是外部类别，请使用带有通配符的super关键字。
         无界通配符 - 如果可以使用Object类方法访问变量，则使用未绑定的通配符。
         无通配符 - 如果代码访问进/出类别中的变量，那么不要使用通配符。
         */
    }

    /**
     * 通过提示信息我们可以看到Generic<Integer>不能被看作为`Generic<Number>的子类。
     * 由此可以看出:同一种泛型可以对应多个版本（因为参数类型是不确定的），不同版本的泛型类实例是不兼容的。
     * @param obj
     */
    public static void showKeyValue1(Generic<Number> obj){ //Generic<?>
        /**
         * 类型通配符一般是使用？代替具体的类型实参，注意了，此处’？’是类型实参，而不是类型形参 。
         * 再直白点的意思就是，此处的？和Number、String、Integer一样都是一种实际的类型，可以把？看成所有类型的父类。是一种真实的类型。
         可以解决当具体类型不确定的时候，这个通配符就是 ?  ；当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。
         */
        System.out.println("泛型测试:key value is " + obj.getKey());
    }


    public static void genericPrint(List<Generic> generics) {
        for (Generic generic : generics) {
            generic.print();
        }
    }

    public static void genericPrint1(List<?> generics) {
        for (Object obj : generics) {
            Generic generic = (Generic)obj;
            generic.print();
        }
    }

    public static void genericPrint2(List<? extends Generic> generics) {
        for (Generic generic : generics) {
            generic.print();
        }
    }

    public static void genericPrint3(List<? super Test1Generic> generics) {
        System.out.println("测试");
    }

    public static void testMethod() {
        Integer i1 = new Integer(10);
        String s1 = new String("aaa");
        Generic<Number> genericTest = new Generic<Number>(12345);

        //Integer是Number的子类，所以这里可以
        genericTest.show1(i1);
        //genericTest.show1(s1);

        genericTest.show2(i1);
        genericTest.show2(s1);

        genericTest.show3(i1);
        genericTest.show3(s1);

        genericTest.printMsg("111",222,"aaaa","2323.4",55.55);
    }

    /**
     * 泛型  擦除
     */
    public static void test3() {

        List<Integer> li = new ArrayList<>();
        li.add(12);
        li.add(13);
        List list = li;
        List<String> ls = list;
        System.out.println(ls.get(0));
    }

    /**
     * 不能创建一个确切的泛型类型的数组
     */
    public static void test4() {

        //也就是说下面的这个例子是不可以的：
        //List<String>[] ls = new ArrayList<String>[10];

        //而使用通配符创建泛型数组是可以的，如下面这个例子：
        List<?>[] ls1 = new ArrayList<?>[10];

        //这样也是可以的：
        List<String>[] ls2 = new ArrayList[10];

        /**
         * 下面采用通配符的方式是被允许的:数组的类型不可以是类型变量，除非是采用通配符的方式，
         * 因为对于通配符的方式，最后取出数据是要做显式的类型转换的。
         */
        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0); // OK
    }

    /**
     * 1、Java泛型不能使用原始类型 int char等
     * 2、Java泛型不能使用实例（如果要实现这样的功能，请使用反射）
     * public static <T> void add(Box<T> box) {
     * //T item = new T();
     * //box.add(item);
     * }
     * 3、Java泛型不能使用静态域 private static T t;
     * 4、Java泛型不能转换类型
     * Box<Integer> integerBox = new Box<Integer>();
     * Box<Number> numberBox = new Box<Number>();
     * //Compiler Error: Cannot cast from Box<Number> to Box<Integer>，下面用法是错误的
     * integerBox = (Box<Integer>)numberBox;
     * 5、Java泛型不能使用instanceof运算符
     * Box<Integer> integerBox = new Box<Integer>();
     * if(integerBox instanceof Box<Integer>){ }
     * 6、Java泛型不能使用数组（能创建一个确切的泛型类型的数组）
     * Box<Integer>[] arrayOfLists = new Box<Integer>[2];
     * 7、Java泛型不能使用异常
     * class Box<T> extends Exception {}
     * class Box1<T> extends Throwable {}
     * 在一个方法中，不允许捕获一个类型参数的实例  catch (T e) {}
     * throws子句中允许使用类型参数
     * 8、Java泛型不能重载
     */

    /**
     * throws子句中允许使用类型参数
     * @param <T>
     */
    class Box<T extends Exception>  {
        private int t;

        public void add(int t) throws T {
            this.t = t;
        }

        public int get() {
            return t;
        }

        //Java泛型不能重载
        //public void print(List<String> stringList) { }
        public void print(List<Integer> integerList) { }
    }


}

class Generic<T>{
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;
    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }
    public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

    public void print() {
        System.out.println("this is Generic");
    }

    public void show1(T t){
        System.out.println(t.toString());
    }

    //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
    //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
    public <E> void show3(E t){
        System.out.println(t.toString());
    }

    //在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
    public <T> void show2(T t){
        System.out.println(t.toString());
    }

    public <T> void printMsg( T... args){
        for(T t : args){
            System.out.println("泛型测试 t is " + t);
        }
    }

    /**
     * 类中的静态方法使用泛型：静态方法无法访问类上定义的泛型；
     * 如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。
     即：如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法 。

     如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
     * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     * 如：public static void show(T t){..},此时编译器会提示错误信息：
     "StaticGenerator cannot be refrenced from static context"

     */
    public static <T> void show(T t){
        System.out.println(t.toString());
    }

}

class Test1Generic extends Generic<String> {

    public Test1Generic(String key) {
        super(key);
    }

    @Override
    public String getKey() {
        //return super.getKey().toString();
        return super.getKey();
    }

    public void print() {
        System.out.println("this is Test1Generic");
    }
}

class Test2Generic extends Generic {

    public Test2Generic(String key) {
        super(key);
    }

    @Override
    public String getKey() {
        return super.getKey().toString();
    }

    public void print() {
        System.out.println("this is Test2Generic");
    }
}


//定义一个泛型接口
interface Generator<T> {
    public T next();
}


/**
 * 当实现泛型接口的类，未传入泛型实参时：
 * 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
 * 即：class FruitGenerator<T> implements Generator<T>{
 * 如果不声明泛型，如：class FruitGenerator implements Generator<T>，编译器会报错："Unknown class"
 */
class FruitGenerator<T> implements Generator<T>{
    @Override
    public T next() {
        return null;
    }
}


/**
 * 当实现泛型接口的类，传入泛型实参时：
 * 传入泛型实参时：
 * 定义一个生产器实现这个接口,虽然我们只创建了一个泛型接口Generator<T>
 * 但是我们可以为T传入无数个实参，形成无数种类型的Generator接口。
 * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
 * 即：Generator<T>，public T next();中的的T都要替换成传入的String类型。
 */
class TestGenerator implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }
}
