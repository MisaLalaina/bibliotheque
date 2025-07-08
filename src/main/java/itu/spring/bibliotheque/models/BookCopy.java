package itu.spring.bibliotheque.models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "book_copy")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "copy_number", nullable = false)
    private String copyNumber;

    @Column(name = "acquisition_date")
    @Temporal(TemporalType.DATE)
    private Date acquisitionDate;

    @Column(length = 100, name = "copy_condition")
    private String copyCondition;

    @Column(length = 30)
    private String state;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public Date getAcquisitionDate() { return acquisitionDate; }
    public void setAcquisitionDate(Date acquisitionDate) { this.acquisitionDate = acquisitionDate; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getCopyCondition() {
        return copyCondition;
    }
    public void setCopyCondition(String copyCondition) {
        this.copyCondition = copyCondition;
    }
    public String getCopyNumber() {
        return copyNumber;
    }
    public void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }
}
