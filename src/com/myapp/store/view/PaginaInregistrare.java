package com.myapp.store.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.myapp.store.controller.ManagerBazaDeDate;

/**
 * PaginaInregistrare este o interfață grafică (GUI) pentru crearea unui cont nou.
 * <p>
 * Utilizatorii pot introduce numele, adresa de email și parola pentru a crea un cont.
 * În cazul în care parolele nu se potrivesc sau email-ul este deja utilizat,
 * aplicația afișează mesaje informative pentru utilizator.
 * </p>
 */
public class PaginaInregistrare extends JFrame {
    private JTextField emailField; // Câmp pentru introducerea adresei de email
    private JPasswordField passwordField; // Câmp pentru introducerea parolei
    private JPasswordField confirmPasswordField; // Câmp pentru confirmarea parolei
    private JTextField usernameField; // Câmp pentru introducerea numelui utilizatorului
    private JLabel messageLabel; // Etichetă pentru afișarea mesajelor de eroare

    /**
     * Creează și configurează fereastra pentru înregistrarea unui cont nou.
     * <p>
     * Include câmpuri pentru introducerea numelui, email-ului, parolei și confirmarea parolei.
     * Dispune de un buton pentru înregistrare și un link pentru navigarea către
     * pagina de autentificare.
     * </p>
     */
    public PaginaInregistrare() {
        setTitle("Înregistrare");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titlul
        JLabel titleLabel = new JLabel("SIGN UP", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Formular
        gbc.gridwidth = 1;

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Name", JLabel.LEFT), gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Email address", JLabel.LEFT), gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(new JLabel("Password", JLabel.LEFT), gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        add(new JLabel("Confirm Password", JLabel.LEFT), gbc);

        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(confirmPasswordField, gbc);

        // Mesaj informativ
        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);

        // Butonul de înregistrare
        JButton registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(Color.BLACK);
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(e -> inregistrareUtilizator());
        gbc.gridy = 6;
        add(registerButton, gbc);

        // Mesaj pentru autentificare
        JLabel loginPrompt = new JLabel("Ai deja un cont? Autentifică-te acum!", JLabel.CENTER);
        loginPrompt.setForeground(Color.RED);
        loginPrompt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginPrompt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Închide fereastra curentă
                new PaginaAutentificare(); // Navigare către pagina de autentificare
            }
        });
        gbc.gridy = 7;
        add(loginPrompt, gbc);

        setVisible(true);
    }

    /**
     * Logica pentru înregistrarea unui utilizator nou.
     * <p>
     * Verifică dacă toate câmpurile sunt completate, iar parola și confirmarea parolei coincid.
     * Dacă verificările sunt valide, încearcă să înregistreze utilizatorul în baza de date.
     * Afișează mesaje informative despre succesul sau eșecul operațiunii.
     * </p>
     */
    private void inregistrareUtilizator() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Parolele nu se potrivesc!");
            return;
        }

        ManagerBazaDeDate manager = new ManagerBazaDeDate();
        String rezultat = manager.inregistrareUtilizator(username, email, password);

        if ("success".equals(rezultat)) {
            JOptionPane.showMessageDialog(this, "Cont creat cu succes! Redirecționare către autentificare.");
            dispose(); // Închide fereastra curentă
            new PaginaAutentificare(); // Navigare către autentificare
        } else {
            messageLabel.setText(rezultat);
        }
    }

}
