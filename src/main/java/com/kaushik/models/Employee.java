package com.kaushik.models;

import java.util.UUID;

public class Employee extends User {
    private String employeeId = UUID.randomUUID().toString();

    public Employee(String name, String email, String password, String address) {
        super(name, email, password, address);
    }

    public void display() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
    }

}
