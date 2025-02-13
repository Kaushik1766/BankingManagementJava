package com.kaushik.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaushik.models.Account;

public interface AccountDB extends JpaRepository<Account, String> {
    void saveAll(List<Account> customer);

    Account findByAccountNumber(String accountNumber);
}
