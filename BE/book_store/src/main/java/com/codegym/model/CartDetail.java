package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "cart_detail")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartDetailId;
    private Integer cartDetailQuantity;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "bookId")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart;

    public CartDetail() {
    }

    public Integer getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(Integer cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public Integer getCartDetailQuantity() {
        return cartDetailQuantity;
    }

    public void setCartDetailQuantity(Integer cartDetailQuantity) {
        this.cartDetailQuantity = cartDetailQuantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
