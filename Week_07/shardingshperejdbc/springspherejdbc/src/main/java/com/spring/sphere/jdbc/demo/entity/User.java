package com.spring.sphere.jdbc.demo.entity;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String userName;
    private int userAge;

    public User() {}

    public User(String name, int age) {
        this.userName = name;
        this.userAge = age;
    }
}
