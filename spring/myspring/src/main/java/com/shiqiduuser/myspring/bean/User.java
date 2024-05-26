package com.shiqiduuser.myspring.bean;

/**
 * @author luzhuang
 * @version 1.0
 * @className null.java
 * @since 1.0
 */
public class User {
    private String name;
    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
