package com.trilogyed.noteservice.service;

import com.trilogyed.noteservice.dao.NoteServiceDao;
import com.trilogyed.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteServiceLayer {

    private NoteServiceDao noteServiceDao;

    @Autowired
    public NoteServiceLayer(NoteServiceDao noteServiceDao) {
        this.noteServiceDao = noteServiceDao;
    }

        public Note saveNote(Note note){
        note.setBook_id(note.getBook_id());
        note.setNote(note.getNote());
        note = noteServiceDao.createNote(note);
       return  note;
    }


    public void updateNote(Note note){

        note.setNote_id(note.getNote_id());
        note.setBook_id(note.getBook_id());
        note.setNote(note.getNote());
        noteServiceDao.updateNote(note);

        }
    public Note getNotebyId(int  note_id){
        Note note = noteServiceDao.getNote(note_id);
        if(note == null )
            return null;
        else
            return note;
    }

    public void deleteNoteby(int  note_id){
      noteServiceDao.deleteNote(note_id);
    }

    public List<Note> getNotebyBookId(int  book_id){
        List<Note> notesList = noteServiceDao.getNoteByBook(book_id);
        List<Note> listNoteiewModel = new ArrayList<>();
        for (Note notes: notesList) {
            listNoteiewModel.add(notes);
        }
        return listNoteiewModel;
    }

    public List<Note> getAllNotes() {
        List<Note> noteList = noteServiceDao.getAllNotes();
        List<Note> listNote = new ArrayList<>();
        for (Note nte: noteList) {
            listNote.add(nte);
        }
        return listNote;

    }


}

