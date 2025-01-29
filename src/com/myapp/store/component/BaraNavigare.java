package com.myapp.store.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.myapp.store.model.Utilizator;
import com.myapp.store.view.PaginaCos;
import com.myapp.store.view.PaginaPornire;
import com.myapp.store.view.PaginaCont;
import com.myapp.store.view.PaginaProduse;
import com.myapp.store.view.PaginaCautare;

/**
 * BaraNavigare este o componentă reutilizabilă pentru navigare.
 * <p>
 * Această bară include:
 * - Un logo pentru navigare către pagina principală.
 * - Link-uri către categorii specifice (Rochii, Pantofi, Genti).
 * - O bară de căutare pentru produse.
 * - Iconițe pentru contul utilizatorului și coșul de cumpărături.
 * </p>
 */
public class BaraNavigare {

    /**
     * Creează bara de navigare pentru aplicație.
     *
     * @param frame     Fereastra curentă.
     * @param utilizator Utilizatorul autentificat.
     * @return JPanel-ul reprezentând bara de navigare.
     */
    public static JPanel creeazaBaraNavigare(JFrame frame, Utilizator utilizator) {
    	
    	Color burgundy = new Color(155, 28, 49);


        // Configurarea principală a barei de navigare
        JPanel baraNavigare = new JPanel();
        baraNavigare.setLayout(new BoxLayout(baraNavigare, BoxLayout.X_AXIS)); 
        baraNavigare.setBackground(Color.WHITE);
        baraNavigare.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        baraNavigare.setPreferredSize(new Dimension(800, 30)); 

        // Secțiunea logo
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); 
        logoPanel.setBackground(Color.WHITE);
        JLabel logo = new JLabel("Store");
        logo.setFont(new Font("Harrington", Font.BOLD, 24));
        logo.setForeground(burgundy);
        logo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new PaginaPornire(utilizator); 
            }
        });
        logoPanel.add(logo);

     // Secțiunea categorii
        JPanel categoriiPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); 
        categoriiPanel.setBackground(Color.WHITE);
        String[] categorii = {"Rochii", "Pantofi", "Genti"};
        for (String categorie : categorii) {
            JLabel categorieLabel = new JLabel(categorie.toUpperCase());
            categorieLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            categorieLabel.setForeground(Color.BLACK);
            categorieLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            categorieLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                    new PaginaProduse(categorie, utilizator); 
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Subliniem cu roșu când mouse-ul este deasupra
                    categorieLabel.setText("<html><u style='color:burgundy;'>" + categorie.toUpperCase() + "</u></html>");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Revenim la stilul original când mouse-ul pleacă
                    categorieLabel.setText(categorie.toUpperCase());
                    categorieLabel.setForeground(Color.BLACK);
                }
            });

            categoriiPanel.add(categorieLabel);
        }



        // Secțiunea căutare și iconițe
        JPanel searchAndIconsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0)); 
        searchAndIconsPanel.setBackground(Color.WHITE);

        // Câmpul de căutare
        JTextField searchField = new JTextField(15);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton searchButton = new JButton("Caută");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 14));
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            if (!keyword.isEmpty()) {
                frame.dispose();
                new PaginaCautare(keyword, utilizator); 
            } else {
                JOptionPane.showMessageDialog(frame, "Introduceți un cuvânt cheie pentru căutare!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });
        searchAndIconsPanel.add(searchField);
        searchAndIconsPanel.add(searchButton);

        // Icon pentru cont
        ImageIcon userIcon = new ImageIcon("C:\\Users\\onica\\Downloads\\Online Store\\cont.jpg");
        Image scaledUserImage = userIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedUserIcon = new ImageIcon(scaledUserImage);
        JLabel iconCont = new JLabel(resizedUserIcon);
        iconCont.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iconCont.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new PaginaCont(utilizator); 
            }
        });
        searchAndIconsPanel.add(iconCont);

        // Icon pentru coș
        ImageIcon cartIcon = new ImageIcon("C:\\Users\\onica\\Downloads\\Online Store\\cart.jpg");
        Image scaledCartImage = cartIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedCartIcon = new ImageIcon(scaledCartImage);
        JLabel iconCos = new JLabel(resizedCartIcon);
        iconCos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iconCos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new PaginaCos(utilizator); 
            }
        });
        searchAndIconsPanel.add(iconCos);

        // Adăugare secțiuni în bara de navigare
        baraNavigare.add(logoPanel);
        baraNavigare.add(Box.createHorizontalGlue()); // Pentru a împinge categoriile la mijloc
        baraNavigare.add(categoriiPanel);
        baraNavigare.add(Box.createHorizontalGlue()); // Pentru a împinge iconițele la dreapta
        baraNavigare.add(searchAndIconsPanel);

        return baraNavigare;
    }
}
