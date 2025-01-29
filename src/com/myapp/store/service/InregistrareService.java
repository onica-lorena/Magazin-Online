package com.myapp.store.service;

import com.myapp.store.controller.ManagerBazaDeDate;

/**
 * Serviciu pentru înregistrarea utilizatorilor.
 */
public class InregistrareService {
    private ManagerBazaDeDate managerBazaDeDate;

    public InregistrareService() {
        this.managerBazaDeDate = new ManagerBazaDeDate();
    }

    /**
     * Înregistrează un utilizator nou.
     *
     * @param username Numele utilizatorului.
     * @param email    Email-ul utilizatorului.
     * @param password Parola utilizatorului.
     * @return Un mesaj descriptiv despre succesul sau eșecul înregistrării.
     */
    public String inregistreazaUtilizator(String username, String email, String password) {
        if (username == null || username.trim().isEmpty()) {
            return "Numele utilizatorului este obligatoriu!";
        }
        if (!validareEmail(email)) {
            return "Email-ul este invalid!";
        }
        if (!validareParola(password)) {
            return "Parola trebuie să aibă cel puțin 8 caractere!";
        }

        // Apelăm metoda din ManagerBazaDeDate și returnăm mesajele corespunzătoare
        String rezultat = managerBazaDeDate.inregistrareUtilizator(username, email, password);
        if ("success".equals(rezultat)) {
            return "Utilizatorul a fost înregistrat cu succes!";
        }
        return rezultat; // Returnăm mesajul descriptiv generat de ManagerBazaDeDate
    }

    /**
     * Validează adresa de email.
     *
     * @param email Email-ul de validat.
     * @return True dacă email-ul este valid, false altfel.
     */
    private boolean validareEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    /**
     * Validează parola utilizatorului.
     *
     * @param password Parola de validat.
     * @return True dacă parola respectă cerințele, false altfel.
     */
    private boolean validareParola(String password) {
        return password != null && password.length() >= 8;
    }
}
