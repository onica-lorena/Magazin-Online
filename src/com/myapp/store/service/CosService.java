package com.myapp.store.service;

import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Produs;

import java.util.List;

/**
 * Service pentru gestionarea coșului de cumpărături.
 */
public class CosService {
    private final ManagerBazaDeDate manager;
    private final int userId;

    /**
     * Constructor pentru inițializarea serviciului coșului.
     *
     * @param userId ID-ul utilizatorului pentru care se gestionează coșul.
     */
    public CosService(int userId) {
        this.manager = new ManagerBazaDeDate();
        this.userId = userId;
    }

    /**
     * Adaugă un produs în coșul utilizatorului.
     *
     * @param productId ID-ul produsului.
     * @param quantity  Cantitatea de adăugat.
     * @return {@code true} dacă produsul a fost adăugat cu succes, altfel {@code false}.
     */
    public boolean adaugaInCos(int productId, int quantity) {
        return manager.adaugaProdusInCos(userId, productId, quantity);
    }

    /**
     * Elimină un produs din coșul utilizatorului.
     *
     * @param productId ID-ul produsului care trebuie eliminat.
     */
    public void stergeDinCos(int productId) {
        manager.eliminaProdusDinCos(userId, productId);
    }

    /**
     * Actualizează cantitatea unui produs din coșul utilizatorului.
     *
     * @param productId   ID-ul produsului.
     * @param newQuantity Noua cantitate.
     * @return {@code true} dacă actualizarea a fost realizată cu succes, altfel {@code false}.
     */
    public boolean actualizeazaCantitateInCos(int productId, int newQuantity) {
        return manager.actualizeazaCantitateInCos(userId, productId, newQuantity);
    }

    /**
     * Obține lista produselor din coșul utilizatorului.
     *
     * @return Lista produselor din coș.
     */
    public List<Produs> obtineProduseDinCos() {
        return manager.obtineProduseDinCos(userId);
    }

    /**
     * Calculează totalul coșului utilizatorului.
     *
     * @return Totalul prețurilor produselor din coș.
     */
    public double calculeazaTotalCos() {
        return manager.calculeazaSumaTotalaCos(userId);
    }

    /**
     * Finalizează comanda pentru coșul utilizatorului.
     *
     * @return {@code true} dacă comanda a fost finalizată cu succes, altfel {@code false}.
     */
    public boolean finalizeazaComanda() {
        return manager.finalizeazaComanda(userId);
    }
}
