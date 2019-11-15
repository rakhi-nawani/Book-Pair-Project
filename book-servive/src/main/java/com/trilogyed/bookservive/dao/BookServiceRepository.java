package com.trilogyed.bookservive.dao;

import com.trilogyed.bookservive.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookServiceRepository extends JpaRepository<Book, Integer> {

}
