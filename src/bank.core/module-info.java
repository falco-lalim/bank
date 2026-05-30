module bank.core {
    requires bank.api;
    uses com.uqac.inf853.tp5.api.AuthenticationService;
    provides com.uqac.inf853.tp5.api.AccountFactory with com.uqac.inf853.tp5.core.SimpleAccountFactory;
    exports com.uqac.inf853.tp5.core;
}

