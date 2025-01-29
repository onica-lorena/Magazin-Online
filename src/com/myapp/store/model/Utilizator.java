package com.myapp.store.model;

/**
 * Reprezintă un utilizator al aplicației (client sau administrator).
 */
public class Utilizator {
    private int userId;
    private String username;
    private String password;
    private String role; 
    private String email;

    /**
     * Constructor pentru crearea unui utilizator.
     *
     * @param userId ID-ul unic al utilizatorului.
     * @param username Numele de utilizator.
     * @param password Parola utilizatorului.
     * @param role Rolul utilizatorului (client sau admin).
     * @param email Adresa de email a utilizatorului.
     */
    public Utilizator(int userId, String username, String password, String role, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    /** @return ID-ul utilizatorului. */
    public int getUserId() {
        return userId;
    }

    /** @param userId Setează ID-ul utilizatorului. */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** @return Numele de utilizator. */
    public String getUsername() {
        return username;
    }

    /** @param username Setează numele de utilizator. */
    public void setUsername(String username) {
        this.username = username;
    }

    /** @return Parola utilizatorului. */
    public String getPassword() {
        return password;
    }

    /** @param password Setează parola utilizatorului. */
    public void setPassword(String password) {
        this.password = password;
    }

    /** @return Rolul utilizatorului. */
    public String getRole() {
        return role;
    }

    /** @param role Setează rolul utilizatorului. */
    public void setRole(String role) {
        this.role = role;
    }

    /** @return Emailul utilizatorului. */
    public String getEmail() {
        return email;
    }

    /** @param email Setează emailul utilizatorului. */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
