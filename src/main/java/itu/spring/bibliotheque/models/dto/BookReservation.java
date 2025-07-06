package itu.spring.bibliotheque.models.dto;

import java.sql.Date;

public class BookReservation {
    private Integer bookId;
    private String bookTitle;
    private String bookAuthor;
    private Integer bookAgeMin;
    private String bookState;
    private Integer reservationId;
    private Integer adherentId;
    private String reservationState;
    private Date reservationDate;
    private Integer validatedBy;

    public BookReservation() {}
    public BookReservation(Integer bookId, String bookTitle, String bookAuthor, Integer bookAgeMin, String bookState, Integer reservationId, Integer adherentId, String reservationState, Date reservationDate, Integer validatedBy) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookAgeMin = bookAgeMin;
        this.bookState = bookState;
        this.reservationId = reservationId;
        this.adherentId = adherentId;
        this.reservationState = reservationState;
        this.reservationDate = reservationDate;
        this.validatedBy = validatedBy;
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
    public Integer getReservationId() { return reservationId; }
    public void setReservationId(Integer reservationId) { this.reservationId = reservationId; }
    public Integer getAdherentId() { return adherentId; }
    public void setAdherentId(Integer adherentId) { this.adherentId = adherentId; }
    public String getReservationState() { return reservationState; }
    public void setReservationState(String reservationState) { this.reservationState = reservationState; }
    public Date getReservationDate() { return reservationDate; }
    public void setReservationDate(Date reservationDate) { this.reservationDate = reservationDate; }
    public Integer getValidatedBy() { return validatedBy; }
    public void setValidatedBy(Integer validatedBy) { this.validatedBy = validatedBy; }
}
