package itu.spring.bibliotheque.controllers.api;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import itu.spring.bibliotheque.models.dto.BookLoan;

public class BookLoanDTO {
    @JsonProperty("book_title")
    public String bookTitle;
    @JsonProperty("exemplaire")
    public String bookCopyNumber;
    public int adherentId;
    public String adherent;
    @JsonProperty("from_date")
    public Date fromDate;
    @JsonProperty("to_date")
    public Date toDate;

    public String getBookTitle() {
        return bookTitle;
    }

    public BookLoanDTO (){}
    public BookLoanDTO (BookLoan bookLoan){
        setBookTitle(bookLoan.getBookTitle());
        setBookCopyNumber(bookLoan.getCopyNumber());
        setFromDate(bookLoan.getFromDate());
        setToDate(bookLoan.getToDate());
        setAdherentId(bookLoan.getAdherentId());
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getBookCopyNumber() {
        return bookCopyNumber;
    }
    public void setBookCopyNumber(String bookCopyNumber) {
        this.bookCopyNumber = bookCopyNumber;
    }
    public int getAdherentId() {
        return adherentId;
    }
    public void setAdherentId(int adherentId) {
        this.adherentId = adherentId;
    }
    public Date getFromDate() {
        return fromDate;
    }
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    public Date getToDate() {
        return toDate;
    }
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getAdherent() {
        return adherent;
    }

    public void setAdherent(String adherent) {
        this.adherent = adherent;
    }
}
