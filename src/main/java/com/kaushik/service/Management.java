package com.kaushik.service;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.kaushik.database.AccountDB;
import com.kaushik.database.CustomerDB;
import com.kaushik.database.EmployeeDB;
import com.kaushik.database.TransactionDB;
import com.kaushik.models.Account;
import com.kaushik.models.Customer;
import com.kaushik.models.Employee;

public class Management {
    private AccountDB accountDB;
    private EmployeeDB employeeDB;
    private CustomerDB customerDB;
    private TransactionDB transactionDB;
    private Scanner sc;
    private Employee employee;
    private Customer customer;

    Management(Scanner sc) {
        this.sc = sc;
    }

    void createCustomer() {
        String name = sc.nextLine();
        String email = sc.nextLine();
        String password = sc.nextLine();
        String address = sc.nextLine();
        Account account = new Account();
        Customer customer = new Customer(name, email, password, address, account);
        accountDB.save(account);
        customerDB.save(customer);
    }

    void createEmployee() {
        String name = sc.nextLine();
        String email = sc.nextLine();
        String password = sc.nextLine();
        String address = sc.nextLine();
        Employee employee = new Employee(name, email, password, address);
        employeeDB.save(employee);
    }

    void employeeLogin() {
        String email = sc.nextLine();
        String pasword = sc.nextLine();
        Employee employee = employeeDB.findByEmail(email);
        try {
            if (employee.getPassword().equals(pasword)) {
                this.employee = employee;
            } else {
                throw new Unauthorized("Unauthorized");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    void customerLogin() {
        String email = sc.nextLine();
        String pasword = sc.nextLine();
        Customer customer = customerDB.findByEmail(email);
        try {
            if (customer.getPassword().equals(pasword)) {
                this.customer = customer;
            } else {
                throw new Unauthorized("Unauthorized");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    void sendMoney() {
        double amount = sc.nextDouble();
    }
    // void addMoney(){
    // double amount = sc.nextDouble();
    // accountDB.save
    // }
}

final class InsufficientBalance extends RuntimeException {
    InsufficientBalance(String message) {
        super(message);
    }
}

final class Unauthorized extends RuntimeException {
    Unauthorized(String message) {
        super(message);
    }
}