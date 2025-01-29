package com.myapp.store.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Reprezintă coșul de cumpărături al unui utilizator.
 */
public class Cos {
    private List<Produs> produse = new ArrayList<>();

    /**
     * Adaugă un produs în coș.
     *
     * @param produs Produsul care va fi adăugat.
     */
    public void adaugaProdus(Produs produs) {
        produse.add(produs);
    }

    /**
     * Elimină un produs din coș.
     *
     * @param produs Produsul care va fi eliminat.
     */
    public void eliminaProdus(Produs produs) {
        produse.remove(produs);
    }

    /**
     * Calculează suma totală a produselor din coș.
     *
     * @return Totalul calculat.
     */
    public double calculeazaTotal() {
        return produse.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();
    }

    /** @return Lista produselor din coș. */
    public List<Produs> getProduse() {
        return produse;
    }

    @Override
    public String toString() {
        return "Cos{" +
                "produse=" + produse +
                '}';
    }
}
