package com.kaushik.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
abstract class User {
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;

    public abstract void display();
}