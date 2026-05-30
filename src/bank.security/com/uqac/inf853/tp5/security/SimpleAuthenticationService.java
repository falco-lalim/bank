    package com.uqac.inf853.tp5.security;

  import com.uqac.inf853.tp5.api.AuthenticationService;

  public class SimpleAuthenticationService implements AuthenticationService {
    @Override
    public boolean authorize(String owner) {
        System.out.println("[security] authorizing owner=" + owner);
        return owner != null && !owner.trim().isEmpty();
    }
}


