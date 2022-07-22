package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "payment_online")
public class PaymentOnline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentOnlineId;
    @JoinColumn(columnDefinition = "DATETIME")
    private String timeCreate;
    private String note;
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart;

    public PaymentOnline() {
    }

    public Integer getPaymentOnlineId() {
        return paymentOnlineId;
    }

    public void setPaymentOnlineId(Integer paymentOnlineId) {
        this.paymentOnlineId = paymentOnlineId;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
