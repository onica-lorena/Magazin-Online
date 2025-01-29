package com.myapp.store.model;

/**
 * Reprezintă un produs din catalogul magazinului online.
 */
public class Produs {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category; 
    private String imagePath;

    /**
     * Constructor pentru crearea unui produs.
     *
     * @param productId ID-ul unic al produsului.
     * @param name Numele produsului.
     * @param description Descrierea produsului.
     * @param price Prețul produsului.
     * @param quantity Cantitatea disponibilă.
     * @param category Categoria produsului.
     * @param imagePath Calea către imaginea asociată produsului.
     */
    public Produs(int productId, String name, String description, double price, int quantity, String category, String imagePath) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.imagePath = imagePath;
    }

    /** @return ID-ul produsului. */
    public int getProductId() {
        return productId;
    }

    /** @param productId Setează ID-ul produsului. */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /** @return Numele produsului. */
    public String getName() {
        return name;
    }

    /** @param name Setează numele produsului. */
    public void setName(String name) {
        this.name = name;
    }

    /** @return Descrierea produsului. */
    public String getDescription() {
        return description;
    }

    /** @param description Setează descrierea produsului. */
    public void setDescription(String description) {
        this.description = description;
    }

    /** @return Prețul produsului. */
    public double getPrice() {
        return price;
    }

    /** @param price Setează prețul produsului. */
    public void setPrice(double price) {
        this.price = price;
    }

    /** @return Cantitatea disponibilă a produsului. */
    public int getQuantity() {
        return quantity;
    }

    /** @param quantity Setează cantitatea produsului. */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /** @return Categoria produsului. */
    public String getCategory() {
        return category;
    }

    /** @param category Setează categoria produsului. */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * @return Calea către imaginea produsului.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath Setează calea către imaginea produsului.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
