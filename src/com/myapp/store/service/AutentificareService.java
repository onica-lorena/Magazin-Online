package com.myapp.store.service;

import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Utilizator;

/**
 * Serviciu pentru autentificarea utilizatorilor.
 */
public class AutentificareService {
    private ManagerBazaDeDate managerBazaDeDate;

    public AutentificareService() {
        this.managerBazaDeDate = new ManagerBazaDeDate();
    }

    /**
     * Autentifică un utilizator.
     *
     * @param email Email-ul utilizatorului.
     * @param password Parola utilizatorului.
     * @return Un obiect {@link Utilizator} dacă autentificarea reușește, altfel {@code null}.
     */
    public Utilizator autentifica(String email, String password) {
        return managerBazaDeDate.autentificareUtilizator(email, password);
    }
}
