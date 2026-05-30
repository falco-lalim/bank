package com.uqac.inf853.tp5.plugin.savings;

import com.uqac.inf853.tp5.api.Account;
import com.uqac.inf853.tp5.api.AccountFactory;

public class SavingsAccountFactory implements AccountFactory {
    @Override
    public String getTypeName() {
        return "savings";
    }

    @Override
    public Account create(String owner) {
        return new SavingsAccount(owner);
    }
}


