package com.sky.hiwise.design.lesson.clazz;

public class Out {

    /**
     * 一 内部类是什么
     * Java类中不仅可以定义变量和方法，还可以定义类，这样定义在类内部的类就被称为内部类。
     * 根据定义的方式不同，内部类分为静态内部类，成员内部类，局部内部类，匿名内部类四种。
     * Java为什么要引入内部类这个概念呢？
     * 原因在于，内部类定义在类的内部，可以方便访问外部类的变量和方法，并且和其它类进行隔离。
     */

    private static int age = 1;
    private int base = 4;
    public int c = 10;

    /**
     * Inner就是静态内部类。
     * 静态内部类可以访问外部类所有的静态变量和方法，即使是private的也一样。
     * 静态内部类和一般类一致，可以定义静态变量、方法，构造方法等。
     * 其它类使用静态内部类需要使用“外部类.静态内部类”方式
     */
    public static class Inner {
        private int age = 2;
        public void print() {
            int age = 3;
            System.out.println("局部变量：" + age);
            System.out.println("内部类变量：" + this.age);
            System.out.println("外部类变量：" + Out.age);
            //System.out.println(Out.b);
            //System.out.println(Out.c);
        }
    }

    /**
     * 成员内部类可以访问外部类所有的变量和方法，包括静态和实例，私有和非私有。
     * 成员内部类，就是作为外部类的成员，可以直接使用外部类的所有成员和方法，即使是private的。
     * 和静态内部类不同的是，每一个成员内部类的实例都依赖一个外部类的实例。
     * 其它类使用内部类必须要先创建一个外部类的实例。
     *
     * 内部类可以拥有private访问权限、protected访问权限、public访问权限及包访问权限。
     * 比如上面的例子，如果成员内部类Inner用private修饰，则只能在外部类的内部访问，如果用public修饰，则任何地方都能访问；如果用protected修饰，则只能在同一个包下或者继承外部类的情况下访问；如果是默认访问权限，则只能在同一个包下访问。
     * 这一点和外部类有一点不一样，外部类只能被public和包访问两种权限修饰。
     * 我个人是这么理解的，由于成员内部类看起来像是外部类的一个成员，所以可以像类的成员一样拥有多种权限修饰。
     * 要注意的是，成员内部类不能含有static的变量和方法。因为成员内部类需要先创建了外部类，才能创建它自己的
     *
     * 成员内部类不能定义静态方法和变量（final修饰的除外）。
     * 这是因为成员内部类是非静态的，类初始化的时候先初始化静态成员，
     * 如果允许成员内部类定义静态变量，那么成员内部类的静态变量初始化顺序是有歧义的。
     */
    public class Inner2 {
        private int base = 5;
        //private final static int t = 5;
        public void print() {
            int base = 6;
            System.out.println("局部变量：" + base);
            System.out.println("内部类变量：" + this.base);
            System.out.println("外部类变量：" + Out.this.base);
        }
    }

    /**
     * 定义在方法中的类，就是局部类。
     * 局部类只能在定义该局部类的方法中使用。
     * 定义在实例方法中的局部类可以访问外部类的所有变量和方法，
     * 定义在静态方法中的局部类只能访问外部类的静态变量和方法。
     * 同时局部类还可以访问方法的参数和方法中的局部变量，这些参数和变量必须要声明为final的。
     */
    public void testInner(final int type) {
        final int d = 7;
        class Inner3 {
            public void print() {
                System.out.println(type);
                System.out.println(d);
                System.out.println(age);
                System.out.println(base);
            }
        }
        (new Inner3()).print();
    }

    public static void testStatic(final int type) {
        final int d = 8;
        class Inner4 {
            public void print() {
                System.out.println(d);
                System.out.println(type);
                System.out.println(age);
                //定义在静态方法中的局部类不可以访问外部类的实例变量
                //System.out.println(base);
            }
        }
        (new Inner4()).print();
    }

    /**
     * 匿名内部类可以出现在任何允许表达式出现的地方，定义格式：
     * new 类/接口{
     *   //匿名内部类实现部分
     * }
     * 匿名内部类可以访问外部类所有的变量和方法。
     * 匿名内部类也是不能有访问修饰符和static修饰符的。
     * 匿名内部类是唯一一种没有构造器的类。
     * 正因为其没有构造器，所以匿名内部类的使用范围非常有限，大部分匿名内部类用于接口回调。
     * 匿名内部类在编译的时候由系统自动起名为Outter$1.class。
     * 一般来说，匿名内部类用于继承其他类或是实现接口，并不需要增加额外的方法，只是对继承方法的实现或是重写。

     * view.setOnClickListener(new View.OnClickListener() {
     * @Override
     * public void onClick(View v) {
     *   Toast.makeText(v.getContext(),"click",Toast.LENGTH_SHORT).show();    }
     * });
     */
    private Object obj = new Object() {
        private String name = "匿名内部类";
        @Override
        public String toString() {
            return name;
        }
    };

    public void test() {
        Object obj = new Object() {
            @Override
            public String toString() {
                System.out.println(age);
                return String.valueOf(base);
            }
        };
        System.out.println(obj.toString());
    }

}
