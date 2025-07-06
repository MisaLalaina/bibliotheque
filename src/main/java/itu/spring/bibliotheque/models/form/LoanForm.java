package itu.spring.bibliotheque.models.form;

public class LoanForm {
    public String bookId;
    public String reservationId;
    public String adherentId;
    public String fromDate;
    public String toDate;
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getAdherentId() {
        return adherentId;
    }
    public void setAdherentId(String adherentId) {
        this.adherentId = adherentId;
    }
    public String getFromDate() {
        return fromDate;
    }
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    public String getToDate() {
        return toDate;
    }
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    public String getReservationId() {
        return reservationId;
    }
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
}
