package itu.spring.bibliotheque.models;

import itu.spring.bibliotheque.enums.ReturnBookState;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "return_book")
public class ReturnBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @Column(name = "return_date")
    private Date returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @Enumerated(EnumType.STRING)
    private ReturnBookState state;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Loan getLoan() { return loan; }
    public void setLoan(Loan loan) { this.loan = loan; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
    public BookCopy getBookCopy() { return bookCopy; }
    public void setBookCopy(BookCopy bookCopy) { this.bookCopy = bookCopy; }
    public ReturnBookState getState() { return state; }
    public void setState(ReturnBookState state) { this.state = state; }
}
