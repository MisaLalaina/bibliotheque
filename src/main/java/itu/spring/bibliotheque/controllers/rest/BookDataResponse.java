package itu.spring.bibliotheque.controllers.rest;

import java.util.List;

import itu.spring.bibliotheque.models.BookCopy;

public class BookDataResponse {
    public List<BookCopy> data;

    public BookDataResponse(List<BookCopy> data) {
        this.data = data;
    }

    public List<BookCopy> getData() {
        return data;
    }

    public void setData(List<BookCopy> data) {
        this.data = data;
    }
}
