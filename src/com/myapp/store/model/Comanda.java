package com.myapp.store.model;

import java.util.Date;
import java.util.List;

/**
 * Reprezintă o comandă plasată de un utilizator în magazinul online.
 */
public class Comanda {
    private int orderId;
    private int userId;
    private Date orderDate;
    private double totalAmount;
    private List<Produs> products;

    /**
     * Constructor pentru crearea unei comenzi.
     *
     * @param orderId ID-ul comenzii.
     * @param userId ID-ul utilizatorului care a plasat comanda.
     * @param orderDate Data plasării comenzii.
     * @param totalAmount Suma totală a comenzii.
     * @param products Lista produselor din comandă.
     */
    public Comanda(int orderId, int userId, Date orderDate, double totalAmount, List<Produs> products) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.products = products;
    }

    /** @return ID-ul comenzii. */
    public int getOrderId() {
        return orderId;
    }

    /** @param orderId Setează ID-ul comenzii. */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /** @return ID-ul utilizatorului. */
    public int getUserId() {
        return userId;
    }

    /** @param userId Setează ID-ul utilizatorului. */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** @return Data plasării comenzii. */
    public Date getOrderDate() {
        return orderDate;
    }

    /** @param orderDate Setează data plasării comenzii. */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /** @return Suma totală a comenzii. */
    public double getTotalAmount() {
        return totalAmount;
    }

    /** @param totalAmount Setează suma totală a comenzii. */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /** @return Lista produselor din comandă. */
    public List<Produs> getProducts() {
        return products;
    }

    /** @param products Setează lista produselor din comandă. */
    public void setProducts(List<Produs> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", products=" + products +
                '}';
    }
}
