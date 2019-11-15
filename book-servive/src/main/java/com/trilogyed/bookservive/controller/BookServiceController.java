package com.trilogyed.bookservive.controller;

import com.trilogyed.bookservive.dto.Book;
import com.trilogyed.bookservive.service.BookServiceLayer;
import com.trilogyed.bookservice.NotFoundException;
import com.trilogyed.bookservice.service.BookServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookServiceController {
    @Autowired
    BookServiceLayer bookServiceLayer;

    public BookServiceController(BookServiceLayer bookServiceLayer ) { 
        this.bookServiceLayer = bookServiceLayer;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookServiceLayer.saveBook(book);
    }

    @RequestMapping(value = "/books/{book_id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable int id) {
        bookServiceLayer.deleteBookby(id);
    }

    @RequestMapping(value = "/books/{book_id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("book_id") int book_id, @RequestBody @Valid Book book) {
        if (book.getBook_id() == 0)
            book.setBook_id(book_id);
        if (book_id != book.getBook_id()) {
            throw new IllegalArgumentException("Book ID on path must match the ID in the Book object");
        }
        bookServiceLayer.updateBook(book);
    }
    @RequestMapping(value = "/books/book_id/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable("book_id") int book_id) {
        Book book = bookServiceLayer.getBookbyId(book_id);
        if (book == null)
            throw new NotFoundException("Book could not be retrieved for id " + book_id);
        return book;
    }
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBook() {
        List<Book>  taskList = bookServiceLayer.getAllBooks();
        return taskList;
    }
    @RequestMapping(value = "/books/book_id/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBookByBookId(@PathVariable("book_id") int book_id) {
        List<Book> bookList = bookServiceLayer.getBookbyBookId(book_id);
        if (bookList == null)
            throw new NotFoundException("Book could not be retrieved for id " + book_id);
        return bookList;
    }
}
