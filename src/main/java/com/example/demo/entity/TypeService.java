package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity


public class TypeService   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String nom;

    @Transient
    @OneToMany(mappedBy = "prestation", fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    private Set<Prestation> prestations = new HashSet<Prestation>();

    public TypeService() {
    }

    public TypeService(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Set<Prestation> getPrestations() {
        return prestations;
    }

    public void setPrestations(Set<Prestation> prestations) {
        this.prestations = prestations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
