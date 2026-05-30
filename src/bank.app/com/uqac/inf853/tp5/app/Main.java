package com.uqac.inf853.tp5.app;

import com.uqac.inf853.tp5.api.Account;
import com.uqac.inf853.tp5.api.AccountFactory;
import com.uqac.inf853.tp5.api.InsufficientFundsException;
import com.uqac.inf853.tp5.core.SimpleAccountService;
import com.uqac.inf853.tp5.api.AccountService;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demo JPMS : Système bancaire modulaire ===");

        // 1) lister les AccountFactory disponibles
        ServiceLoader<AccountFactory> loader = ServiceLoader.load(AccountFactory.class);
        System.out.println("Factories disponibles (types de comptes) :");
        for (AccountFactory f : loader) {
            System.out.println(" - " + f.getTypeName() + " (" + f.getClass().getName() + ")");
        }

        // 2) utiliser le service de comptes (implémentation dans bank.core)
        AccountService service = new SimpleAccountService();

        try {
            System.out.println("\nCréation d'un compte courant (checking) pour Alice");
            Account acc1 = service.createAccount("Alice", "checking");
            System.out.println("Compte créé: id=" + acc1.getId() + ", owner=" + acc1.getOwner());

            System.out.println("Dépôt de 100.0 sur le compte d'Alice");
            service.deposit(acc1.getId(), 100.0);
            System.out.println("Solde: " + acc1.getBalance());

            System.out.println("\nCréation d'un compte épargne (savings) pour Bob (plugin)");
            Account acc2 = service.createAccount("Bob", "savings");
            System.out.println("Compte créé: id=" + acc2.getId() + ", owner=" + acc2.getOwner());

            System.out.println("Dépôt de 200.0 sur le compte de Bob (savings)");
            service.deposit(acc2.getId(), 200.0);
            System.out.println("Solde: " + acc2.getBalance());

            System.out.println("\nTentative de retrait 150.0 sur le compte d'Alice");
            service.withdraw(acc1.getId(), 150.0);
            System.out.println("Retrait réussi, solde: " + acc1.getBalance());
        } catch (InsufficientFundsException e) {
            System.err.println("Erreur: fonds insuffisants -> " + e.getMessage());
        } catch (IllegalArgumentException | SecurityException e) {
            System.err.println("Erreur: " + e.getMessage());
        }

        System.out.println("\n=== Fin de la démo ===");
    }
}


