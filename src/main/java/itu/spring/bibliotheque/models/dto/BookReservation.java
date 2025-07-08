package itu.spring.bibliotheque.models.dto;

import java.sql.Date;

public class BookReservation {
    private Integer copyId;
    private String copyNumber;
    private String copyState;
    private String bookTitle;
    private String bookAuthor;
    private Integer bookAgeMin;
    private String bookState;
    private Integer bookId;
    private Integer reservationId;
    private Integer adherentId;
    private String reservationState;
    private Date reservationDate;
    private Integer validatedBy;
    private Date acquisitionDate;
    private String copyCondition;


    public BookReservation(
        Integer copyId,
        String copyNumber,
        Date acquisitionDate,
        String copyCondition,
        String copyState,
        Integer bookId,
        String bookTitle,
        String bookAuthor,
        Integer bookAgeMin,
        String bookState,
        Integer reservationId,
        Integer adherentId,
        String reservationState,
        Date reservationDate,
        Integer validatedBy
    ) {
        this.copyId = copyId;
        this.copyNumber = copyNumber;
        this.acquisitionDate = acquisitionDate;
        this.copyCondition = copyCondition;
        this.copyState = copyState;
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


    public BookReservation() {}
    
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
    public Integer getCopyId() { return copyId; }
    public void setCopyId(Integer copyId) { this.copyId = copyId; }
    public String getCopyNumber() { return copyNumber; }
    public void setCopyNumber(String copyNumber) { this.copyNumber = copyNumber; }
    public String getCopyState() { return copyState; }
    public void setCopyState(String copyState) { this.copyState = copyState; }
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

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getCopyCondition() {
        return copyCondition;
    }

    public void setCopyCondition(String copyCondition) {
        this.copyCondition = copyCondition;
    }
}
