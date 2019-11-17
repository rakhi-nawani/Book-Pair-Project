package com.trilogyed.bookservive.service;



import com.trilogyed.bookservive.dao.BookServiceRepository;
import com.trilogyed.bookservive.dto.Book;
import com.trilogyed.bookservive.feign.NoteServiceClient;
import com.trilogyed.noteservice.model.Note;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceLayerTest {

    private  BookServiceLayer bookServiceLayer;
    private BookServiceRepository repo;
    private NoteServiceClient client;
   @Before
    public void setUp() throws Exception {
       setUpFeignClientMock();

       bookServiceLayer = new BookServiceLayer(repo ,client );

    }

    private  void setUpFeignClientMock(){

       client = mock(NoteServiceClient.class);
       Note inputNote =  new Note();
       inputNote.setBook_id(1);
       inputNote.setNote("This para provides great info about cloud.");

       Note outputNote =  new Note();
       outputNote.setNote_id(10);
       outputNote.setBook_id(1);
       outputNote.setNote("This para provides great info about cloud.");

        List<Note> newList = new ArrayList<>();
        newList.add(outputNote);
        when(client.createNote(inputNote)).thenReturn(outputNote);
        when(client.getNote(10)).thenReturn(outputNote);
        when(client.getNoteByBookId(1)).thenReturn(newList);
        when(client.getAllNote()).thenReturn(newList);

    }

    public void shouldUpdateNote(){
       Note updatedNote = bookServiceLayer



    }



}