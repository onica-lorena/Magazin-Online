package com.myapp.store.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.myapp.store.service.AutentificareService;
import com.myapp.store.model.Utilizator;

public class AutentificareServiceTest {
    @Test
    public void testAutentificareReusita() {
        AutentificareService service = new AutentificareService();
        Utilizator utilizator = service.autentifica("admin@example.com", "admin123");
        assertNotNull(utilizator);
        assertEquals("admin", utilizator.getRole());
    }

    @Test
    public void testAutentificareEsuata() {
        AutentificareService service = new AutentificareService();
        Utilizator utilizator = service.autentifica("gresit@example.com", "parolagresita");
        assertNull(utilizator);
    }
    
    @Test
    public void testAutentificareCuDateIncomplete() {
        AutentificareService service = new AutentificareService();

        // Email gol
        Utilizator utilizator1 = service.autentifica("", "password");
        assertNull(utilizator1);

        // Parolă goală
        Utilizator utilizator2 = service.autentifica("user@example.com", "");
        assertNull(utilizator2);

        // Email și parolă nule
        Utilizator utilizator3 = service.autentifica(null, null);
        assertNull(utilizator3);
    }

    @Test
    public void testAutentificareUtilizatorInexistent() {
        AutentificareService service = new AutentificareService();

        Utilizator utilizator = service.autentifica("inexistent@example.com", "parolainvalida");
        assertNull(utilizator); // Utilizatorul nu există, deci trebuie să fie null
    }

}
