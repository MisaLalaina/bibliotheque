package itu.spring.bibliotheque.controllers.api;

import java.util.List;

public class BookLoanResponse {
    public List<BookLoanDTO> data;

    public BookLoanResponse(List<BookLoanDTO> data) {
        this.data = data;
    }

    public List<BookLoanDTO> getData() {
        return data;
    }

    public void setData(List<BookLoanDTO> data) {
        this.data = data;
    }
}
