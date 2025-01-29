package com.myapp.store.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Utilizator;

/**
 * PaginaGestionareComenzi reprezintă fereastra pentru afișarea și gestionarea comenzilor din baza de date.
 *
 * <p>
 * Această clasă permite administratorului să vizualizeze toate comenzile într-un format listă.
 * Include funcționalități de navigare în aplicație, cum ar fi revenirea la pagina principală.
 * </p>
 */
public class PaginaGestionareComenzi extends JFrame {

    /**
     * Constructor care configurează fereastra pentru gestionarea comenzilor.
     *
     * @param utilizator Utilizatorul curent autentificat (administrator).
     */
    public PaginaGestionareComenzi(Utilizator utilizator) {
        setTitle("Gestionare Comenzi");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adaugăm bara de navigare
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton homeButton = new JButton("Pagina Principală");
        homeButton.addActionListener(e -> {
            dispose();
            new PaginaAdministrator(utilizator);
        });
        topPanel.add(homeButton, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);

        // Conținutul principal
        ManagerBazaDeDate manager = new ManagerBazaDeDate();
        List<String> comenzi = manager.obtineComenzi();

        JPanel comenziPanel = new JPanel(new GridLayout(0, 1));
        for (String comanda : comenzi) {
            JLabel comandaLabel = new JLabel(comanda);
            comenziPanel.add(comandaLabel);
        }

        JScrollPane scrollPane = new JScrollPane(comenziPanel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
