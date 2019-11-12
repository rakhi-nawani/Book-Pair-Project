package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteServiceDaoImplTemplateTest {


    @Autowired
    NoteServiceDao noteServiceDao;

    private Note note;

    @Before
    public void setUp() throws Exception {
        List<Note> notesList = noteServiceDao.getAllNotes();
        for (Note note : notesList) {
            noteServiceDao.deleteNote(note.getNote_id());
        }
    }

    @Test
    public void createGetAndDeleteNote() {
        Note note = new Note();
        note.setBook_id(123);
        note.setNote("This Book Belongs to Yuvaan");
         Note addedNote = noteServiceDao.createNote(note);
         Note noteIGot = noteServiceDao.getNote(addedNote.getNote_id());

         assertEquals(noteIGot, addedNote);
         noteServiceDao.deleteNote(addedNote.getNote_id());
         Note deletedNote = noteServiceDao.getNote(addedNote.getNote_id());
         assertEquals(null, deletedNote);
          }

    @Test
    public void getAllNotes() {
        Note note1 = new Note();
        note1.setBook_id(123);
        note1.setNote("This Book Belongs to Yuvaan");
        noteServiceDao.createNote(note1);

        Note note2 = new Note();
        note2.setBook_id(456);
        note2.setNote("This Book Belongs to Veer");
        noteServiceDao.createNote(note2);

        List<Note> noteList = noteServiceDao.getAllNotes();

        assertEquals(2, noteList.size());
    }

    @Test
    public void getNoteByBookID() {
        Note note = new Note();
        note.setBook_id(123);
        note.setNote("This Book Belongs to Yuvaan");
        Note addedNote = noteServiceDao.createNote(note);
        List<Note> listByBookId = noteServiceDao.getNoteByBook(123);
        assertEquals(1, listByBookId.size());
    }

    @Test
    public void updateNote() {
        Note note1 = new Note();
        note1.setBook_id(123);
        note1.setNote("This Book Belongs to Yuvaan");
        noteServiceDao.createNote(note1);
        note1.setBook_id(123);
        note1.setNote("This Book Belongs to Veer");
        noteServiceDao.updateNote(note1);
        Note updatedNote = noteServiceDao.getNote(note1.getNote_id());

        assertEquals(updatedNote, note1);
    }

}