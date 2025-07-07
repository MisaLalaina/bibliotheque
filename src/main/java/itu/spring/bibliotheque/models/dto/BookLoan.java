package itu.spring.bibliotheque.models.dto;

import java.sql.Date;


public class BookLoan {
    private Integer bookId;
    private Integer copyId;
    private String bookTitle;
    private String bookAuthor;
    private Integer bookAgeMin;
    private Integer copyNumber;
    private String copyState;
    private Integer loanId;
    private Integer adherentId;
    private String loanState;
    private Date fromDate;
    private Date toDate;
    private Integer createdBy;

    public BookLoan(Integer bookId, Integer copyId, String bookTitle, String bookAuthor, Integer bookAgeMin,
        String copyState, Integer copyNumber,
        Integer loanId, Integer adherentId, String loanState,
        Date fromDate, Date toDate, Integer createdBy){

        this.bookId = bookId;
        this.copyId = copyId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookAgeMin = bookAgeMin;
        this.copyNumber = copyNumber;
        this.copyState = copyState;
        this.loanId = loanId;
        this.adherentId = adherentId;
        this.loanState = loanState;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdBy = createdBy;
    }

    public BookLoan() {}
   
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public String getBookAuthor() { return bookAuthor; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
    public Integer getBookAgeMin() { return bookAgeMin; }
    public void setBookAgeMin(Integer bookAgeMin) { this.bookAgeMin = bookAgeMin; }
    public Integer getCopyId() { return copyId; }
    public void setCopyId(Integer copyId) { this.copyId = copyId; }
    public Integer getCopyNumber() { return copyNumber; }
    public void setCopyNumber(Integer copyNumber) { this.copyNumber = copyNumber; }
    public String getCopyState() { return copyState; }
    public void setCopyState(String copyState) { this.copyState = copyState; }
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
