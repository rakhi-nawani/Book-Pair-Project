package com.trilogyed.bookservive.service;

import com.trilogyed.bookservive.dao.BookServiceRepository;
import com.trilogyed.bookservive.dto.Book;
import com.trilogyed.bookservive.feign.NoteServiceClient;
import com.trilogyed.bookservive.viewModel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceLayer {

    private BookServiceRepository repo;
    private NoteServiceClient client;

    @Autowired
    public BookServiceLayer(BookServiceRepository repo, NoteServiceClient client){
        this.repo = repo;
        this.client = client;
    }


    public Book saveBook(Book book) {

       Book book1 = new Book();
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        repo.save(book1);
        return book1;
    }

    public void deleteBookby(int id) {
        repo.deleteById(id);
    }

    public void updateBook(BookViewModel bookViewModel) {
//        Book book = new Book();
//        book.setTitle(bookViewModel.getTitle());
//        book.setAuthor(bookViewModel.getAuthor());

    }

    public  BookViewModel getBookbyId(int id) {
//        BookViewModel bookViewModel = new BookViewModel();
//      Optional<Book>   book = repo.findById(id);
//      bookViewModel = buildBookViewModel(book);
//       return buildBookViewModel(book);
        BookViewModel outPutBook = null;
        Optional<Book> bookOptional = repo.findById(id);
        if (bookOptional.isPresent()){
            outPutBook = buildBookViewModel(bookOptional.get());
        }
        else{
            return outPutBook;
        }
        return outPutBook;
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
