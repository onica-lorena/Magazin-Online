package com.myapp.store.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.myapp.store.component.BaraNavigare;
import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Utilizator;

/**
 * PaginaCont reprezintă fereastra pentru afișarea detaliilor contului utilizatorului.
 *
 * <p>
 * Această clasă afișează informații despre utilizatorul curent autentificat, inclusiv numele,
 * email-ul și rolul, în plus față de o listă a comenzilor plasate de acesta. Utilizatorul poate
 * naviga între pagini și se poate deconecta din aplicație.
 * </p>
 */
public class PaginaCont extends JFrame {

    /**
     * Constructor care configurează fereastra pentru afișarea detaliilor contului utilizatorului.
     *
     * @param utilizator Utilizatorul autentificat.
     */
    public PaginaCont(Utilizator utilizator) {
        setTitle("Contul Meu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Bara de navigare
        JPanel baraNavigare = BaraNavigare.creeazaBaraNavigare(this, utilizator);
        add(baraNavigare, BorderLayout.NORTH);

        // Panou principal pentru detalii utilizator și comenzi
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Detalii utilizator
        JPanel userDetailsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        userDetailsPanel.setBorder(BorderFactory.createTitledBorder("Detalii Utilizator"));

        userDetailsPanel.add(new JLabel("Nume utilizator:"));
        userDetailsPanel.add(new JLabel(utilizator.getUsername()));

        userDetailsPanel.add(new JLabel("Email:"));
        userDetailsPanel.add(new JLabel(utilizator.getEmail()));

        userDetailsPanel.add(new JLabel("Rol:"));
        userDetailsPanel.add(new JLabel(utilizator.getRole()));

        mainPanel.add(userDetailsPanel, BorderLayout.NORTH);

        // Comenzile utilizatorului
        ManagerBazaDeDate manager = new ManagerBazaDeDate();
        List<String> comenzi = manager.obtineComenziUtilizator(utilizator.getUserId());
        JPanel comenziPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        comenziPanel.setBorder(BorderFactory.createTitledBorder("Comenzile Tale"));

        if (comenzi.isEmpty()) {
            comenziPanel.add(new JLabel("Nu există comenzi plasate."));
        } else {
            for (String comanda : comenzi) {
                comenziPanel.add(new JLabel(comanda));
            }
        }

        JScrollPane scrollPane = new JScrollPane(comenziPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buton de deconectare
        JButton logoutButton = new JButton("Deconectare");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> {
            dispose();
            new PaginaAutentificare(); // Redirecționează utilizatorul la pagina de autentificare
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(logoutButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
