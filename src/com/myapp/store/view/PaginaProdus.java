package com.myapp.store.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import com.myapp.store.component.BaraNavigare;
import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Produs;
import com.myapp.store.model.Utilizator;

/**
 * PaginaProdus afișează detalii despre un produs și sugerează produse complementare.
 */
public class PaginaProdus extends JFrame {

    private ManagerBazaDeDate manager; // Managerul bazei de date
    private Produs produs; // Produsul afișat
    private Utilizator utilizator;

    /**
     * Constructor care configurează fereastra pentru afișarea unui produs.
     *
     * @param produs  Produsul curent.
     * @param manager Managerul bazei de date.
     * @param utilizator Utilizatorul autentificat.
     */
    public PaginaProdus(Produs produs, ManagerBazaDeDate manager, Utilizator utilizator) {
        this.produs = produs;
        this.manager = manager;
        this.utilizator = utilizator;

        setTitle("Detalii Produs - " + produs.getName());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adaugă bara de navigare
        JPanel baraNavigare = BaraNavigare.creeazaBaraNavigare(this, utilizator);
        add(baraNavigare, BorderLayout.NORTH);

        // Secțiunea principală
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Secțiunea cu informații despre produs
        JPanel produsPanel = new JPanel(new BorderLayout());
        produsPanel.setBackground(Color.WHITE);

        // Imaginea produsului (partea stângă)
        JLabel produsImage = new JLabel();
        produsImage.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            String fullPath = "C:\\Users\\onica\\Downloads\\Online Store\\images\\" + produs.getImagePath();
            ImageIcon imageIcon = new ImageIcon(fullPath);
            Image scaledImage = imageIcon.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
            produsImage.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            produsImage.setText("Imagine indisponibilă");
        }
        produsPanel.add(produsImage, BorderLayout.WEST);

        // Detaliile produsului (partea dreaptă)
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel produsName = new JLabel(produs.getName(), JLabel.LEFT);
        produsName.setFont(new Font("Arial", Font.BOLD, 20));
        produsName.setForeground(Color.BLACK);

        JLabel produsDescription = new JLabel("<html>" + produs.getDescription() + "</html>", JLabel.LEFT);
        produsDescription.setFont(new Font("Arial", Font.PLAIN, 15));
        produsDescription.setForeground(Color.GRAY);
        
        JLabel produsPrice = new JLabel("Preț: " + produs.getPrice() + " RON", JLabel.LEFT);
        produsPrice.setFont(new Font("Arial", Font.BOLD, 15));
        produsPrice.setForeground(Color.BLACK);

        JButton addToCartButton = new JButton("Adaugă în coș");
        addToCartButton.setFont(new Font("Arial", Font.BOLD, 16));
        addToCartButton.setBackground(Color.RED);
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setFocusPainted(false);
        addToCartButton.addActionListener(e -> adaugaInCos(produs));

        detailsPanel.add(produsName);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(produsDescription);
        detailsPanel.add(Box.createVerticalStrut(20));
        detailsPanel.add(produsPrice);
        detailsPanel.add(addToCartButton);

        produsPanel.add(detailsPanel, BorderLayout.CENTER);

        mainPanel.add(produsPanel, BorderLayout.CENTER);

        // Secțiunea de sugestii
        JPanel sugestiiPanel = new JPanel(new BorderLayout());
        sugestiiPanel.setBackground(Color.WHITE);
        sugestiiPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel sugestiiLabel = new JLabel("Stilizează cu", JLabel.LEFT);
        sugestiiLabel.setFont(new Font("Arial", Font.BOLD, 18));
        sugestiiLabel.setForeground(Color.BLACK);
        sugestiiPanel.add(sugestiiLabel, BorderLayout.NORTH);

        JPanel sugestiiGrid = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 produse pe rând
        sugestiiGrid.setBackground(Color.WHITE);

        List<Produs> sugestii = manager.obtineSugestiiProduse(produs.getProductId());
        for (Produs sugestie : sugestii) {
            sugestiiGrid.add(createSugestieCard(sugestie));
        }

        JScrollPane scrollPane = new JScrollPane(sugestiiGrid);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sugestiiPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        add(sugestiiPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Creează un card pentru un produs din sugestii.
     *
     * @param produs Sugestia de produs.
     * @return JPanel-ul care reprezintă cardul sugestiei.
     */
    private JPanel createSugestieCard(Produs produs) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(5, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        // Imaginea produsului
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            String fullPath = "C:\\Users\\onica\\Downloads\\Online Store\\images\\" + produs.getImagePath();
            ImageIcon imageIcon = new ImageIcon(fullPath);
            Image scaledImage = imageIcon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            imageLabel.setText("Imagine indisponibilă");
        }

        JLabel nameLabel = new JLabel(produs.getName(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        nameLabel.setForeground(Color.BLACK);

        JLabel priceLabel = new JLabel("Preț: " + produs.getPrice() + " RON", JLabel.CENTER);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 12));
        priceLabel.setForeground(Color.RED);

        card.add(imageLabel, BorderLayout.NORTH);
        card.add(nameLabel, BorderLayout.CENTER);
        card.add(priceLabel, BorderLayout.SOUTH);

        // Adaugă mouse listener pentru a deschide pagina produsului sugestiv
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PaginaProdus(produs, manager, utilizator); // Deschide pagina produsului
            }
        });

        return card;
    }

    /**
     * Adaugă produsul în coșul utilizatorului.
     *
     * @param produs Produsul care trebuie adăugat.
     */
    private void adaugaInCos(Produs produs) {
        boolean success = manager.adaugaProdusInCos(utilizator.getUserId(), produs.getProductId(), 1);
        if (success) {
            JOptionPane.showMessageDialog(this, "Produsul \"" + produs.getName() + "\" a fost adăugat în coș!");
        } else {
            JOptionPane.showMessageDialog(this, "Eroare la adăugarea produsului în coș!");
        }
    }

}
