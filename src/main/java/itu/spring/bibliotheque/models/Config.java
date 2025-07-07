package itu.spring.bibliotheque.models;

import jakarta.persistence.*;

@Entity
@Table(name = "config")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "max_extension")
    private Integer maxExtension;

    @Column(name = "default_sanction")
    private Integer defaultSanction;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getMaxExtension() { return maxExtension; }
    public void setMaxExtension(Integer maxExtension) { this.maxExtension = maxExtension; }
    public Integer getDefaultSanction() {
        return defaultSanction;
    }
    public void setDefaultSanction(Integer defaultSanction) {
        this.defaultSanction = defaultSanction;
    }
}
