package com.trilogyed.bookservive.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Objects;
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer book_id;
    String title;
    String author;

    public Book(){}

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public  Book(Integer book_id, String title, String author){
        this.book_id = book_id;
        this.title = title;
        this.author = author;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return book_id.equals(that.book_id) &&
                title.equals(that.title) &&
                author.equals(that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, title, author);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
