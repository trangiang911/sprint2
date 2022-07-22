package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String bookName;
    private String bookImage;
    private String bookPrice;
    private String bookTranslator;
    private String bookPublishingCompany;
    private String bookNumberOfPages;
    private String bookSize;
    private String bookAuthor;
    private String bookDiscount;
    private String bookAmount;
    @Column(columnDefinition = "DATE")
    private String bookReleaseDate;
    private String bookDescription;

    @OneToMany(mappedBy = "book")
    private List<CartDetail> cartDetailList;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "categoryId")
    private Category category;

    public Book() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookTranslator() {
        return bookTranslator;
    }

    public void setBookTranslator(String bookTranslator) {
        this.bookTranslator = bookTranslator;
    }

    public String getBookPublishingCompany() {
        return bookPublishingCompany;
    }

    public void setBookPublishingCompany(String bookPublishingCompany) {
        this.bookPublishingCompany = bookPublishingCompany;
    }

    public String getBookNumberOfPages() {
        return bookNumberOfPages;
    }

    public void setBookNumberOfPages(String bookNumberOfPages) {
        this.bookNumberOfPages = bookNumberOfPages;
    }

    public String getBookSize() {
        return bookSize;
    }

    public void setBookSize(String bookSize) {
        this.bookSize = bookSize;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDiscount() {
        return bookDiscount;
    }

    public void setBookDiscount(String bookDiscount) {
        this.bookDiscount = bookDiscount;
    }

    public String getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(String bookAmount) {
        this.bookAmount = bookAmount;
    }

    public String getBookReleaseDate() {
        return bookReleaseDate;
    }

    public void setBookReleaseDate(String bookReleaseDate) {
        this.bookReleaseDate = bookReleaseDate;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public List<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public void setCartDetailList(List<CartDetail> cartDetailList) {
        this.cartDetailList = cartDetailList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
