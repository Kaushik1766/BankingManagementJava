package com.kaushik.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaushik.models.Customer;

public interface CustomerDB extends JpaRepository<Customer, String> {
    Customer findByEmail(String email);
}
