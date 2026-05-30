package com.uqac.inf853.tp5.core;

import com.uqac.inf853.tp5.api.Account;
import com.uqac.inf853.tp5.api.AccountFactory;

/**
 * Factory par défaut : type "checking"
 */
public class SimpleAccountFactory implements AccountFactory {
    @Override
    public String getTypeName() {
        return "checking";
    }

    @Override
    public Account create(String owner) {
        return new SimpleAccount(owner);
    }
}


