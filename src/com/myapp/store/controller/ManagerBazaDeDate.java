package com.myapp.store.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myapp.store.model.Utilizator;
import com.myapp.store.model.Produs;

/**
 * ManagerBazaDeDate este responsabil pentru gestionarea interacțiunilor cu baza de date a aplicației.
 * <p>
 * Această clasă conține metode pentru autentificare, gestionarea produselor, utilizatorilor și comenzilor.
 * </p>
 */

public class ManagerBazaDeDate {
    private final String url = "jdbc:sqlite:C:/Users/onica/Downloads/Online Store/online_store.db";

    /**
     * Creează o conexiune la baza de date.
     *
     * @return Un obiect {@link Connection} către baza de date.
     * @throws SQLException Dacă apare o eroare la conectare.
     */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    // ------------------ Produse ------------------

    /**
     * Obține toate produsele din baza de date.
     *
     * @return O listă de produse ({@link Produs}).
     */
    public List<Produs> obtineToateProdusele() {
        String sql = "SELECT * FROM Products";
        List<Produs> produse = new ArrayList<>();

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produse.add(new Produs(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("category"),
                        rs.getString("image_path") 
                ));
            }
        } catch (SQLException e) {
            System.err.println("Eroare la obținerea produselor: " + e.getMessage());
        }

        return produse;
    }


    /**
     * Adaugă un produs nou în baza de date.
     *
     * @param name        Numele produsului.
     * @param description Descrierea produsului.
     * @param price       Prețul produsului.
     * @param quantity    Cantitatea disponibilă a produsului.
     * @param category    Categoria din care face parte produsul.
     * @param imagePath   Calea către imaginea produsului.
     * @return {@code true} dacă produsul a fost adăugat cu succes, altfel {@code false}.
     */
    public boolean adaugaProdus(String name, String description, double price, int quantity, String category, String imagePath) {
        String sql = "INSERT INTO Products (name, description, price, quantity, category, image_path) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.setString(5, category);
            stmt.setString(6, imagePath); 
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Eroare la adăugarea produsului: " + e.getMessage());
            return false;
        }
    }


    /**
     * Șterge un produs din baza de date.
     *
     * @param productId ID-ul produsului care trebuie șters.
     * @return {@code true} dacă produsul a fost șters cu succes, altfel {@code false}.
     */
    public boolean stergeProdus(int productId) {
        String sql = "DELETE FROM Products WHERE product_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Eroare la ștergerea produsului: " + e.getMessage());
            return false;
        }
    }

    // ------------------ Utilizatori ------------------

    /**
     * Obține lista completă a utilizatorilor din baza de date.
     *
     * @return O listă de utilizatori ({@link Utilizator}).
     */
    public List<Utilizator> obtineUtilizatori() {
        String sql = "SELECT * FROM Users";
        List<Utilizator> utilizatori = new ArrayList<>();

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                utilizatori.add(new Utilizator(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Eroare la obținerea utilizatorilor: " + e.getMessage());
        }

        return utilizatori;
    }

    /**
     * Modifică rolul unui utilizator existent în baza de date.
     *
     * @param userId ID-ul utilizatorului.
     * @param role   Noul rol al utilizatorului.
     * @return {@code true} dacă modificarea a fost realizată cu succes, altfel {@code false}.
     */
    public boolean modificaRolUtilizator(int userId, String role) {
        String sql = "UPDATE Users SET role = ? WHERE user_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, role);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Eroare la modificarea rolului utilizatorului: " + e.getMessage());
            return false;
        }
    }

    // ------------------ Comenzi ------------------

    /**
     * Obține toate comenzile existente în baza de date.
     *
     * @return O listă cu descrieri ale comenzilor.
     */
    public List<String> obtineComenzi() {
        String sql = "SELECT * FROM Orders";
        List<String> comenzi = new ArrayList<>();

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                comenzi.add("Comanda ID: " + rs.getInt("order_id") + ", Utilizator ID: " + rs.getInt("user_id") +
                        ", Data: " + rs.getString("order_date") + ", Suma Totală: " + rs.getDouble("total_amount"));
            }
        } catch (SQLException e) {
            System.err.println("Eroare la obținerea comenzilor: " + e.getMessage());
        }

        return comenzi;
    }

    // ------------------ Autentificare ------------------

    /**
     * Autentifică un utilizator pe baza email-ului și parolei introduse.
     *
     * @param email    Adresa de email a utilizatorului.
     * @param password Parola utilizatorului.
     * @return Obiectul {@link Utilizator} dacă autentificarea reușește, altfel {@code null}.
     */
    public Utilizator autentificareUtilizator(String email, String password) {

        // Validare intrări
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            // Verifică dacă email-ul sau parola sunt invalide (null sau goale)
            System.err.println("Eroare: Email sau parolă invalidă!");
            return null; // Returnează null pentru a indica eroarea
        }

        // Interogare SQL pentru selectarea utilizatorului pe baza email-ului și parolei
        String sql = "SELECT user_id, username, role FROM Users WHERE email = ? AND password = ?";
        try (Connection conn = connect(); // Creează o conexiune la baza de date
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Pregătește interogarea SQL

            // Înlocuiește parametrii din interogare cu valorile introduse
            stmt.setString(1, email.trim()); // Setează email-ul după eliminarea spațiilor albe
            stmt.setString(2, password.trim()); // Setează parola după eliminarea spațiilor albe

            // Execută interogarea și primește rezultatele
            ResultSet rs = stmt.executeQuery();

            // Verifică dacă a fost găsit un utilizator în baza de date
            if (rs.next()) {
                // Creează și returnează un obiect Utilizator cu datele găsite
                return new Utilizator(
                        rs.getInt("user_id"), // ID-ul utilizatorului
                        rs.getString("username"), // Numele utilizatorului
                        password, // Parola utilizatorului (necriptată, direct din intrare)
                        rs.getString("role"), // Rolul utilizatorului
                        email // Email-ul utilizatorului
                );
            } else {
                // Dacă utilizatorul nu este găsit, returnează null
                return null;
            }

        } catch (SQLException e) {
            // Prinde și gestionează orice eroare SQL
            System.err.println("Eroare la autentificare: " + e.getMessage());
            return null; // Returnează null în caz de eroare
        }
    }

    
    // -----------------Inregistrare---------------------------
    
    /**
     * Înregistrează un utilizator nou în baza de date.
     *
     * @param username Numele utilizatorului.
     * @param email    Adresa de email a utilizatorului.
     * @param password Parola utilizatorului.
     * @return Un mesaj descriptiv despre succesul sau eroarea înregistrării.
     */
    public String inregistrareUtilizator(String username, String email, String password) {
        // Validare: verifică dacă numele utilizatorului este nul sau gol
        if (username == null || username.trim().isEmpty()) {
            return "Numele utilizatorului este obligatoriu!";
        }

        // Validare: verifică dacă adresa de email este nulă sau goală
        if (email == null || email.trim().isEmpty()) {
            return "Adresa de email este obligatorie!";
        }

        // Validare: verifică dacă adresa de email este în format valid (conține '@' și '.')
        if (!email.contains("@") || !email.contains(".")) {
            return "Adresa de email este invalidă!";
        }

        // Validare: verifică dacă parola este nulă sau goală
        if (password == null || password.trim().isEmpty()) {
            return "Parola este obligatorie!";
        }

        // Validare: verifică dacă parola are cel puțin 8 caractere
        if (password.length() < 8) {
            return "Parola trebuie să aibă cel puțin 8 caractere!";
        }

        // Interogare SQL pentru introducerea utilizatorului în baza de date
        String sql = "INSERT INTO Users (username, email, password, role) VALUES (?, ?, ?, ?)";

        // Execută interogarea și gestionează posibilele erori
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Setează parametrii interogării cu valorile introduse
            stmt.setString(1, username.trim()); // Nume utilizator fără spații
            stmt.setString(2, email.trim());    // Email fără spații
            stmt.setString(3, password.trim()); // Parolă fără spații 
            stmt.setString(4, "client");        // Setează rolul implicit ca 'client'

            // Execută interogarea
            stmt.executeUpdate();

            // Returnează mesaj de succes dacă interogarea reușește
            return "success";

        } catch (SQLException e) {
            // Gestionare eroare: email duplicat
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                return "Adresa de email este deja folosită!";
            }

            // Gestionare eroare generică
            return "Eroare la înregistrare: " + e.getMessage();
        }
    }


    
    /**
     * Șterge utilizatorul pe baza email-ului.
     *
     * @param email Email-ul utilizatorului de șters.
     */
    public void stergeUtilizator(String email) {
        String sql = "DELETE FROM Users WHERE email = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Eroare la ștergerea utilizatorului: " + e.getMessage());
        }
    }

    
    /**
     * Obține lista produselor pe baza categoriei specificate.
     *
     * @param category Categoria dorită.
     * @return Lista de produse din categoria specificată.
     */
    public List<Produs> obtineProdusePeCategorie(String category) {
        // Definirea interogării SQL pentru selectarea produselor dintr-o categorie specifică
        String sql = "SELECT * FROM Products WHERE category = ?";

        // Lista care va conține produsele găsite
        List<Produs> produse = new ArrayList<>();

        // Conectarea la baza de date și executarea interogării
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Setarea parametrului interogării cu valoarea categoriei
            stmt.setString(1, category);

            // Executarea interogării și obținerea rezultatelor
            ResultSet rs = stmt.executeQuery();

            // Iterarea prin fiecare rând din rezultatele interogării
            while (rs.next()) {
                // Crearea unui obiect Produs din valorile rândului curent
                produse.add(new Produs(
                    rs.getInt("product_id"),       // ID-ul produsului
                    rs.getString("name"),          // Numele produsului
                    rs.getString("description"),   // Descrierea produsului
                    rs.getDouble("price"),         // Prețul produsului
                    rs.getInt("quantity"),         // Cantitatea disponibilă
                    rs.getString("category"),      // Categoria produsului
                    rs.getString("image_path")     // Calea imaginii produsului
                ));
            }
        } catch (SQLException e) {
            // Gestionarea erorilor SQL și afișarea unui mesaj în consolă
            System.err.println("Eroare la obținerea produselor pe categorie: " + e.getMessage());
        }

        // Returnarea listei de produse (poate fi goală dacă nu s-au găsit produse)
        return produse;
    }


    
    /**
     * Obține lista categoriilor unice din baza de date.
     *
     * @return Lista de categorii.
     */
    public List<String> obtineCategorii() {
        String sql = "SELECT DISTINCT category FROM Products";
        List<String> categorii = new ArrayList<>();

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                categorii.add(rs.getString("category"));
            }
        } catch (SQLException e) {
            System.err.println("Eroare la obținerea categoriilor: " + e.getMessage());
        }

        return categorii;
    }
    
    /**
     * Adaugă o comandă nouă în baza de date.
     *
     * @param userId      ID-ul utilizatorului care plasează comanda.
     * @param produse     Lista ID-urilor produselor din comandă.
     * @param totalAmount Suma totală a comenzii.
     * @return {@code true} dacă comanda a fost adăugată cu succes, altfel {@code false}.
     */
    public boolean adaugaComanda(int userId, List<Integer> produse, double totalAmount) {
        String sqlComanda = "INSERT INTO Orders (user_id, order_date, total_amount) VALUES (?, datetime('now'), ?)";
        String sqlProduseComanda = "INSERT INTO OrderProducts (order_id, product_id) VALUES (?, ?)";
        try (Connection conn = connect()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtComanda = conn.prepareStatement(sqlComanda, Statement.RETURN_GENERATED_KEYS)) {
                stmtComanda.setInt(1, userId);
                stmtComanda.setDouble(2, totalAmount);
                stmtComanda.executeUpdate();

                ResultSet generatedKeys = stmtComanda.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);

                    try (PreparedStatement stmtProduse = conn.prepareStatement(sqlProduseComanda)) {
                        for (int productId : produse) {
                            stmtProduse.setInt(1, orderId);
                            stmtProduse.setInt(2, productId);
                            stmtProduse.executeUpdate();
                        }
                    }
                }
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Eroare la adăugarea comenzii: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obține comenzile unui utilizator specific.
     *
     * @param userId ID-ul utilizatorului.
     * @return Lista comenzilor utilizatorului.
     */
    public List<String> obtineComenziUtilizator(int userId) {
        String sql = "SELECT * FROM Orders WHERE user_id = ?";
        List<String> comenzi = new ArrayList<>();

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comenzi.add("Comandă " +
                            " - Total: " + rs.getDouble("total_amount") +
                            " - Data: " + rs.getString("order_date"));
            }
        } catch (SQLException e) {
            System.err.println("Eroare la obținerea comenzilor utilizatorului: " + e.getMessage());
        }

        return comenzi;
    }

    /**
     * Obține toate comenzile din baza de date.
     *
     * @return Lista tuturor comenzilor.
     */
    public List<String> obtineToateComenzile() {
        String sql = "SELECT * FROM Orders";
        List<String> comenzi = new ArrayList<>();

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comenzi.add("Comandă #" + rs.getInt("order_id") +
                            " - Utilizator ID: " + rs.getInt("user_id") +
                            " - Total: " + rs.getDouble("total_amount") +
                            " - Data: " + rs.getString("order_date"));
            }
        } catch (SQLException e) {
            System.err.println("Eroare la obținerea tuturor comenzilor: " + e.getMessage());
        }

        return comenzi;
    }
    
    /**
     * Caută produse în baza de date pe baza unui cuvânt cheie.
     *
     * @param keyword Cuvântul cheie pentru căutare.
     * @return Lista produselor care conțin cuvântul cheie în nume sau descriere.
     */
    public List<Produs> cautaProduse(String keyword) {
        // Interogare SQL pentru căutarea produselor pe baza numelui sau descrierii
        String sql = "SELECT * FROM Products WHERE name LIKE ? OR description LIKE ?";

        // Lista care va conține toate produsele găsite
        List<Produs> produse = new ArrayList<>();

        try (
            // Conectare la baza de date
            Connection conn = connect();
            // Pregătire interogare SQL
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Formatarea cuvântului cheie pentru căutare cu wildcard-uri SQL
            String pattern = "%" + keyword + "%";

            // Setarea valorilor parametrilor pentru interogare
            stmt.setString(1, pattern); // Parametru pentru coloana 'name'
            stmt.setString(2, pattern); // Parametru pentru coloana 'description'

            // Executarea interogării și obținerea rezultatelor
            ResultSet rs = stmt.executeQuery();

            // Parcurgerea rezultatelor
            while (rs.next()) {
                // Crearea unui obiect Produs din valorile rândului curent
                produse.add(new Produs(
                    rs.getInt("product_id"),       // ID-ul produsului
                    rs.getString("name"),          // Numele produsului
                    rs.getString("description"),   // Descrierea produsului
                    rs.getDouble("price"),         // Prețul produsului
                    rs.getInt("quantity"),         // Cantitatea disponibilă
                    rs.getString("category"),      // Categoria produsului
                    rs.getString("image_path")     // Calea imaginii produsului
                ));
            }
        } catch (SQLException e) {
            // Gestionarea erorilor SQL
            System.err.println("Eroare la căutarea produselor: " + e.getMessage());
        }

        // Returnarea listei de produse
        return produse;
    }


    /**
     * Obține produsele din coșul unui utilizator specific.
     *
     * @param userId ID-ul utilizatorului.
     * @return Lista de produse din coș.
     */
    public List<Produs> obtineProduseDinCos(int userId) {
        // Interogare SQL pentru selectarea produselor din coș, inclusiv `image_path`
        String sql = "SELECT p.product_id, p.name, p.description, p.price, c.quantity, p.category, p.image_path " +
                     "FROM Cart c JOIN Products p ON c.product_id = p.product_id WHERE c.user_id = ?";
        // Lista care va conține produsele din coș
        List<Produs> produse = new ArrayList<>();
        
        try (
            // Conectare la baza de date
            Connection conn = connect();
            // Pregătire interogare SQL
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Setarea parametrului pentru `user_id`
            stmt.setInt(1, userId);
            
            // Executarea interogării și obținerea rezultatelor
            ResultSet rs = stmt.executeQuery();
            
            // Iterarea prin fiecare rând din rezultate
            while (rs.next()) {
                // Crearea unui obiect `Produs` din valorile rândului curent
                produse.add(new Produs(
                        rs.getInt("product_id"),       // ID-ul produsului
                        rs.getString("name"),          // Numele produsului
                        rs.getString("description"),   // Descrierea produsului
                        rs.getDouble("price"),         // Prețul produsului
                        rs.getInt("quantity"),         // Cantitatea din coș
                        rs.getString("category"),      // Categoria produsului
                        rs.getString("image_path")     // Calea imaginii produsului
                ));
            }
        } catch (SQLException e) {
            // Gestionarea erorilor SQL și afișarea unui mesaj în consolă
            System.err.println("Eroare la obținerea produselor din coș: " + e.getMessage());
        }
        
        // Returnarea listei de produse (poate fi goală dacă coșul este gol)
        return produse;
    }


    /**
     * Elimină un produs din coșul unui utilizator.
     *
     * @param userId    ID-ul utilizatorului.
     * @param productId ID-ul produsului care trebuie eliminat.
     */
    public void eliminaProdusDinCos(int userId, int productId) {
        String sql = "DELETE FROM Cart WHERE user_id = ? AND product_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Eroare la eliminarea produsului din coș: " + e.getMessage());
        }
    }

    /**
     * Finalizează comanda unui utilizator specific.
     *
     * @param userId ID-ul utilizatorului care finalizează comanda.
     * @return {@code true} dacă comanda a fost finalizată cu succes, altfel {@code false}.
     */
    public boolean finalizeazaComanda(int userId) {
        String sqlInsertOrder = "INSERT INTO Orders (user_id, order_date, total_amount) " +
                                "SELECT ?, datetime('now'), SUM(p.price * c.quantity) " +
                                "FROM Cart c JOIN Products p ON c.product_id = p.product_id WHERE c.user_id = ?";
        String sqlInsertOrderItems = "INSERT INTO OrderItems (order_id, product_id, quantity, price) " +
                                     "SELECT ?, c.product_id, c.quantity, p.price " +
                                     "FROM Cart c JOIN Products p ON c.product_id = p.product_id WHERE c.user_id = ?";
        String sqlUpdateProductQuantities = "UPDATE Products SET quantity = quantity - " +
                "(SELECT quantity FROM Cart WHERE product_id = Products.product_id AND user_id = ?) " +
                "WHERE product_id IN (SELECT product_id FROM Cart WHERE user_id = ?)";
        String sqlClearCart = "DELETE FROM Cart WHERE user_id = ?";
        try (Connection conn = connect()) {
            conn.setAutoCommit(false);

            // Insert into Orders
            int orderId;
            try (PreparedStatement stmtOrder = conn.prepareStatement(sqlInsertOrder, Statement.RETURN_GENERATED_KEYS)) {
                stmtOrder.setInt(1, userId);
                stmtOrder.setInt(2, userId);
                stmtOrder.executeUpdate();

                ResultSet rs = stmtOrder.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                } else {
                    conn.rollback();
                    return false;
                }
            }

            // Insert into OrderItems
            try (PreparedStatement stmtOrderItems = conn.prepareStatement(sqlInsertOrderItems)) {
                stmtOrderItems.setInt(1, orderId);
                stmtOrderItems.executeUpdate();
            }

            // Update product quantities
            try (PreparedStatement stmtUpdateQuantities = conn.prepareStatement(sqlUpdateProductQuantities)) {
                stmtUpdateQuantities.setInt(1, userId);
                stmtUpdateQuantities.setInt(2, userId);
                stmtUpdateQuantities.executeUpdate();
            }
            
            // Clear Cart
            try (PreparedStatement stmtClearCart = conn.prepareStatement(sqlClearCart)) {
                stmtClearCart.setInt(1, userId);
                stmtClearCart.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Eroare la finalizarea comenzii: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obține o listă de produse sortate în funcție de preț.
     *
     * @param categorie Categoria produselor. "Toate Produsele" include toate categoriile.
     * @param sortOrder Ordinea de sortare: "ASC" pentru ascendent, "DESC" pentru descendent.
     * @return O listă de produse sortate conform criteriilor date.
     */
    public List<Produs> obtineProduseSortate(String categorie, String sortOrder) {
        String sql;
        if (categorie.equals("Toate Produsele")) {
            sql = "SELECT * FROM Products ORDER BY price " + sortOrder;
        } else {
            sql = "SELECT * FROM Products WHERE category = ? ORDER BY price " + sortOrder;
        }

        List<Produs> produse = new ArrayList<>();
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (!categorie.equals("Toate Produsele")) {
                stmt.setString(1, categorie);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produse.add(new Produs(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("category"),
                        rs.getString("image_path")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Eroare la sortarea produselor: " + e.getMessage());
        }
        return produse;
    }

    /**
     * Obține o listă de produse sugerate pentru un produs specific.
     *
     * @param productId ID-ul produsului pentru care se caută sugestii.
     * @return O listă de produse sugerate asociate produsului dat.
     */
    public List<Produs> obtineSugestiiProduse(int productId) {
        String sql = "SELECT p.* FROM ProductSuggestions ps " +
                     "JOIN Products p ON ps.suggested_product_id = p.product_id " +
                     "WHERE ps.product_id = ?";
        List<Produs> sugestii = new ArrayList<>();
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sugestii.add(new Produs(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getString("category"),
                    rs.getString("image_path")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Eroare la obținerea sugestiilor: " + e.getMessage());
        }
        return sugestii;
    }

    /**
     * Adaugă un produs în coș pentru un utilizator.
     *
     * @param userId ID-ul utilizatorului.
     * @param productId ID-ul produsului.
     * @param quantity Cantitatea produsului.
     */
    public boolean adaugaProdusInCos(int userId, int productId, int quantity) {
        String sql = "INSERT INTO Cart (user_id, product_id, quantity) " +
                     "VALUES (?, ?, ?) " +
                     "ON CONFLICT(user_id, product_id) DO UPDATE SET quantity = quantity + ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setInt(4, quantity); // Pentru actualizare
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Eroare la adăugarea produsului în coș: " + e.getMessage());
            return false;
        }
    }


    
    /**
     * Obține detaliile unei comenzi.
     *
     * @param orderId ID-ul comenzii.
     * @return Lista produselor din comandă.
     */
    public List<Produs> obtineDetaliiComanda(int orderId) {
        String sql = "SELECT oi.product_id, p.name, p.description, oi.price, oi.quantity, p.image_path " +
                     "FROM OrderItems oi " +
                     "JOIN Products p ON oi.product_id = p.product_id " +
                     "WHERE oi.order_id = ?";
        List<Produs> produse = new ArrayList<>();

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                produse.add(new Produs(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        null,
                        rs.getString("image_path")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Eroare la obținerea detaliilor comenzii: " + e.getMessage());
        }

        return produse;
    }

    
    /**
     * Calculează suma totală a produselor din coș.
     *
     * @param userId ID-ul utilizatorului.
     * @return Suma totală.
     */
    public double calculeazaSumaTotalaCos(int userId) {
        String sql = "SELECT SUM(p.price * c.quantity) AS total " +
                     "FROM Cart c JOIN Products p ON c.product_id = p.product_id " +
                     "WHERE c.user_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Eroare la calcularea sumei totale a coșului: " + e.getMessage());
        }

        return 0.0;
    }
    
    /**
     * Actualizează cantitatea unui produs în coșul utilizatorului.
     *
     * @param userId      ID-ul utilizatorului.
     * @param productId   ID-ul produsului.
     * @param newQuantity Noua cantitate pentru produs.
     * @return {@code true} dacă actualizarea a fost realizată cu succes, altfel {@code false}.
     */
    public boolean actualizeazaCantitateInCos(int userId, int productId, int newQuantity) {
        String sql = "UPDATE Cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, userId);
            stmt.setInt(3, productId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Returnează true dacă s-au actualizat rânduri
        } catch (SQLException e) {
            System.err.println("Eroare la actualizarea cantității în coș: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizeazaProdus(Produs produs) {
        String sql = "UPDATE Products SET name = ?, description = ?, price = ?, quantity = ?, category = ?, image_path = ? WHERE product_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produs.getName());
            stmt.setString(2, produs.getDescription());
            stmt.setDouble(3, produs.getPrice());
            stmt.setInt(4, produs.getQuantity());
            stmt.setString(5, produs.getCategory());
            stmt.setString(6, produs.getImagePath());
            stmt.setInt(7, produs.getProductId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Eroare la actualizarea produsului: " + e.getMessage());
            return false;
        }
    }



    
}
