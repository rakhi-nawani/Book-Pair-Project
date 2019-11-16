package com.trilogyed.bookservive.messages;

public class NoteListEntry {
    Integer book_id;
    String note;

    public NoteListEntry(){}

    public  NoteListEntry(Integer book_id, String note){
        this.book_id = book_id;
        this.note = note;
    }

    public NoteListEntry(String note) {
    }


    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "NoteListEntry{" +
                "book_id=" + book_id +
                ", note='" + note + '\'' +
                '}';
    }
}
