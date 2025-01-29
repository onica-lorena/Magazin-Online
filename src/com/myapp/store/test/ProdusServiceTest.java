package com.myapp.store.test;

import static org.junit.jupiter.api.Assertions.*;

import com.myapp.store.service.ProdusService;
import com.myapp.store.model.Produs;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProdusServiceTest {

    private final ProdusService produsService = new ProdusService();

    @Test
    public void testObtineToateProdusele() {
        List<Produs> produse = produsService.obtineToateProdusele();
        assertNotNull(produse);
        assertFalse(produse.isEmpty(), "Lista produselor nu ar trebui să fie goală.");
    }

    @Test
    public void testCautaProduse() {
        List<Produs> produse = produsService.cautaProduse("rochie");
        assertNotNull(produse);
        assertTrue(produse.stream().allMatch(p -> p.getName().toLowerCase().contains("rochie") || p.getDescription().toLowerCase().contains("rochie")),
                "Toate produsele returnate ar trebui să conțină cuvântul cheie.");
    }

    @Test
    public void testObtineProdusePeCategorie() {
        List<Produs> produse = produsService.obtineProdusePeCategorie("Rochii");
        assertNotNull(produse);
        assertTrue(produse.stream().allMatch(p -> "Rochii".equals(p.getCategory())), "Toate produsele ar trebui să aparțină categoriei 'Rochii'.");
    }

    @Test
    public void testObtineSugestiiProduse() {
        List<Produs> sugestii = produsService.obtineSugestiiProduse(1);
        assertNotNull(sugestii);
        assertFalse(sugestii.isEmpty(), "Sugestiile pentru produsul 1 nu ar trebui să fie goale.");
    }
}
