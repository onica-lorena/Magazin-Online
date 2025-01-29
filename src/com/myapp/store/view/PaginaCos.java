package com.myapp.store.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.myapp.store.component.BaraNavigare;
import com.myapp.store.model.Produs;
import com.myapp.store.model.Utilizator;
import com.myapp.store.service.ComandaService;

/**
 * PaginaCos reprezintă fereastra pentru afișarea coșului de cumpărături.
 */
public class PaginaCos extends JFrame {

    private JLabel totalLabel; // Eticheta pentru suma totală
    private JPanel produsePanel; // Panoul cu produsele din coș
    private Utilizator utilizator; // Utilizatorul curent
    private ComandaService comandaService; // Serviciul pentru comenzi
    private Set<Integer> ordineaProduselor; // Ordinea în care utilizatorul adaugă produsele

    /**
     * Constructor care configurează fereastra pentru coșul de cumpărături.
     *
     * @param utilizator Utilizatorul autentificat.
     */
    public PaginaCos(Utilizator utilizator) {
        this.utilizator = utilizator;
        this.comandaService = new ComandaService();
        this.ordineaProduselor = new LinkedHashSet<>(); // Ordine de adăugare unică și păstrată

        setTitle("Coș de Cumpărături");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adaugă bara de navigare
        JPanel baraNavigare = BaraNavigare.creeazaBaraNavigare(this, utilizator);
        add(baraNavigare, BorderLayout.NORTH);

        // Panoul pentru afișarea produselor din coș
        produsePanel = new JPanel();
        produsePanel.setLayout(new BoxLayout(produsePanel, BoxLayout.Y_AXIS));
        produsePanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(produsePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // Panoul pentru suma totală și finalizarea comenzii
        JPanel checkoutPanel = new JPanel(new BorderLayout());
        checkoutPanel.setBackground(Color.LIGHT_GRAY);

        totalLabel = new JLabel("Total: 0 RON", JLabel.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(Color.BLACK);
        checkoutPanel.add(totalLabel, BorderLayout.CENTER);

        JButton checkoutButton = new JButton("Finalizează Comanda");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutButton.setBackground(Color.RED);
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.addActionListener(e -> finalizeazaComanda());
        checkoutPanel.add(checkoutButton, BorderLayout.EAST);

        add(checkoutPanel, BorderLayout.SOUTH);

        actualizeazaCos(); // Inițializează afișarea produselor
        setVisible(true);
    }

    /**
     * Actualizează lista de produse din coș și suma totală.
     */
    private void actualizeazaCos() {
        produsePanel.removeAll(); // Golește panoul curent
        List<Produs> produseCos = comandaService.obtineProduseDinCos(utilizator.getUserId());

        // Adăugăm produsele noi în ordinea lor
        for (Produs produs : produseCos) {
            ordineaProduselor.add(produs.getProductId());
        }

        double total = 0;

        // Filtrăm produsele din coș în ordinea utilizatorului
        List<Produs> produseOrdonate = new ArrayList<>();
        for (Integer productId : ordineaProduselor) {
            produseCos.stream()
                .filter(produs -> produs.getProductId() == productId)
                .findFirst()
                .ifPresent(produseOrdonate::add);
        }

        for (Produs produs : produseOrdonate) {
            JPanel produsPanel = new JPanel();
            produsPanel.setLayout(new BorderLayout());
            produsPanel.setBackground(Color.WHITE);
            produsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            produsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150)); // Dimensiune fixă pe înălțime

            // Imaginea produsului
            JLabel imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            try {
                String fullPath = "C:\\Users\\onica\\Downloads\\Online Store\\images\\" + produs.getImagePath();
                ImageIcon imageIcon = new ImageIcon(fullPath);
                Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                imageLabel.setText("Imagine indisponibilă");
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            }

            // Eticheta pentru detalii produs
            JLabel produsLabel = new JLabel(produs.getName() + " - " + produs.getPrice() + " RON");
            produsLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            // Panou pentru cantitate
            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            quantityPanel.setBackground(Color.WHITE);
            JLabel quantityLabel = new JLabel("Cantitate: ");
            JTextField quantityField = new JTextField(String.valueOf(produs.getQuantity()), 3);
            quantityField.setHorizontalAlignment(JTextField.CENTER);

            JButton updateButton = new JButton("Actualizează");
            updateButton.setFont(new Font("Arial", Font.BOLD, 12));
            updateButton.setBackground(Color.LIGHT_GRAY);
            updateButton.setForeground(Color.BLACK);
            updateButton.addActionListener(e -> {
                try {
                    int newQuantity = Integer.parseInt(quantityField.getText());
                    if (newQuantity > 0) {
                        comandaService.actualizeazaCantitateInCos(utilizator.getUserId(), produs.getProductId(), newQuantity);
                        actualizeazaCos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Cantitatea trebuie să fie mai mare decât 0!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Introduceți un număr valid!");
                }
            });

            quantityPanel.add(quantityLabel);
            quantityPanel.add(quantityField);
            quantityPanel.add(updateButton);

            // Buton pentru eliminare produs
            JButton removeButton = new JButton("Elimină");
            removeButton.setFont(new Font("Arial", Font.BOLD, 12));
            removeButton.setBackground(Color.BLACK);
            removeButton.setForeground(Color.WHITE);
            removeButton.addActionListener(e -> {
                ordineaProduselor.remove(produs.getProductId()); // Eliminăm produsul din listă
                comandaService.eliminaProdusDinCos(utilizator.getUserId(), produs.getProductId());
                actualizeazaCos();
            });

            // Panou pentru partea centrală cu informațiile produsului
            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.setBackground(Color.WHITE);
            centerPanel.add(produsLabel, BorderLayout.NORTH);
            centerPanel.add(quantityPanel, BorderLayout.SOUTH);

            // Adaugă componente în panou
            produsPanel.add(imageLabel, BorderLayout.WEST); // Imaginea în partea stângă
            produsPanel.add(centerPanel, BorderLayout.CENTER); // Detalii produs în centru
            produsPanel.add(removeButton, BorderLayout.EAST); // Buton eliminare în dreapta

            produsePanel.add(produsPanel);

            total += produs.getPrice() * produs.getQuantity();
        }

        totalLabel.setText("Total: " + total + " RON");
        produsePanel.revalidate();
        produsePanel.repaint();
    }

    /**
     * Finalizează comanda și redirecționează utilizatorul.
     */
    private void finalizeazaComanda() {
        boolean success = comandaService.finalizeazaComanda(utilizator.getUserId());
        if (success) {
            JOptionPane.showMessageDialog(this, "Comanda a fost finalizată cu succes!");
            dispose();
            new PaginaCont(utilizator);
        } else {
            JOptionPane.showMessageDialog(this, "Eroare la finalizarea comenzii.");
        }
    }
}
