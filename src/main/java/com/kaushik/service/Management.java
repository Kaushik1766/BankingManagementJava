package com.kaushik.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaushik.database.AccountDB;
import com.kaushik.database.CustomerDB;
import com.kaushik.database.EmployeeDB;
import com.kaushik.database.TransactionDB;
import com.kaushik.exceptions.UnauthorizedException;
import com.kaushik.models.Account;
import com.kaushik.models.Customer;
import com.kaushik.models.Employee;
import com.kaushik.models.Transaction;

import lombok.Getter;

@Getter
@Service
public class Management {
    private AccountDB accountDB;
    private EmployeeDB employeeDB;
    private CustomerDB customerDB;
    private TransactionDB transactionDB;
    private Scanner sc;
    private Employee employee;
    private Customer customer;

    public Management(Scanner sc) {
        this.sc = sc;
    }

    public void createCustomer() {
        System.out.println("Enter customer name:");
        String name = sc.nextLine();
        System.out.println("Enter customer email:");
        String email = sc.nextLine();
        System.out.println("Enter customer password:");
        String password = sc.nextLine();
        System.out.println("Enter customer address:");
        String address = sc.nextLine();
        Account account = new Account();
        customer = new Customer(name, email, password, address, account.getAccountNumber());
        account.setAccountNumber(customer.getAccountNumber());
        accountDB.save(account);
        customerDB.save(customer);
        System.out.println("Customer created successfully!");
    }

    public void createEmployee() {
        System.out.println("Enter employee name:");
        String name = sc.nextLine();
        System.out.println("Enter employee email:");
        String email = sc.nextLine();
        System.out.println("Enter employee password:");
        String password = sc.nextLine();
        System.out.println("Enter employee address:");
        String address = sc.nextLine();
        Employee employee = new Employee(name, email, password, address);
        employeeDB.save(employee);
        System.out.println("Employee created successfully!");
    }

    public void employeeLogin() {
        System.out.println("Enter employee email:");
        String email = sc.nextLine();
        System.out.println("Enter password:");
        String pasword = sc.nextLine();
        Employee employee = employeeDB.findByEmail(email);
        try {
            if (employee.getPassword().equals(pasword)) {
                this.employee = employee;
                System.out.println("Login successful!");
            } else {
                throw new UnauthorizedException("Unauthorized");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void customerLogin() {
        System.out.println("Enter customer email:");
        String email = sc.nextLine();
        System.out.println("Enter password:");
        String pasword = sc.nextLine();
        Customer customer = customerDB.findByEmail(email);
        try {
            if (customer.getPassword().equals(pasword)) {
                this.customer = customer;
                System.out.println("Login successful!");
            } else {
                throw new UnauthorizedException("Unauthorized");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void logout() {
        this.employee = null;
        this.customer = null;
        System.out.println("Logged out successfully!");
    }

    public void sendMoney() {
        try {
            // validation
            if (this.employee != null) {
                throw new UnauthorizedException("Employee cant send money");
            }
            if (this.customer == null) {
                throw new UnauthorizedException("Unauthorized");
            }

            System.out.println("Enter receiver's email:");
            String receiverEmail = sc.nextLine();
            System.out.println("Enter amount to send:");
            double amount = sc.nextDouble();
            Customer receiver = customerDB.findByEmail(receiverEmail);
            Account senderAccount = accountDB.findByAccountNumber(customer.getAccountNumber());

            if (receiver == null) {
                throw new RuntimeException("Receiver not found");
            }
            Account receiverAccount = accountDB.findByAccountNumber(receiver.getAccountNumber());
            if (senderAccount.getBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);
            Transaction transaction = new Transaction(senderAccount.getAccountNumber(),
                    receiverAccount.getAccountNumber(), amount);

            // saving
            transactionDB.save(transaction);
            accountDB.save(senderAccount);
            accountDB.save(receiverAccount);
            System.out.println("Money sent successfully!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMoney() {
        System.out.println("Enter amount to add:");
        double amount = sc.nextDouble();
        Account account = accountDB.findByAccountNumber(customer.getAccountNumber());
        account.setBalance(account.getBalance() + amount);
        accountDB.save(account);
        System.out.println("Money added successfully!");
    }

    public void viewAccountDetails() {
        Account account = accountDB.findByAccountNumber(customer.getAccountNumber());
        account.display();
    }

    public void viewCustomerDetails() {
        if (this.employee == null) {
            throw new UnauthorizedException("Unauthorized");
        }
        System.out.println("Enter customer email to view details:");
        String email = sc.nextLine();
        Customer customer = customerDB.findByEmail(email);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        customer.display();
    }
}