package itu.spring.bibliotheque.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AdherentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "available_quote")
    private Integer availableQuote;
    @Column(name = "available_duration")
    private Integer availableDuration;

    @ManyToOne
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAvailableQuote() {
        return availableQuote;
    }
    public void setAvailableQuote(Integer availableQuote) {
        this.availableQuote = availableQuote;
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

}
