package com.trilogyed.bookservive.controller;

import com.trilogyed.bookservive.dto.Book;
import com.trilogyed.bookservive.feign.NoteServiceClient;
import com.trilogyed.bookservive.messages.NoteListEntry;
import com.trilogyed.bookservive.service.BookServiceLayer;
import com.trilogyed.bookservive.viewModel.BookViewModel;
import com.trilogyed.noteservice.NotFoundException;
import com.trilogyed.noteservice.model.Note;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookServiceController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.#";

    @Autowired
    BookServiceLayer bookServiceLayer;

    @Autowired
    private final NoteServiceClient client;

    public BookServiceController(BookServiceLayer bookServiceLayer , NoteServiceClient client, RabbitTemplate rabbitTemplate ) {
        this.bookServiceLayer = bookServiceLayer;
        this.client = client;
        this.rabbitTemplate = rabbitTemplate;
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

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBook() {
        List<BookViewModel>  noteList = bookServiceLayer.getAllBooks();
        return noteList;
    }
    @RequestMapping(value = "/books/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBookByBookId( @PathVariable int book_id) {
        BookViewModel bookViewModel = bookServiceLayer.getBookbyId(book_id);
        if (bookViewModel == null)
            throw new NotFoundException("Book could not be retrieved for id " + book_id);
        return bookViewModel;
    }
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note) {
        return client.createNote(note);
    }

    @RequestMapping(value = "/notes/{note_id}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable int id) {
        client.deleteNote(id);
    }

    @RequestMapping(value = "/notes/{note_id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNote(@PathVariable("note_id") int note_id, @RequestBody @Valid Note note) {
        if (note.getNote_id() == 0)
            note.setNote_id(note_id);
        if (note_id != note.getNote_id()) {
            throw new IllegalArgumentException("Note ID on path must match the ID in the Note object");
        }
        client.updateNote(note_id, note);
    }
    @RequestMapping(value = "/notes/note_id/{note_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Note getNote(@PathVariable("note_id") int note_id) {
        Note note = client.getNote(note_id);
        if (note == null)
            throw new NotFoundException("Note could not be retrieved for id " + note_id);
        return note;
    }
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNote() {
        List<Note>  taskList = client.getAllNote();
        return taskList;
    }
    @RequestMapping(value = "/notes/book_id/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNoteByBookId(@PathVariable("book_id") int book_id) {
        List<Note> noteList = client.getNoteByBookId(book_id);
        if (noteList == null)
            throw new NotFoundException("Note could not be retrieved for id " + book_id);
        return noteList;
    }

    @RequestMapping(value = "/note/{note_id}", method = RequestMethod.PUT)
    public String updateNoteByQueue(@PathVariable("note_id") int note_id, @RequestBody @Valid Note note) {
        // create message to send to email list creation queue
        NoteListEntry msg = new NoteListEntry(note.getBook_id() + " " + note.getNote());
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
        System.out.println("Message Sent");

        // Now do account creation stuff...

        return "Note Updated";
    }



}
