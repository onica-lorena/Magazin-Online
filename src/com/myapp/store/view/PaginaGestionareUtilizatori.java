package com.myapp.store.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Utilizator;

/**
 * PaginaGestionareUtilizatori reprezintă fereastra pentru afișarea și gestionarea utilizatorilor din baza de date.
 *
 * <p>
 * Această clasă permite administratorului să vizualizeze lista utilizatorilor, să le modifice rolurile și
 * să navigheze înapoi la pagina principală a aplicației.
 * </p>
 */
public class PaginaGestionareUtilizatori extends JFrame {

    /**
     * Constructor care configurează fereastra pentru gestionarea utilizatorilor.
     *
     * @param utilizator Utilizatorul curent autentificat (administrator).
     */
    public PaginaGestionareUtilizatori(Utilizator utilizator) {
        setTitle("Gestionare Utilizatori");
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
        List<Utilizator> utilizatori = manager.obtineUtilizatori();

        JPanel utilizatoriPanel = new JPanel(new GridLayout(0, 1));
        for (Utilizator utilizatorActual : utilizatori) {
            JPanel utilizatorPanel = new JPanel(new BorderLayout());
            JLabel utilizatorLabel = new JLabel(utilizatorActual.toString());
            JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"client", "admin"});

            roleComboBox.setSelectedItem(utilizatorActual.getRole());
            roleComboBox.addActionListener(e -> {
                manager.modificaRolUtilizator(utilizatorActual.getUserId(), (String) roleComboBox.getSelectedItem());
                JOptionPane.showMessageDialog(this, "Rol actualizat cu succes!");
            });

            utilizatorPanel.add(utilizatorLabel, BorderLayout.CENTER);
            utilizatorPanel.add(roleComboBox, BorderLayout.EAST);
            utilizatoriPanel.add(utilizatorPanel);
        }

        JScrollPane scrollPane = new JScrollPane(utilizatoriPanel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
