package com.myapp.store.service;

import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Produs;

import java.util.List;

/**
 * Service pentru gestionarea comenzilor și a operațiunilor legate de coș.
 */
public class ComandaService {
    private final ManagerBazaDeDate manager;

    /**
     * Constructor pentru inițializarea serviciului de comenzi.
     */
    public ComandaService() {
        this.manager = new ManagerBazaDeDate();
    }

    /**
     * Adaugă o nouă comandă pentru un utilizator.
     *
     * @param userId      ID-ul utilizatorului.
     * @param produse     Lista ID-urilor produselor incluse în comandă.
     * @param totalAmount Suma totală a comenzii.
     * @return {@code true} dacă comanda a fost adăugată cu succes, altfel {@code false}.
     */
    public boolean adaugaComanda(int userId, List<Integer> produse, double totalAmount) {
        return manager.adaugaComanda(userId, produse, totalAmount);
    }

    /**
     * Obține comenzile unui utilizator specific.
     *
     * @param userId ID-ul utilizatorului.
     * @return Lista comenzilor utilizatorului sub formă de descrieri.
     */
    public List<String> obtineComenziUtilizator(int userId) {
        return manager.obtineComenziUtilizator(userId);
    }

    /**
     * Obține toate comenzile din baza de date.
     *
     * @return Lista tuturor comenzilor sub formă de descrieri.
     */
    public List<String> obtineToateComenzile() {
        return manager.obtineToateComenzile();
    }

    /**
     * Obține produsele din coșul unui utilizator.
     *
     * @param userId ID-ul utilizatorului.
     * @return Lista produselor din coș.
     */
    public List<Produs> obtineProduseDinCos(int userId) {
        return manager.obtineProduseDinCos(userId);
    }

    /**
     * Elimină un produs din coșul unui utilizator.
     *
     * @param userId    ID-ul utilizatorului.
     * @param productId ID-ul produsului care trebuie eliminat.
     */
    public void eliminaProdusDinCos(int userId, int productId) {
        manager.eliminaProdusDinCos(userId, productId);
    }

    /**
     * Finalizează comanda unui utilizator.
     *
     * @param userId ID-ul utilizatorului care finalizează comanda.
     * @return {@code true} dacă comanda a fost finalizată cu succes, altfel {@code false}.
     */
    public boolean finalizeazaComanda(int userId) {
        return manager.finalizeazaComanda(userId);
    }

    /**
     * Actualizează cantitatea unui produs din coșul unui utilizator.
     *
     * @param userId      ID-ul utilizatorului.
     * @param productId   ID-ul produsului.
     * @param newQuantity Noua cantitate.
     * @return {@code true} dacă actualizarea a fost realizată cu succes, altfel {@code false}.
     */
    public boolean actualizeazaCantitateInCos(int userId, int productId, int newQuantity) {
        return manager.actualizeazaCantitateInCos(userId, productId, newQuantity);
    }
}
