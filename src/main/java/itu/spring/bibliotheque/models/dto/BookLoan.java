package itu.spring.bibliotheque.models.dto;

import java.sql.Date;

public class BookLoan {
    private Integer bookId;
    private String bookTitle;
    private String bookAuthor;
    private Integer bookAgeMin;
    private String bookState;
    private Integer loanId;
    private Integer adherentId;
    private String loanState;
    private Date fromDate;
    private Date toDate;
    private Integer createdBy;

    public BookLoan() {}
    public BookLoan(Integer bookId, String bookTitle, String bookAuthor, Integer bookAgeMin, String bookState, Integer loanId, Integer adherentId, String loanState, Date fromDate, Date toDate, Integer createdBy) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookAgeMin = bookAgeMin;
        this.bookState = bookState;
        this.loanId = loanId;
        this.adherentId = adherentId;
        this.loanState = loanState;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdBy = createdBy;
    }
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public String getBookAuthor() { return bookAuthor; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
    public Integer getBookAgeMin() { return bookAgeMin; }
    public void setBookAgeMin(Integer bookAgeMin) { this.bookAgeMin = bookAgeMin; }
    public String getBookState() { return bookState; }
    public void setBookState(String bookState) { this.bookState = bookState; }
    public Integer getLoanId() { return loanId; }
    public void setLoanId(Integer loanId) { this.loanId = loanId; }
    public Integer getAdherentId() { return adherentId; }
    public void setAdherentId(Integer adherentId) { this.adherentId = adherentId; }
    public String getLoanState() { return loanState; }
    public void setLoanState(String loanState) { this.loanState = loanState; }
    public Date getFromDate() { return fromDate; }
    public void setFromDate(Date fromDate) { this.fromDate = fromDate; }
    public Date getToDate() { return toDate; }
    public void setToDate(Date toDate) { this.toDate = toDate; }
    public Integer getCreatedBy() { return createdBy; }
    public void setCreatedBy(Integer createdBy) { this.createdBy = createdBy; }
}
