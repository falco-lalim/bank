package com.uqac.inf853.tp5.plugin.savings;

import com.uqac.inf853.tp5.api.Account;
import com.uqac.inf853.tp5.api.InsufficientFundsException;
import java.util.UUID;
import java.util.Objects;

public class SavingsAccount implements Account {
    private final String id;
    private final String owner;
    private double balance;
    private final double interestRate = 0.01; // 1% per deposit for demo

    public SavingsAccount(String owner) {
        this.id = UUID.randomUUID().toString();
        this.owner = Objects.requireNonNull(owner);
        this.balance = 0.0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public synchronized double getBalance() {
        return balance;
    }

    @Override
    public synchronized void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Montant de dépôt doit être positif");
        balance += amount + amount * interestRate;
    }

    @Override
    public synchronized void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Montant de retrait doit être positif");
        if (balance < amount) throw new InsufficientFundsException("Fonds insuffisants: " + balance);
        balance -= amount;
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
}


