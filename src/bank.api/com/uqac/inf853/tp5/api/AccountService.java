package com.uqac.inf853.tp5.api;

import java.util.Optional;

public interface AccountService {
    Account createAccount(String owner, String type) throws IllegalArgumentException, SecurityException;
    Optional<Account> findAccount(String id);
    void deposit(String id, double amount) throws IllegalArgumentException;
    void withdraw(String id, double amount) throws InsufficientFundsException;
}


