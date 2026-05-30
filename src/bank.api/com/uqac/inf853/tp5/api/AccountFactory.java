package com.uqac.inf853.tp5.api;

/**
 * Service interface pour fournir des types de comptes (ex: "checking", "savings").
 * Découvert via ServiceLoader.
 */
public interface AccountFactory {
    String getTypeName();
    Account create(String owner);
}


