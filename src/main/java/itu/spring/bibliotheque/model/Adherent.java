package itu.spring.bibliotheque.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "adherent")
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adherent_type_id")
    private AdherentType adherentType;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public AdherentType getAdherentType() { return adherentType; }
    public void setAdherentType(AdherentType adherentType) { this.adherentType = adherentType; }
    public int getAge(Date refDate) {
        int refYear = refDate.toLocalDate().getYear();
        int birthYear = this.utilisateur.getBirthDate().toLocalDate().getYear();
        return refYear - birthYear;
    }
    public int getAge() {
        return getAge(new Date(System.currentTimeMillis()));
    }
}
