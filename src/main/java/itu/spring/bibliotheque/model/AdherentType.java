package itu.spring.bibliotheque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "adherent_type")
public class AdherentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "default_quote")
    private Integer defaultQuote;

    @Column(name = "default_duration")
    private Integer defaultDuration;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getDefaultQuote() { return defaultQuote; }
    public void setDefaultQuote(Integer defaultQuote) { this.defaultQuote = defaultQuote; }
    public Integer getDefaultDuration() { return defaultDuration; }
    public void setDefaultDuration(Integer defaultDuration) { this.defaultDuration = defaultDuration; }
}
