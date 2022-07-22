package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    @Column(columnDefinition = "BIT")
    private boolean cartStatus;
    @Column(columnDefinition = "DATE")
    private String dateCreate;
    @OneToMany(mappedBy = "cart")
    private List<PaymentOnline> paymentOnlineList;
    @OneToMany(mappedBy = "cart")
    private List<CartDetail> cartDetailList;

    @OneToOne(mappedBy = "cart")
    private Users users;

    public Cart() {
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public boolean isCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(boolean cartStatus) {
        this.cartStatus = cartStatus;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public List<PaymentOnline> getPaymentOnlineList() {
        return paymentOnlineList;
    }

    public void setPaymentOnlineList(List<PaymentOnline> paymentOnlineList) {
        this.paymentOnlineList = paymentOnlineList;
    }

    public List<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public void setCartDetailList(List<CartDetail> cartDetailList) {
        this.cartDetailList = cartDetailList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
