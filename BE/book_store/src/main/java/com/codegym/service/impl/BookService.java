package com.codegym.service.impl;

import com.codegym.model.Book;
import com.codegym.repository.IBookRepository;
import com.codegym.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    IBookRepository iBookRepository;

    @Override
    public List<Book> findAndSearch(String criterionVal, String valueSearchVal) {
        System.out.println(criterionVal);
        System.out.println(valueSearchVal);
        return this.iBookRepository.findAndsearch(criterionVal,"%"+valueSearchVal+"%");
    }

    @Override
    public Book findById(Integer id) {
        return this.iBookRepository.findById(id).orElse(null);
    }
}
