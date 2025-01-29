package com.myapp.store.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Produs;
import com.myapp.store.model.Utilizator;

/**
 * PaginaGestionareProduse reprezintă fereastra pentru afișarea și gestionarea produselor din baza de date.
 *
 * <p>
 * Această clasă permite administratorului să vizualizeze, editeze, șteargă sau să adauge produse.
 * Include funcționalități de navigare, cum ar fi revenirea la pagina principală.
 * </p>
 */
public class PaginaGestionareProduse extends JFrame {

    /**
     * Constructor care configurează fereastra pentru gestionarea produselor.
     *
     * @param utilizator Utilizatorul curent autentificat (administrator).
     */
    public PaginaGestionareProduse(Utilizator utilizator) {
        setTitle("Gestionare Produse");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Bara de navigare
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
        List<Produs> produse = manager.obtineToateProdusele();

        JPanel produsePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        produsePanel.setBackground(Color.WHITE);

        for (Produs produs : produse) {
            JPanel produsPanel = new JPanel(new BorderLayout());
            produsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            JLabel produsLabel = new JLabel(produs.toString());
            JButton deleteButton = new JButton("Șterge");
            JButton editButton = new JButton("Editează");

            // Ștergere produs
            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Sigur doriți să ștergeți acest produs?",
                        "Confirmare",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    manager.stergeProdus(produs.getProductId());
                    JOptionPane.showMessageDialog(this, "Produs șters cu succes!");
                    dispose();
                    new PaginaGestionareProduse(utilizator);
                }
            });

            // Editare produs
            editButton.addActionListener(e -> new PaginaEditareProdus(produs, utilizator));

            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonsPanel.add(editButton);
            buttonsPanel.add(deleteButton);

            produsPanel.add(produsLabel, BorderLayout.CENTER);
            produsPanel.add(buttonsPanel, BorderLayout.EAST);

            produsePanel.add(produsPanel);
        }

        JScrollPane scrollPane = new JScrollPane(produsePanel);
        add(scrollPane, BorderLayout.CENTER);

        // Adăugare produs
        JButton adaugaProdusButton = new JButton("Adaugă Produs");
        adaugaProdusButton.addActionListener(e -> new PaginaAdaugareProdus(utilizator));
        add(adaugaProdusButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
