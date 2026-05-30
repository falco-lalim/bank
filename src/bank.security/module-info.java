module bank.security {
    requires bank.api;
    provides com.uqac.inf853.tp5.api.AuthenticationService with com.uqac.inf853.tp5.security.SimpleAuthenticationService;
    exports com.uqac.inf853.tp5.security;
}

