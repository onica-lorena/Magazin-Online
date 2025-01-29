package com.myapp.store.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.myapp.store.service.InregistrareService;
import com.myapp.store.controller.ManagerBazaDeDate;

/**
 * Teste unitare pentru clasa {@link InregistrareService}.
 * Verifică funcționalitatea înregistrării utilizatorilor în diferite scenarii.
 */
public class InregistrareServiceTest {

    /**
     * Testează scenariul în care un utilizator valid este înregistrat cu succes.
     */
    @Test
    public void testInregistrareReusita() {
        ManagerBazaDeDate manager = new ManagerBazaDeDate();
        InregistrareService service = new InregistrareService();

        // Datele nu trebuie să existe înainte de test
        manager.stergeUtilizator("test@example.com");

        // Apelează metoda de înregistrare cu date valide
        String result = service.inregistreazaUtilizator("TestUser", "test@example.com", "password123");

        // Verifică dacă mesajul returnat indică succesul
        assertEquals("Utilizatorul a fost înregistrat cu succes!", result, 
            "Mesajul ar trebui să indice succesul înregistrării.");

        // Șterge utilizatorul după test pentru curățenie
        manager.stergeUtilizator("test@example.com");
    }

    /**
     * Testează scenariul în care este utilizat un email invalid.
     */
    @Test
    public void testInregistrareEmailInvalid() {
        InregistrareService service = new InregistrareService();

        // Apelează metoda de înregistrare cu un email invalid
        String result = service.inregistreazaUtilizator("TestUser", "invalidEmail", "password123");

        // Verifică dacă mesajul returnat indică un email invalid
        assertEquals("Email-ul este invalid!", result, 
            "Mesajul ar trebui să indice un email invalid.");
    }

    /**
     * Testează scenariul în care parola este nesigură (prea scurtă).
     */
    @Test
    public void testInregistrareParolaNesigura() {
        InregistrareService service = new InregistrareService();

        // Apelează metoda de înregistrare cu o parolă nesigură
        String result = service.inregistreazaUtilizator("TestUser", "test@example.com", "123");

        // Verifică dacă mesajul returnat indică o parolă nesigură
        assertEquals("Parola trebuie să aibă cel puțin 8 caractere!", result, 
            "Mesajul ar trebui să indice o parolă nesigură.");
    }

    /**
     * Testează scenariul în care se încearcă înregistrarea unui utilizator duplicat.
     */
    @Test
    public void testInregistrareDuplicata() {
        ManagerBazaDeDate manager = new ManagerBazaDeDate();
        InregistrareService service = new InregistrareService();

        // Adaugă un utilizator în baza de date
        manager.inregistrareUtilizator("TestUser", "test@example.com", "password123");

        // Apelează metoda de înregistrare pentru același utilizator
        String result = service.inregistreazaUtilizator("TestUser", "test@example.com", "password123");

        // Verifică dacă mesajul indică un utilizator duplicat
        assertNotEquals("Utilizatorul a fost înregistrat cu succes!", result, 
            "Mesajul ar trebui să indice un utilizator duplicat.");

        // Șterge utilizatorul pentru curățenie
        manager.stergeUtilizator("test@example.com");
    }
}
