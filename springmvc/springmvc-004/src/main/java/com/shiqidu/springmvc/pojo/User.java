package com.shiqidu.springmvc.pojo;

import java.util.Arrays;

/**
 * @author luzhuang
 * @version 1.0
 * @className null.java
 * @since 1.0
 */
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer sex;
    private String[] interest;
    private String intro;
    private Integer age;


    public User()
    {
    }

    public User(Integer id, String name, String password, Integer sex, String[] interest, String intro, Integer age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.interest = interest;
        this.intro = intro;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String[] getInterest() {
        return interest;
    }

    public void setInterest(String[] interest) {
        this.interest = interest;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", interest=" + Arrays.toString(interest) +
                ", intro='" + intro + '\'' +
                ", age=" + age +
                '}';
    }
}
