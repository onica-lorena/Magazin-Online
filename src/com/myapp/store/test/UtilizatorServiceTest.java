package com.myapp.store.test;

import static org.junit.jupiter.api.Assertions.*;

import com.myapp.store.service.UtilizatorService;
import com.myapp.store.model.Utilizator;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UtilizatorServiceTest {

    private final UtilizatorService utilizatorService = new UtilizatorService();

    @Test
    public void testObtineUtilizatori() {
        List<Utilizator> utilizatori = utilizatorService.obtineUtilizatori();
        assertNotNull(utilizatori);
        assertFalse(utilizatori.isEmpty(), "Lista utilizatorilor nu ar trebui să fie goală.");
    }

    @Test
    public void testModificaRol() {
        boolean result = utilizatorService.modificaRol(2, "admin");
        assertTrue(result, "Modificarea rolului ar trebui să fie cu succes.");
    }
}
