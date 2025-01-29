package com.myapp.store.view;

import javax.swing.*;

import java.awt.*;
import com.myapp.store.component.BaraNavigare;
import com.myapp.store.model.Utilizator;

/**
 * PaginaPornire reprezintă pagina principală a aplicației.
 * <p>
 * Aceasta oferă utilizatorului o interfață pentru a vizualiza conținutul aplicației,
 * incluzând imagini, texte, o bară de navigare și un footer cu informații de contact.
 * </p>
 */
public class PaginaPornire extends JFrame {

    /**
     * Constructor care configurează fereastra principală.
     * <p>
     * Creează structura de bază a paginii principale, incluzând bara de navigare,
     * secțiunile de imagini și footer-ul. Pagina este personalizată pe baza informațiilor
     * despre utilizatorul autentificat.
     * </p>
     *
     * @param utilizator Obiectul utilizator care conține detalii despre utilizatorul autentificat.
     */
    public PaginaPornire(Utilizator utilizator) {
        setTitle("Pagina de Pornire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Setează dimensiunea maximă a ferestrei la lansare
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Adaugă bara de navigare fixată
        JPanel baraNavigare = BaraNavigare.creeazaBaraNavigare(this, utilizator);
        add(baraNavigare, BorderLayout.NORTH);

        // Panou principal derulabil
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(Color.WHITE);

        // Secțiuni de imagini cu căi diferite
        mainContent.add(createImageSectionWithTextAbove(
            "C:\\Users\\onica\\Downloads\\Online Store\\portret1.jpeg",
            "C:\\Users\\onica\\Downloads\\Online Store\\landscape1.jpeg",
            "C:\\Users\\onica\\Downloads\\Online Store\\landscape2.jpeg",
            true,
            "Rochie aurie glamour cu detalii din dantelă",
            "Colecția exclusivă",
            "Geantă sofisticată cu design clasic",
            "Accesorii Launer",
            "Pantofi roșii stilizați pentru o seară glamour",
            "Louboutin"
        ));
        mainContent.add(createImageSectionWithTextAbove(
            "C:\\Users\\onica\\Downloads\\Online Store\\portret2.jpeg",
            "C:\\Users\\onica\\Downloads\\Online Store\\landscape3.jpeg",
            "C:\\Users\\onica\\Downloads\\Online Store\\landscape4.jpeg",
            false,
            "Rochie elegantă burgundy pentru un look atemporal",
            "Alege Rochia Midi Aje Isabella",
            "Pantofi eleganți pentru ocazii speciale",
            "Prada",
            "Geantă vișinie chic pentru eleganța zilnică",
            "Lalage Beaumount"
        ));

        // Footer
        JPanel contactPanel = createContactSection();
        mainContent.add(contactPanel);

        // Scroll pane pentru conținut
        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Personalizarea barei de derulare
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK; // Bara de derulare
                this.trackColor = Color.LIGHT_GRAY; // Fundalul barei de derulare
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createCustomArrowButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createCustomArrowButton();
            }

            private JButton createCustomArrowButton() {
                JButton button = new JButton();
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setBackground(Color.BLACK);
                button.setForeground(Color.BLACK);
                return button;
            }
        });

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Creează o secțiune de imagini cu text deasupra fiecărei imagini.
     * <p>
     * Aceasta generează un JPanel ce conține o imagine portret și două imagini landscape,
     * fiecare cu texte deasupra. Imaginile pot fi poziționate în funcție de parametrii specificați.
     * </p>
     *
     * @param portraitPath   Calea către imaginea portret.
     * @param landscape1Path Calea către prima imagine landscape.
     * @param landscape2Path Calea către a doua imagine landscape.
     * @param portraitOnLeft Dacă imaginea portret este plasată pe stânga sau dreapta.
     * @param portraitTitle  Textul deasupra imaginii portret.
     * @param portraitSubtitle Subtitlul deasupra imaginii portret.
     * @param landscape1Title Textul deasupra primei imagini landscape.
     * @param landscape1Subtitle Subtitlul deasupra primei imagini landscape.
     * @param landscape2Title Textul deasupra celei de-a doua imagini landscape.
     * @param landscape2Subtitle Subtitlul deasupra celei de-a doua imagini landscape.
     * @return JPanel-ul reprezentând secțiunea de imagini cu text.
     */
    private JPanel createImageSectionWithTextAbove(String portraitPath, String landscape1Path, String landscape2Path, boolean portraitOnLeft,
                                                   String portraitTitle, String portraitSubtitle,
                                                   String landscape1Title, String landscape1Subtitle,
                                                   String landscape2Title, String landscape2Subtitle) {
        JPanel section = new JPanel(new GridBagLayout());
        section.setBackground(Color.WHITE);
        section.setPreferredSize(new Dimension(1100, 700));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Imaginea portret cu text deasupra
        JPanel portraitPanel = createImageWithTextPanel(portraitPath, 400, 600, portraitTitle, portraitSubtitle);
        // Imaginile landscape cu text deasupra
        JPanel landscapePanel = new JPanel(new GridLayout(2, 1, 5, 5));
        landscapePanel.setBackground(Color.WHITE);
        landscapePanel.setPreferredSize(new Dimension(600, 600));
        landscapePanel.add(createImageWithTextPanel(landscape1Path, 600, 300, landscape1Title, landscape1Subtitle));
        landscapePanel.add(createImageWithTextPanel(landscape2Path, 600, 300, landscape2Title, landscape2Subtitle));

        if (portraitOnLeft) {
            gbc.gridx = 0;
            section.add(portraitPanel, gbc);
            gbc.gridx = 1;
            section.add(landscapePanel, gbc);
        } else {
            gbc.gridx = 0;
            section.add(landscapePanel, gbc);
            gbc.gridx = 1;
            section.add(portraitPanel, gbc);
        }

        return section;
    }

    /**
     * Creează un JPanel care conține o imagine și text deasupra acesteia.
     *
     * @param imagePath Calea către imagine.
     * @param width     Lățimea imaginii.
     * @param height    Înălțimea imaginii.
     * @param title     Textul deasupra imaginii.
     * @param subtitle  Subtitlul deasupra imaginii.
     * @return JPanel-ul cu imaginea și textul.
     */
    private JPanel createImageWithTextPanel(String imagePath, int width, int height, String title, String subtitle) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Textul deasupra imaginii
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrare pe orizontală

        JLabel subtitleLabel = new JLabel(subtitle, JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrare pe orizontală

        // Adaugă etichetele în panel-ul de text
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);
        textPanel.add(Box.createVerticalGlue());

        // Imaginea
        JLabel imageLabel = createImageLabel(imagePath, width, height);

        // Adaugă textul deasupra imaginii
        panel.add(textPanel, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creează un JLabel cu imaginea specificată.
     *
     * @param imagePath Calea către imagine.
     * @param width     Lățimea imaginii.
     * @param height    Înălțimea imaginii.
     * @return JLabel-ul care conține imaginea redimensionată.
     */
    private JLabel createImageLabel(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaledImage));
        label.setPreferredSize(new Dimension(width, height));
        return label;
    }

    /**
     * Creează secțiunea de contact din partea de jos a paginii.
     *
     * @return JPanel-ul pentru secțiunea de contact.
     */
    private JPanel createContactSection() {
        JPanel contactPanel = new JPanel();
        contactPanel.setBackground(Color.BLACK);
        contactPanel.setPreferredSize(new Dimension(1100, 100));
        contactPanel.setLayout(new GridLayout(2, 1));

        JLabel contactLabel = new JLabel("Contactați-ne la: contact@store.com", JLabel.CENTER);
        contactLabel.setForeground(Color.WHITE);
        contactLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contactPanel.add(contactLabel);

        JLabel phoneLabel = new JLabel("Telefon: +40 123 456 789", JLabel.CENTER);
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contactPanel.add(phoneLabel);

        return contactPanel;
    }

    /**
     * Punctul de intrare pentru aplicație.
     * <p>
     * Lansează aplicația prin afișarea paginii de autentificare.
     * </p>
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaginaAutentificare());
    }

}
