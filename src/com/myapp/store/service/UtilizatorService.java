package com.myapp.store.service;

import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Utilizator;

import java.util.List;

/**
 * Service pentru operațiuni legate de utilizatori.
 */
public class UtilizatorService {
    private final ManagerBazaDeDate manager;

    public UtilizatorService() {
        this.manager = new ManagerBazaDeDate();
    }

    /**
     * Obține lista tuturor utilizatorilor.
     *
     * @return O listă de utilizatori.
     */
    public List<Utilizator> obtineUtilizatori() {
        return manager.obtineUtilizatori();
    }

    /**
     * Modifică rolul unui utilizator.
     *
     * @param userId ID-ul utilizatorului.
     * @param rol Noul rol al utilizatorului.
     * @return True dacă modificarea a fost efectuată cu succes, false altfel.
     */
    public boolean modificaRol(int userId, String rol) {
        return manager.modificaRolUtilizator(userId, rol);
    }

    /**
     * Autentifică un utilizator pe baza email-ului și parolei.
     *
     * @param email Adresa de email a utilizatorului.
     * @param parola Parola utilizatorului.
     * @return Obiectul {@link Utilizator} dacă autentificarea reușește, altfel null.
     */
    public Utilizator autentificareUtilizator(String email, String parola) {
        return manager.autentificareUtilizator(email, parola);
    }

    /**
     * Înregistrează un utilizator nou în sistem.
     *
     * @param username Numele utilizatorului.
     * @param email Adresa de email.
     * @param parola Parola utilizatorului.
     * @return Un mesaj descriptiv despre succesul sau eroarea înregistrării.
     */
    public String inregistreazaUtilizator(String username, String email, String parola) {
        return manager.inregistrareUtilizator(username, email, parola);
    }

    /**
     * Șterge un utilizator pe baza email-ului.
     *
     * @param email Adresa de email a utilizatorului de șters.
     */
    public void stergeUtilizator(String email) {
        manager.stergeUtilizator(email);
    }
}
