package com.trilogyed.bookservive.feign;

import com.trilogyed.noteservice.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "note-service")
public interface NoteServiceClient {

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
     Note createNote(@RequestBody Note note);

    @RequestMapping(value = "/notes/{note_id}", method = RequestMethod.DELETE)
    void deleteNote(@PathVariable int id);

    @RequestMapping(value = "/notes/{note_id}", method = RequestMethod.PUT)
    public void updateNote(@PathVariable("note_id") int note_id, @RequestBody @Valid Note note);

    @RequestMapping(value = "/notes/note_id/{note_id}", method = RequestMethod.GET)
    public Note getNote(@PathVariable("note_id") int note_id);

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<Note> getAllNote();

    @RequestMapping(value = "/notes/book_id/{book_id}", method = RequestMethod.GET)
    public List<Note> getNoteByBookId(@PathVariable("book_id") int book_id);


}
