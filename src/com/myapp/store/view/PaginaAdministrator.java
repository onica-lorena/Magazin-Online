package com.myapp.store.view;

import javax.swing.*;
import java.awt.*;
import com.myapp.store.model.Utilizator;
/**
 * Fereastră principală pentru administrator.
 *
 * <p>
 * Permite accesul la funcționalități administrative precum gestionarea produselor,
 * utilizatorilor și comenzilor. Include un mesaj de bun venit personalizat
 * și un buton pentru deconectare.
 * </p>
 */
public class PaginaAdministrator extends JFrame {

    /**
     * Constructor care configurează fereastra pentru administrator.
     *
     * @param utilizator Utilizatorul autentificat (administrator).
     */
    public PaginaAdministrator(Utilizator utilizator) {
        setTitle("Administrator - Panou de Control");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bun venit, " + utilizator.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT); 

        JButton logoutButton = new JButton("Deconectare");
        logoutButton.addActionListener(e -> {
            dispose(); 
            new PaginaAutentificare(); 
        });

        topPanel.add(welcomeLabel, BorderLayout.CENTER); 
        topPanel.add(logoutButton, BorderLayout.EAST);   
        add(topPanel, BorderLayout.NORTH);

        // Meniu principal
        JPanel meniu = new JPanel(new GridLayout(3, 1));
        JButton produseButton = new JButton("Gestionare Produse");
        JButton utilizatoriButton = new JButton("Gestionare Utilizatori");
        JButton comenziButton = new JButton("Gestionare Comenzi");

        meniu.add(produseButton);
        meniu.add(utilizatoriButton);
        meniu.add(comenziButton);

        produseButton.addActionListener(e -> new PaginaGestionareProduse(utilizator));
        utilizatoriButton.addActionListener(e -> new PaginaGestionareUtilizatori(utilizator));
        comenziButton.addActionListener(e -> new PaginaGestionareComenzi(utilizator));

        add(meniu, BorderLayout.CENTER);

        setVisible(true);
    }
}
