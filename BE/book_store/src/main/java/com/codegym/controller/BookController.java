package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.Category;
import com.codegym.service.IBookService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping(value = "/api/book")
public class BookController {
    @Autowired
    IBookService iBookService;
    @Autowired
    ICategoryService iCategoryService;

    @GetMapping(value = "")
    public ResponseEntity<Page<Book>> getAllBookForeignLiterature(@RequestParam (defaultValue = "book_id") Optional<String> criterion,
                                                                  @RequestParam Optional<String> valueSearch,
                                                                  @RequestParam(defaultValue = "0") Integer page,
                                                                  @RequestParam(defaultValue = "8") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        String criterionVal = criterion.orElse("book_id");
        String valueSearchVal = valueSearch.orElse("%");

        Page books;
        System.out.println(criterionVal);
        System.out.println(valueSearchVal);
        List<Book> bookList = this.iBookService.findAndSearch(criterionVal,valueSearchVal);
        int start = Math.min((int)pageable.getOffset(), bookList.size());
        int end = Math.min((start + pageable.getPageSize()), bookList.size());
        if(bookList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        books = new PageImpl(bookList.subList(start,end),pageable,bookList.size());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categoryList = this.iCategoryService.findAll();
        if(categoryList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id){
        Book book = this.iBookService.findById(id);
        if(book == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
}
