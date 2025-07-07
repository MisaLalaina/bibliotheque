package itu.spring.bibliotheque.controllers.rest;

import itu.spring.bibliotheque.models.BookCopy;

public class BookCopyDTO {
    private Integer id;
    private String state;
    private String bookTitle;
    
    public BookCopyDTO(BookCopy copy) {
        this.id = copy.getId();
        this.state = copy.getState();
        this.bookTitle = copy.getBook().getTitle();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}

