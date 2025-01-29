package com.myapp.store.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSQLiteConnection {
    public static void main(String[] args) {
        // Calea către baza de date SQLite
    	String url = "jdbc:sqlite:C:/Users/onica/Downloads/Online Store/online_store.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Conexiune reușită la baza de date SQLite!");
            }
        } catch (SQLException e) {
            System.out.println("Eroare la conectarea la baza de date: " + e.getMessage());
        }
    }
}
