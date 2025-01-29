package com.myapp.store.service;

import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Produs;

import java.util.List;

/**
 * Service pentru gestionarea căutărilor în catalogul de produse.
 */
public class CautareService {
    private final ManagerBazaDeDate manager;

    public CautareService() {
        this.manager = new ManagerBazaDeDate();
    }

    public List<Produs> cautaProduse(String keyword) {
        return manager.cautaProduse(keyword);
    }
}
