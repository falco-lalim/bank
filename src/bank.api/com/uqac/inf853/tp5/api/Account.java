package com.uqac.inf853.tp5.api;

public interface Account {
    String getId();
    String getOwner();
    double getBalance();
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientFundsException;
}


