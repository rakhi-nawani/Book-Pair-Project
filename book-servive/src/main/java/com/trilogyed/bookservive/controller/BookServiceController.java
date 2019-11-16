package com.trilogyed.bookservive.controller;

import com.trilogyed.bookservive.dao.BookServiceRepository;
import com.trilogyed.bookservive.dto.Book;
import com.trilogyed.bookservive.feign.NoteServiceClient;
import com.trilogyed.bookservive.service.BookServiceLayer;
import com.trilogyed.bookservive.viewModel.BookViewModel;
import com.trilogyed.noteservice.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookServiceController {
    @Autowired
    BookServiceLayer bookServiceLayer;

    @Autowired
    private final NoteServiceClient client;



    public BookServiceController(BookServiceLayer bookServiceLayer , NoteServiceClient client ) {
        this.bookServiceLayer = bookServiceLayer;
        this.client = client;
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
    public void updateBook(@PathVariable("book_id") int book_id, @RequestBody @Valid BookViewModel book) {
        if (book.getBook_id() == 0)
            book.setBook_id(book_id);
        if (book_id != book.getBook_id()) {
            throw new IllegalArgumentException("Book ID on path must match the ID in the Book object");
        }
        bookServiceLayer.updateBook(book);
    }
//    @RequestMapping(value = "/books/book_id/{book_id}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public BookViewModel getBook(@PathVariable("book_id") int book_id) {
//        BookViewModel book = bookServiceLayer.getBookbyId(book_id);
//        if (book == null)
//            throw new NotFoundException("Book could not be retrieved for id " + book_id);
//        return book;
//    }
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBook() {
        List<BookViewModel>  taskList = bookServiceLayer.getAllBooks();
        return taskList;
    }
    @RequestMapping(value = "/books/book_id/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBookByBookId(@PathVariable("book_id") int book_id) {
        BookViewModel bookViewModel = bookServiceLayer.getBookbyId(book_id);
        if (bookViewModel == null)
            throw new NotFoundException("Book could not be retrieved for id " + book_id);
        return bookViewModel;
    }
}
