package itu.spring.bibliotheque.models.form;

import java.sql.Date;

import itu.spring.bibliotheque.models.Book;

public class CopyForm {
    public Book book;
    public String copyNumber;
    public Date acquisitionDate;
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public String getCopyNumber() {
        return copyNumber;
    }
    public void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }
    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }
}
