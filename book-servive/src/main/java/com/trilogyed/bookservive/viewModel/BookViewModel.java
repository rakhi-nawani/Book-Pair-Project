package com.trilogyed.bookservive.viewModel;

import com.trilogyed.noteservice.model.Note;

import java.util.List;
import java.util.Objects;

public class BookViewModel {

    private Integer book_id;
    String title;
    String author;
    List<Note> noteList;

    public BookViewModel(){}

    public BookViewModel(String title, String author ,List<Note> noteList){
        this.title = title;
        this.author = author;
        this.noteList = noteList;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel that = (BookViewModel) o;
        return book_id.equals(that.book_id) &&
                title.equals(that.title) &&
                author.equals(that.author) &&
                noteList.equals(that.noteList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, title, author, noteList);
    }

    @Override
    public String toString() {
        return "BookViewModel{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", noteList=" + noteList +
                '}';
    }
}
