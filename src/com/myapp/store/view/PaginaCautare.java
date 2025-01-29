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
import com.myapp.store.service.CautareService;

/**
 * PaginaCautare reprezintă fereastra pentru afișarea rezultatelor unei căutări de produse.
 *
 * <p>
 * Această clasă afișează rezultatele căutării într-un format de tip grilă, permite navigarea
 * între paginile aplicației și accesarea detaliilor unui produs prin clic pe cardurile acestuia.
 * </p>
 */
public class PaginaCautare extends JFrame {

    private Utilizator utilizator;
    private ManagerBazaDeDate manager;

    /**
     * Constructor care configurează fereastra pentru căutarea produselor.
     *
     * @param keyword   Cuvântul cheie folosit pentru căutare.
     * @param utilizator Utilizatorul curent autentificat.
     */
    public PaginaCautare(String keyword, Utilizator utilizator) {
        this.utilizator = utilizator;
        this.manager = new ManagerBazaDeDate(); // Instanțiază managerul bazei de date

        setTitle("Rezultate căutare: " + keyword);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Bara de navigare
        JPanel baraNavigare = BaraNavigare.creeazaBaraNavigare(this, utilizator);
        add(baraNavigare, BorderLayout.NORTH);

        // Căutare produse
        CautareService cautareService = new CautareService();
        List<Produs> produseGasite = cautareService.cautaProduse(keyword);

        // Panou pentru rezultate
        JPanel rezultatePanel = new JPanel(new GridLayout(0, 4, 10, 10));
        rezultatePanel.setBackground(Color.WHITE);

        // Crează carduri pentru fiecare produs
        for (Produs produs : produseGasite) {
            rezultatePanel.add(createProdusCard(produs));
        }

        // Scroll pentru rezultate
        JScrollPane scrollPane = new JScrollPane(rezultatePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Creează un card pentru un produs.
     *
     * @param produs Obiectul produs.
     * @return JPanel-ul care reprezintă cardul produsului.
     */
    private JPanel createProdusCard(Produs produs) {
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
            Image scaledImage = imageIcon.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            imageLabel.setText("Imagine indisponibilă");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Numele produsului
        JLabel nameLabel = new JLabel(produs.getName(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Font mai mic
        nameLabel.setForeground(Color.BLACK);

        // Prețul produsului
        JLabel priceLabel = new JLabel("Preț: " + produs.getPrice() + " RON", JLabel.CENTER);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Font mai mic
        priceLabel.setForeground(Color.RED);

        // Panou pentru text
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 2, 2)); 
        textPanel.setBackground(Color.WHITE);
        textPanel.add(nameLabel);
        textPanel.add(priceLabel);

        // Adaugă componente în card
        card.add(imageLabel, BorderLayout.NORTH);
        card.add(textPanel, BorderLayout.CENTER);

        // Adaugă mouse listener pentru a deschide PaginaProdus
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PaginaProdus(produs, manager, utilizator); // Transmite managerul bazei de date
            }
        });

        return card;
    }
}
