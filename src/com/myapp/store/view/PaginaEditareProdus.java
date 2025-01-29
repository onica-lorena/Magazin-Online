package com.myapp.store.view;

import java.awt.GridLayout;

import javax.swing.*;
import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Produs;
import com.myapp.store.model.Utilizator;

/**
 * PaginaEditareProdus reprezintă fereastra pentru editarea detaliilor unui produs existent.
 *
 * <p>
 * Această clasă permite utilizatorului autentificat să modifice informațiile
 * unui produs, inclusiv numele, descrierea, prețul, cantitatea, categoria și
 * calea imaginii. După salvarea modificărilor, produsul este actualizat în baza de date.
 * </p>
 */
public class PaginaEditareProdus extends JFrame {
	
	/**
     * Constructor care configurează fereastra pentru editarea unui produs.
     *
     * @param produs    Produsul care urmează să fie editat.
     * @param utilizator Utilizatorul curent autentificat.
     */
    public PaginaEditareProdus(Produs produs, Utilizator utilizator) {
        setTitle("Editează Produs");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        JTextField nameField = new JTextField(produs.getName());
        JTextField descriptionField = new JTextField(produs.getDescription());
        JTextField priceField = new JTextField(String.valueOf(produs.getPrice()));
        JTextField quantityField = new JTextField(String.valueOf(produs.getQuantity()));
        JTextField categoryField = new JTextField(produs.getCategory());
        JTextField imagePathField = new JTextField(produs.getImagePath());

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
            produs.setName(nameField.getText());
            produs.setDescription(descriptionField.getText());
            produs.setPrice(Double.parseDouble(priceField.getText()));
            produs.setQuantity(Integer.parseInt(quantityField.getText()));
            produs.setCategory(categoryField.getText());
            produs.setImagePath(imagePathField.getText());

            ManagerBazaDeDate manager = new ManagerBazaDeDate();
            boolean success = manager.actualizeazaProdus(produs); // Metodă nouă
            if (success) {
                JOptionPane.showMessageDialog(this, "Produs actualizat cu succes!");
                dispose();
                new PaginaGestionareProduse(utilizator);
            } else {
                JOptionPane.showMessageDialog(this, "Eroare la actualizarea produsului.");
            }
        });

        cancelButton.addActionListener(e -> dispose());

        add(saveButton);
        add(cancelButton);

        setVisible(true);
    }
}
