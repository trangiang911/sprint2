package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String username;
    private String password;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    @Column(columnDefinition = "BIT")
    private boolean flag;
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<UserRole> userRoleList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart;


    public Users() {
    }

    public Users(String email, String encode) {
        this.username = email;
        this.password = encode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
