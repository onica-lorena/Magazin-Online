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
 * PaginaProduse afișează lista de produse, filtrată pe categorii.
 */
public class PaginaProduse extends JFrame {

    private JPanel produsePanel; // Panoul pentru afișarea produselor
    private ManagerBazaDeDate manager; // Managerul bazei de date
    private List<Produs> produse; // Lista de produse curent afișate
    private Utilizator utilizator;


    /**
     * Constructor care configurează fereastra pentru afișarea produselor.
     *
     * @param categorie Categoria de produse care trebuie afișată.
     * @param utilizator Utilizatorul autentificat.
     */
    public PaginaProduse(String categorie, Utilizator utilizator) {
        setTitle("Produse - " + categorie);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fereastră maximizată
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Managerul bazei de date
        manager = new ManagerBazaDeDate();
        produse = categorie.equals("Toate Produsele")
                ? manager.obtineToateProdusele()
                : manager.obtineProdusePeCategorie(categorie);

        // Bara de navigare
        JPanel baraNavigare = BaraNavigare.creeazaBaraNavigare(this, utilizator);
        add(baraNavigare, BorderLayout.NORTH);

        // Panou principal pentru sortare și produse
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Panou pentru sortare
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.setBackground(Color.WHITE);
        sortPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Spațiu între margini

        JLabel sortLabel = new JLabel("Sortează după: ");
        sortLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        sortLabel.setForeground(Color.BLACK);
        sortPanel.add(sortLabel);

        String[] sortOptions = {"Implicit", "Preț crescător", "Preț descrescător"};
        JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        sortComboBox.setBackground(Color.WHITE);
        sortComboBox.setForeground(Color.BLACK);
        sortComboBox.addActionListener(e -> {
            String selectedSort = (String) sortComboBox.getSelectedItem();
            if ("Preț crescător".equals(selectedSort)) {
                produse = manager.obtineProduseSortate(categorie, "ASC");
            } else if ("Preț descrescător".equals(selectedSort)) {
                produse = manager.obtineProduseSortate(categorie, "DESC");
            } else {
                produse = categorie.equals("Toate Produsele")
                        ? manager.obtineToateProdusele()
                        : manager.obtineProdusePeCategorie(categorie);
            }
            actualizeazaProduse(); // Reîncarcă produsele
        });

        sortPanel.add(sortComboBox);
        mainPanel.add(sortPanel, BorderLayout.NORTH);

        // Panou pentru afișarea produselor
        produsePanel = new JPanel(new GridLayout(0, 4, 10, 10)); // 4 produse pe rând
        produsePanel.setBackground(Color.WHITE);

        // Crează carduri pentru fiecare produs
        for (Produs produs : produse) {
            produsePanel.add(createProdusCard(produs, utilizator));
        }

        JScrollPane scrollPane = new JScrollPane(produsePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK;
                this.trackColor = Color.LIGHT_GRAY;
            }
        });

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Creează un card pentru un produs.
     *
     * @param produs Obiectul produs.
     * @return JPanel-ul care reprezintă cardul produsului.
     */
    private JPanel createProdusCard(Produs produs, Utilizator utilizator) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(5, 5)); // Spațiu redus între componente
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Bordură subțire, gri

        // Imaginea produsului
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            String fullPath = "C:\\Users\\onica\\Downloads\\Online Store\\images\\" + produs.getImagePath();
            ImageIcon imageIcon = new ImageIcon(fullPath);
            Image scaledImage = imageIcon.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH); // Dimensiuni ajustate
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

        // Butonul "Adaugă în coș"
        //JButton addToCartButton = new JButton("Adaugă în coș");
        //addToCartButton.setFont(new Font("Arial", Font.BOLD, 10)); 
        //addToCartButton.setBackground(Color.RED);
        //addToCartButton.setForeground(Color.WHITE);
        //addToCartButton.setFocusPainted(false); 
        //addToCartButton.addActionListener(e -> adaugaInCos(produs));

        // Adaugă componente în card
        card.add(imageLabel, BorderLayout.NORTH);
        card.add(textPanel, BorderLayout.CENTER);
        //card.add(addToCartButton, BorderLayout.SOUTH);
        
        // Adaugă mouse listener pentru a deschide PaginaProdus
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PaginaProdus(produs, manager, utilizator); // Deschide pagina produsului
            }
        });

        return card;
    }

    /**
     * Actualizează lista de produse afișate în panoul principal.
     */
    private void actualizeazaProduse() {
        produsePanel.removeAll(); // Golește panoul curent
        for (Produs produs : produse) {
            produsePanel.add(createProdusCard(produs, utilizator)); // Adaugă cardurile sortate
        }
        produsePanel.revalidate();
        produsePanel.repaint();
    }

    /**
     * Adaugă un produs în coșul utilizatorului.
     *
     * @param produs Produsul care trebuie adăugat în coș.
     */
    private void adaugaInCos(Produs produs) {
        JOptionPane.showMessageDialog(this, "Produsul \"" + produs.getName() + "\" a fost adăugat în coș!");
    }
}
