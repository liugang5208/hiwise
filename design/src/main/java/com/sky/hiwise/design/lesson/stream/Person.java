package com.sky.hiwise.design.lesson.stream;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {

    private static final long serialVersionUID = -4333316296251054416L;

    private String name;

    //private int age;

    //该元素不会进行jvm默认的序列化 也可以自己完成这个元素的序列化
    private transient int age;

    //ArrayList

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{
        s.defaultWriteObject();  //把jvm默认的序列化元素序列化
        s.writeInt(age);  //自己完成序列化
    }

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.age = s.readInt();
    }
}
