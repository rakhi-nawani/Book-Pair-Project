package com.trilogyed.noteservice.controller;


import com.trilogyed.noteservice.NotFoundException;
import com.trilogyed.noteservice.model.Note;
import com.trilogyed.noteservice.service.NoteServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteService {

    @Autowired
    NoteServiceLayer noteServiceLayer;

    public NoteService(NoteServiceLayer noteServiceLayer ) {
        this.noteServiceLayer = noteServiceLayer;

    }
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note) {
        return noteServiceLayer.saveNote(note);
    }

    @RequestMapping(value = "/notes/{note_id}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable int id) {
        noteServiceLayer.deleteNoteby(id);
    }

    @RequestMapping(value = "/notes/{note_id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNote(@PathVariable("note_id") int note_id, @RequestBody @Valid Note note) {
        if (note.getNote_id() == 0)
            note.setNote_id(note_id);
        if (note_id != note.getNote_id()) {
            throw new IllegalArgumentException("Note ID on path must match the ID in the Note object");
        }
        noteServiceLayer.updateNote(note);
    }
    @RequestMapping(value = "/notes/note_id/{note_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Note getNote(@PathVariable("note_id") int note_id) {
        Note note = noteServiceLayer.getNotebyId(note_id);
        if (note == null)
            throw new NotFoundException("Note could not be retrieved for id " + note_id);
        return note;
    }
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNote() {
        List<Note>  taskList = noteServiceLayer.getAllNotes();
        return taskList;
    }
    @RequestMapping(value = "/notes/book_id/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNoteByBookId(@PathVariable("book_id") int book_id) {
        List<Note> noteList = noteServiceLayer.getNotebyBookId(book_id);
        if (noteList == null)
            throw new NotFoundException("Note could not be retrieved for id " + book_id);
        return noteList;
    }
//    @RequestMapping(value="/ad", method = RequestMethod.GET)
//    public String getRandomAd() {
//        return  client.getRandomAd();
//    }


}
