package com.sky.hiwise.design.lesson.basic;

public class Student implements Comparable<Student>{

    private Integer age;

    private String name;

    public Student(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        return (this.getAge()).compareTo(o.getAge());
        //return (o.getAge()).compareTo(this.getAge());
    }

//    @Override
//    public int hashCode() {
//        return 1;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        return this.hashCode() == o.hashCode();
//    }
}
