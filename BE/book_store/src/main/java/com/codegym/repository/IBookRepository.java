package com.codegym.repository;

import com.codegym.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "call findAndSearch(:criterionVal,:valueSearchVal)",
            nativeQuery= true)
    List<Book> findAndsearch(@Param("criterionVal") String criterionVal, @Param("valueSearchVal") String valueSearchVal);
}
