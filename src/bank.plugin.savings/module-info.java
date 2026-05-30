module bank.plugin.savings {
    requires bank.api;
    provides com.uqac.inf853.tp5.api.AccountFactory with com.uqac.inf853.tp5.plugin.savings.SavingsAccountFactory;
    exports com.uqac.inf853.tp5.plugin.savings;
}

