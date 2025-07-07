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
}

