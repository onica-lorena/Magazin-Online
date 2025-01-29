package com.myapp.store.view;

import java.awt.GridLayout;
import javax.swing.*;
import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Utilizator;
/**
 * Fereastră pentru adăugarea unui produs nou în baza de date.
 *
 * <p>
 * Această clasă permite administratorilor să introducă detalii despre un produs
 * (nume, descriere, preț, cantitate, categorie, cale imagine) și să salveze produsul
 * în baza de date prin intermediul clasei ManagerBazaDeDate.
 * </p>
 */
public class PaginaAdaugareProdus extends JFrame {

    /**
     * Constructor care configurează fereastra pentru adăugarea unui produs.
     *
     * @param utilizator Utilizatorul autentificat.
     */
    public PaginaAdaugareProdus(Utilizator utilizator) {
        setTitle("Adaugă Produs");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField imagePathField = new JTextField();

        JButton saveButton = new JButton("Salvează");
        JButton cancelButton = new JButton("Anulează");

        add(new JLabel("Nume:"));
        add(nameField);
        add(new JLabel("Descriere:"));
        add(descriptionField);
        add(new JLabel("Preț:"));
        add(priceField);
        add(new JLabel("Cantitate:"));
        add(quantityField);
        add(new JLabel("Categorie:"));
        add(categoryField);
        add(new JLabel("Calea imaginii:"));
        add(imagePathField);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String description = descriptionField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String category = categoryField.getText();
            String imagePath = imagePathField.getText();

            ManagerBazaDeDate manager = new ManagerBazaDeDate();
            boolean success = manager.adaugaProdus(name, description, price, quantity, category, imagePath);
            if (success) {
                JOptionPane.showMessageDialog(this, "Produs adăugat cu succes!");
                dispose();
                new PaginaGestionareProduse(utilizator);
            } else {
                JOptionPane.showMessageDialog(this, "Eroare la adăugarea produsului.");
            }
        });

        cancelButton.addActionListener(e -> dispose());

        add(saveButton);
        add(cancelButton);

        setVisible(true);
    }
}
