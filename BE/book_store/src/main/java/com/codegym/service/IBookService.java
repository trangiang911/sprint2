package com.codegym.service;

import com.codegym.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAndSearch(String criterionVal, String valueSearchVal);

    Book findById(Integer id);
}
