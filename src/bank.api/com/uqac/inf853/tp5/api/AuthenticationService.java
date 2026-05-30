package com.uqac.inf853.tp5.api;

/**
 * Simple authentication/authorization service (demo).
 */
public interface AuthenticationService {
    /**
     * Autorise l'ouverture d'un compte pour le propriétaire donné.
     */
    boolean authorize(String owner);
}


