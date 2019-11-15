package com.trilogyed.bookservive.service;

import com.trilogyed.bookservive.dao.BookServiceRepository;
import com.trilogyed.bookservive.dto.Book;
import com.trilogyed.bookservive.feign.NoteServiceClient;
import com.trilogyed.bookservive.viewModel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BookServiceLayer {

    private BookServiceRepository repo;
    private final NoteServiceClient client;

    @Autowired
    public BookServiceLayer(BookServiceRepository repo, NoteServiceClient client){
        this.repo = repo;
        this.client = client;
    }


    public BookViewModel saveBook(BookViewModel bookViewModel) {

        Book book = new Book();
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        repo.save(book);
        return buildBookViewModel(book);
    }

    public void deleteBookby(int id) {
        repo.deleteById(id);
    }

    public void updateBook(BookViewModel bookViewModel) {
//        Book book = new Book();
//        book.setTitle(bookViewModel.getTitle());
//        book.setAuthor(bookViewModel.getAuthor());
        
    }

    public BookViewModel getBookbyId(int book_id) {
        BookViewModel bookViewModel = new BookViewModel();
        Book book = repo.getOne(bookViewModel.getBook_id());
        return buildBookViewModel(book);
    }

    public List<BookViewModel> getAllBooks() {
        List<Book> bookList = repo.findAll();
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        for(Book book : bookList){
        bookViewModelList.add(buildBookViewModel(book));
        }
        return bookViewModelList;
    }


    public BookViewModel buildBookViewModel(Book book){

        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setBook_id(book.getBook_id());
        bookViewModel.setNoteList(client.getNoteByBookId(bookViewModel.getBook_id()));
        return bookViewModel;
    }
}
