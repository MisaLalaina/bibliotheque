package itu.spring.bibliotheque.controllers.rest;

import java.util.List;

public class BookDataResponse {
    public List<BookCopyDTO> data;

    public BookDataResponse(List<BookCopyDTO> data) {
        this.data = data;
    }

    public List<BookCopyDTO> getData() {
        return data;
    }

    public void setData(List<BookCopyDTO> data) {
        this.data = data;
    }
}
