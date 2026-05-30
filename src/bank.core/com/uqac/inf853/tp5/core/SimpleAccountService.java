package com.uqac.inf853.tp5.core;

import com.uqac.inf853.tp5.api.Account;
import com.uqac.inf853.tp5.api.AccountFactory;
import com.uqac.inf853.tp5.api.AccountService;
import com.uqac.inf853.tp5.api.AuthenticationService;
import com.uqac.inf853.tp5.api.InsufficientFundsException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

public class SimpleAccountService implements AccountService {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public Account createAccount(String owner, String type) throws IllegalArgumentException, SecurityException {
        // Vérifier autorisation si un AuthenticationService est disponible
        ServiceLoader<AuthenticationService> authLoader = ServiceLoader.load(AuthenticationService.class);
        Optional<AuthenticationService> maybeAuth = StreamSupport.stream(authLoader.spliterator(), false).findFirst();
        if (maybeAuth.isPresent()) {
            boolean ok = maybeAuth.get().authorize(owner);
            if (!ok) {
                throw new SecurityException("Authorization failed for owner: " + owner);
            }
        }

        // Chercher une factory correspondant au type
        ServiceLoader<AccountFactory> loader = ServiceLoader.load(AccountFactory.class);
        AccountFactory chosen = null;
        for (AccountFactory f : loader) {
            if (f.getTypeName().equalsIgnoreCase(type)) {
                chosen = f;
                break;
            }
        }
        if (chosen == null) {
            throw new IllegalArgumentException("Type de compte inconnu: " + type);
        }
        Account account = chosen.create(owner);
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Optional<Account> findAccount(String id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @Override
    public void deposit(String id, double amount) throws IllegalArgumentException {
        Account a = accounts.get(id);
        if (a == null) throw new IllegalArgumentException("Compte introuvable: " + id);
        a.deposit(amount);
    }

    @Override
    public void withdraw(String id, double amount) throws InsufficientFundsException {
        Account a = accounts.get(id);
        if (a == null) throw new IllegalArgumentException("Compte introuvable: " + id);
        a.withdraw(amount);
    }
}


