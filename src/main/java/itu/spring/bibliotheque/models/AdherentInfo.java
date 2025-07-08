package itu.spring.bibliotheque.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "adherent_info")
public class AdherentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "available_pret")
    private Integer availablePret;
    @Column(name = "available_duration")
    private Integer availableDuration;
    @Column(name = "available_reservation")
    private Integer availableReservation;
    @Column(name = "available_extension")
    private Integer availableExtension;

    @ManyToOne
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAvailablePret() {
        return availablePret;
    }
    public void setAvailablePret(Integer availableQuote) {
        this.availablePret = availableQuote;
    }
    public Integer getAvailableDuration() {
        return availableDuration;
    }
    public void setAvailableDuration(Integer availableDuration) {
        this.availableDuration = availableDuration;
    }
    public Adherent getAdherent() {
        return adherent;
    }
    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
    public Integer getAvailableExtension() {
        return availableExtension;
    }
    public void setAvailableExtension(Integer availableExtension) {
        this.availableExtension = availableExtension;
    }
    public Integer getAvailableReservation() {
        return availableReservation;
    }
    public void setAvailableReservation(Integer availableReservation) {
        this.availableReservation = availableReservation;
    }
}
