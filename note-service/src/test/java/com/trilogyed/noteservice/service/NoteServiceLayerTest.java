package com.trilogyed.noteservice.service;

import com.trilogyed.noteservice.dao.NoteServiceDao;
import com.trilogyed.noteservice.dao.NoteServiceDaoImplTemplate;
import com.trilogyed.noteservice.model.Note;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NoteServiceLayerTest {

    NoteServiceDao noteServiceDao;
    NoteServiceLayer noteServiceLayer;



    @Before
    public void setUp() throws Exception {
        setUpNoteServiceDaoMosk();

        noteServiceLayer = new NoteServiceLayer(noteServiceDao);
    }


    @Test
    public void saveandGetNote() {

        Note note = new Note();
        note.setBook_id(123);
        note.setNote("This Book belongs to Yuvaan");
        note = noteServiceLayer.saveNote(note);
        Note fromService = noteServiceLayer.getNotebyId(note.getNote_id());

        assertEquals(fromService,note);
    }


    @Test
    public void getNotebyBookId() {
        Note note = new Note();
        note.setBook_id(123);
        note.setNote("This Book belongs to Yuvaan");
        note = noteServiceLayer.saveNote(note);
       List<Note> fromService = noteServiceLayer.getNotebyBookId(note.getBook_id());

        assertEquals(1,fromService.size());
    }

    @Test
    public void getAllNotes() {
        Note note = new Note();
        note.setBook_id(123);
        note.setNote("This Book belongs to Yuvaan");
        note = noteServiceLayer.saveNote(note);
        List<Note> fromService = noteServiceLayer.getAllNotes();

        assertEquals(1,fromService.size());
     }


    public void setUpNoteServiceDaoMosk(){
        noteServiceDao = mock(NoteServiceDaoImplTemplate.class);

        Note resultNote = new Note();
        resultNote.setNote_id(1);
        resultNote.setBook_id(123);
        resultNote.setNote("This Book belongs to Yuvaan");

        Note inputNote = new Note();
        inputNote.setBook_id(123);
        inputNote.setNote("This Book belongs to Yuvaan");

        List<Note> newList = new ArrayList<>();
        newList.add(resultNote);

        when(noteServiceDao.getNote(1)).thenReturn(resultNote);
        when(noteServiceDao.createNote(inputNote)).thenReturn(resultNote);
        when(noteServiceDao.getAllNotes()).thenReturn(newList);
        when(noteServiceDao.getNoteByBook(123)).thenReturn(newList);

    }
}