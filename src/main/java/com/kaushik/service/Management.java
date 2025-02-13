package com.kaushik.service;

import java.util.Scanner;

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
        String name = sc.nextLine();
        String email = sc.nextLine();
        String password = sc.nextLine();
        String address = sc.nextLine();
        Account account = new Account(customer.getCustomerId());
        Customer customer = new Customer(name, email, password, address, account.getAccountNumber());
        accountDB.save(account);
        customerDB.save(customer);
    }

    public void createEmployee() {
        String name = sc.nextLine();
        String email = sc.nextLine();
        String password = sc.nextLine();
        String address = sc.nextLine();
        Employee employee = new Employee(name, email, password, address);
        employeeDB.save(employee);
    }

    public void employeeLogin() {
        String email = sc.nextLine();
        String pasword = sc.nextLine();
        Employee employee = employeeDB.findByEmail(email);
        try {
            if (employee.getPassword().equals(pasword)) {
                this.employee = employee;
            } else {
                throw new UnauthorizedException("Unauthorized");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void customerLogin() {
        String email = sc.nextLine();
        String pasword = sc.nextLine();
        Customer customer = customerDB.findByEmail(email);
        try {
            if (customer.getPassword().equals(pasword)) {
                this.customer = customer;
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

            String receiverEmail = sc.nextLine();
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
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMoney() {
        double amount = sc.nextDouble();
        Account account = accountDB.findByAccountNumber(customer.getAccountNumber());
        account.setBalance(account.getBalance() + amount);
        accountDB.save(account);
    }

    public void viewAccountDetails() {
        Account account = accountDB.findByAccountNumber(customer.getAccountNumber());
        account.display();
    }

    public void viewCustomerDetails() {
        if (this.employee == null) {
            throw new UnauthorizedException("Unauthorized");
        }
        String email = sc.nextLine();
        Customer customer = customerDB.findByEmail(email);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        customer.display();
    }
}