package itu.spring.bibliotheque.controllers.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import itu.spring.bibliotheque.models.BookCopy;

public class BookCopyDTO {
    @JsonIgnore
    private Integer id;
    @JsonProperty("exemplaire")
    private String copyNumber;
    private String state;
    @JsonIgnore
    private String bookTitle;
    
    public BookCopyDTO(BookCopy copy) {
        this.id = copy.getId();
        this.state = copy.getState();
        this.copyNumber = copy.getCopyNumber();
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

    public String getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }
}

