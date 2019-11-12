package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;

import java.util.List;

public interface NoteServiceDao {

    public Note createNote(Note note);
    public Note getNote(int note_id);
    public List<Note> getAllNotes();
    public List<Note> getNoteByBook(int book_id);
    public void updateNote(Note note);
    public void deleteNote(int note_id);






}
