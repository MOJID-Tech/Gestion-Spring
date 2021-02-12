package com.example.demo.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Prestation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String description;

    private String titre ;
    private Double prix;
    private String adresse;
    private Integer capacite ;
    private Boolean livrable ;

    @ManyToOne(cascade=CascadeType.ALL )
    @JoinColumn(name = "typeService_id")
    private TypeService typeService;

    @ManyToOne(cascade=CascadeType.ALL )
    @JoinColumn(name = "client_id")
    private Client client;

    @Transient

    @OneToMany(mappedBy = "demonstration", fetch = FetchType.LAZY,cascade=CascadeType.ALL)

    private Set<Demonstration> demonstrations = new HashSet<Demonstration>();

    public Prestation() {

    }

    public Prestation(Long id, String description, String titre, Double prix, String adresse, Integer capacite, Boolean livrable) {
        this.id = id;
        this.description = description;
        this.titre = titre;
        this.prix = prix;
        this.adresse = adresse;
        this.capacite = capacite;
        this.livrable = livrable;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TypeService getTypeService() {
        return typeService;
    }


    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public Set<Demonstration> getDemonstrations() {
        return demonstrations;
    }

    public void setDemonstrations(Set<Demonstration> demonstrations) {
        this.demonstrations = demonstrations;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitre() {
        return titre;
    }

    public Double getPrix() {
        return prix;
    }

    public String getAdresse() {
        return adresse;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public Boolean getLivrable() {
        return livrable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public void setLivrable(Boolean livrable) {
        this.livrable = livrable;
    }



}
