package com.myapp.store.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.myapp.store.service.CautareService;
import com.myapp.store.model.Produs;

import java.util.List;

public class CautareServiceTest {

    @Test
    public void testCautaProduseCuKeywordExistent() {
        CautareService cautareService = new CautareService();

        // Căutare produs după un cuvânt cheie existent
        String keyword = "rochie";
        List<Produs> rezultate = cautareService.cautaProduse(keyword);

        assertNotNull(rezultate, "Lista de rezultate nu ar trebui să fie null");
        assertTrue(rezultate.size() > 0, "Rezultatele ar trebui să conțină produse pentru cuvântul cheie existent");
    }

    @Test
    public void testCautaProduseCuKeywordInexistent() {
        CautareService cautareService = new CautareService();

        // Căutare produs după un cuvânt cheie inexistent
        String keyword = "produsInexistent";
        List<Produs> rezultate = cautareService.cautaProduse(keyword);

        assertNotNull(rezultate, "Lista de rezultate nu ar trebui să fie null");
        assertTrue(rezultate.isEmpty(), "Nu ar trebui să existe produse pentru un cuvânt cheie inexistent");
    }
}
