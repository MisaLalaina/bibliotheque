package itu.spring.bibliotheque.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "state")
    private String state;

    @Column(name = "reservation_date")
    @Temporal(TemporalType.DATE)
    private Date reservationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "validated_by")
    private Utilisateur validatedBy;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public Date getReservationDate() { return reservationDate; }
    public void setReservationDate(Date reservationDate) { this.reservationDate = reservationDate; }
    public Utilisateur getValidatedBy() { return validatedBy; }
    public void setValidatedBy(Utilisateur validatedBy) { this.validatedBy = validatedBy; }
}
