package com.kaushik.database;

import java.util.ArrayList;

import com.kaushik.models.Customer;
import com.kaushik.models.Employee;
import com.kaushik.models.Transaction;

final class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Insufficient balance");
    }
}

final class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException() {
        super("Customer not found");
    }
}

public class Database {
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void createTransaction(Customer from, Customer to, double amount) {
        Transaction transaction = new Transaction(from.getAccount(), to.getAccount(), amount);
        transactions.add(transaction);
    }

    public void deposit(Customer customer, double amount) {
        customer.getAccount().setBalance(customer.getAccount().getBalance() + amount);
    }

    public void withdraw(Customer customer, double amount) throws Exception {
        if (customer.getAccount().getBalance() < amount) {
            throw new InsufficientBalanceException();
        } else {
            customer.getAccount().setBalance(customer.getAccount().getBalance() - amount);
        }
    }

    public Customer searchCustomer(String email) throws Exception {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException();
    }

}
