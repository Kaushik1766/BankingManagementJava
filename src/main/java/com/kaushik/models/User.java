package com.kaushik.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class User {
    private String name;
    private String email;
    private String password;
    private String address;

    public abstract void display();
}