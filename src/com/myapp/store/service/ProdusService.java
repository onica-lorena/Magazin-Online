package com.myapp.store.service;

import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Produs;

import java.util.List;

/**
 * Service pentru gestionarea produselor.
 * 
 * Această clasă oferă funcționalități legate de produse, incluzând adăugarea, ștergerea,
 * căutarea, sortarea și gestionarea sugestiilor de produse. Utilizează `ManagerBazaDeDate`
 * pentru a interacționa cu baza de date.
 */
public class ProdusService {
    private final ManagerBazaDeDate manager;

    /**
     * Constructor pentru inițializarea serviciului produselor.
     * Instanțiază un `ManagerBazaDeDate` pentru a gestiona conexiunile și interogările bazei de date.
     */
    public ProdusService() {
        this.manager = new ManagerBazaDeDate();
    }

    /**
     * Adaugă un produs nou în baza de date.
     *
     * @param name        Numele produsului.
     * @param description Descrierea produsului.
     * @param price       Prețul produsului.
     * @param quantity    Cantitatea disponibilă a produsului.
     * @param category    Categoria produsului.
     * @param imagePath   Calea către imaginea produsului.
     * @return `true` dacă produsul a fost adăugat cu succes, altfel `false`.
     */
    public boolean adaugaProdus(String name, String description, double price, int quantity, String category, String imagePath) {
        return manager.adaugaProdus(name, description, price, quantity, category, imagePath);
    }

    /**
     * Obține lista produselor care aparțin unei categorii specifice.
     *
     * @param category Categoria dorită.
     * @return Lista produselor din categoria specificată.
     */
    public List<Produs> obtineProdusePeCategorie(String category) {
        return manager.obtineProdusePeCategorie(category);
    }

    /**
     * Șterge un produs din baza de date.
     *
     * @param productId ID-ul produsului care trebuie șters.
     * @return `true` dacă produsul a fost șters cu succes, altfel `false`.
     */
    public boolean stergeProdus(int productId) {
        return manager.stergeProdus(productId);
    }

    /**
     * Obține lista completă a produselor din baza de date.
     *
     * @return Lista tuturor produselor ({@link Produs}).
     */
    public List<Produs> obtineToateProdusele() {
        return manager.obtineToateProdusele();
    }

    /**
     * Caută produse în baza de date folosind un cuvânt cheie.
     * 
     * Căutarea se face în numele și descrierea produselor.
     *
     * @param keyword Cuvântul cheie pentru căutare.
     * @return Lista produselor care conțin cuvântul cheie.
     */
    public List<Produs> cautaProduse(String keyword) {
        return manager.cautaProduse(keyword);
    }

    /**
     * Obține lista produselor sortate după un criteriu specific.
     * 
     * Produsele pot fi sortate în ordine crescătoare sau descrescătoare a prețului.
     *
     * @param categorie Categoria produselor de sortat (sau "Toate Produsele").
     * @param sortOrder Ordinea de sortare ("ASC" pentru crescător, "DESC" pentru descrescător).
     * @return Lista produselor sortate.
     */
    public List<Produs> obtineProduseSortate(String categorie, String sortOrder) {
        return manager.obtineProduseSortate(categorie, sortOrder);
    }

    /**
     * Obține lista de sugestii pentru un produs specific.
     * 
     * Sugestiile sunt produse asociate logic cu produsul dat, pentru recomandări.
     *
     * @param productId ID-ul produsului pentru care se dorește obținerea sugestiilor.
     * @return Lista produselor sugerate ({@link Produs}).
     */
    public List<Produs> obtineSugestiiProduse(int productId) {
        return manager.obtineSugestiiProduse(productId);
    }
}
