package com.myapp.store.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.myapp.store.controller.ManagerBazaDeDate;
import com.myapp.store.model.Utilizator;

/**
 * PaginaAutentificare este o interfață grafică (GUI) pentru autentificarea utilizatorilor.
 * <p>
 * Utilizatorii pot introduce adresa de email și parola pentru a se autentifica.
 * În funcție de rolul utilizatorului, aceștia sunt redirecționați fie către
 * pagina de administrator, fie către pagina de pornire pentru clienți.
 * În cazul în care autentificarea eșuează, un mesaj informativ este afișat.
 * </p>
 */
public class PaginaAutentificare extends JFrame {
    private JTextField emailField;  // Câmp pentru introducerea adresei de email
    private JPasswordField passwordField; // Câmp pentru introducerea parolei
    private JLabel messageLabel;  // Etichetă pentru afișarea mesajelor de eroare

    /**
     * Creează și configurează fereastra pentru autentificare.
     * <p>
     * Include câmpuri pentru introducerea email-ului și parolei, un buton
     * pentru autentificare și un link pentru redirecționare către pagina de înregistrare.
     * </p>
     */
    public PaginaAutentificare() {
        setTitle("Autentificare");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titlul
        JLabel titleLabel = new JLabel("ALREADY REGISTERED?", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Formular
        gbc.gridwidth = 1;

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Email address", JLabel.LEFT), gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Password", JLabel.LEFT), gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Mesaj informativ
        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);

        // Butonul de autentificare
        JButton loginButton = new JButton("LOG IN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> autentificare());
        gbc.gridy = 4;
        add(loginButton, gbc);

        // Mesaj pentru creare cont
        JLabel registerPrompt = new JLabel("Nu ai un cont? Creează unul acum!", JLabel.CENTER);
        registerPrompt.setForeground(Color.RED);
        registerPrompt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerPrompt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Închide fereastra curentă
                new PaginaInregistrare(); // Deschide pagina de înregistrare
            }
        });
        gbc.gridy = 5;
        add(registerPrompt, gbc);

        setVisible(true);
    }

    /**
     * Logica pentru autentificare.
     * <p>
     * Verifică combinația de email și parolă utilizând clasa ManagerBazaDeDate.
     * Dacă autentificarea reușește:
     * - Utilizatorii cu rolul "admin" sunt redirecționați către pagina de administrare.
     * - Utilizatorii cu rolul "client" sunt redirecționați către pagina principală.
     * Dacă autentificarea eșuează, se afișează un mesaj de eroare.
     * </p>
     */
    private void autentificare() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        ManagerBazaDeDate manager = new ManagerBazaDeDate();
        Utilizator utilizator = manager.autentificareUtilizator(email, password);

        if (utilizator != null) {
            if ("admin".equals(utilizator.getRole())) {
                new PaginaAdministrator(utilizator); // Navigare pentru administrator
            } else {
                new PaginaPornire(utilizator); // Navigare pentru client
            }
            dispose(); // Închide fereastra curentă
        } else {
            messageLabel.setText("Autentificare eșuată! Verifică email-ul și parola.");
        }
    }

    /**
     * Punctul de intrare al aplicației.
     * <p>
     * Deschide fereastra de autentificare.
     * </p>
     *
     * @param args Argumentele din linia de comandă.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaginaAutentificare::new);
    }
}
