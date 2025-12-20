package com.shiqidu.springboot.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
    private Integer id;

    private String name;

    private Integer age;

    private Boolean sex;

    public User(String name, Integer age, Boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    private static final long serialVersionUID = 1L;
}