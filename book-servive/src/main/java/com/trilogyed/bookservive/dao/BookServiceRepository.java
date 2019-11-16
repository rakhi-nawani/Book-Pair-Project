package com.trilogyed.bookservive.dao;

import com.trilogyed.bookservive.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookServiceRepository extends JpaRepository<Book, Integer> {
    Book save(Book book);
}
