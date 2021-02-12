package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity

public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String nom_client;
    private String prenom_client;
    private String emailclient;
    private String tel_client;
    private String adresse_client;
    @Temporal(TemporalType.DATE)
    private Date datenaissance_client;

    @Transient
    @OneToMany(mappedBy = "prestation", fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    private Set<Prestation> Users = new HashSet<Prestation>();

    public Client()
    {

    }

    public Client(String nom_client, String prenom_client, String email_client, String tel_client, Date datenaissance_client) {
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.emailclient = email_client;
        this.tel_client = tel_client;
        this.datenaissance_client = datenaissance_client;
    }

    public Client(Long id, String nom_client, String prenom_client, String email_client, String tel_client, String adresse_client) {
        this.id = id;
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.emailclient = email_client;
        this.tel_client = tel_client;
        this.adresse_client = adresse_client;
    }

    public Client(String nom_client, String prenom_client, String email_client, String tel_client, String adresse_client, Date datenaissance_client) {
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.emailclient = email_client;
        this.tel_client = tel_client;
        this.adresse_client = adresse_client;
        this.datenaissance_client = datenaissance_client;
    }

    public Client(String nom_client, String prenom_client, String email_client, String tel_client,String adresse_client) {
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.emailclient = email_client;
        this.tel_client = tel_client;
        this.adresse_client = adresse_client;
    }
    /****** getters and setters ******/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    public String getEmailclient() {
        return emailclient;
    }

    public void setEmailclient(String emailclient) {
        this.emailclient = emailclient;
    }

    public String getTel_client() {
        return tel_client;
    }

    public void setTel_client(String tel_client) {
        this.tel_client = tel_client;
    }

    public Date getDatenaissance_client() {
        return datenaissance_client;
    }

    public void setDatenaissance_client(Date datenaissance_client) {
        this.datenaissance_client = datenaissance_client;
    }

    //ublic Set<User> getUsers() {
      //  return Users;
    //}

    //public void setUsers(Set<User> users) {
      //  Users = users;
    //}

    public String getAdresse_client() {
        return adresse_client;
    }

    public void setAdresse_client(String adresse_client) {
        this.adresse_client = adresse_client;
    }

    /********** toString************/
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom_client='" + nom_client + '\'' +
                ", prenom_client='" + prenom_client + '\'' +
                ", email_client='" + emailclient + '\'' +
                ", tel_client='" + tel_client + '\'' +
                ", adresse_client='" + adresse_client + '\'' +
                ", datenaissance_client=" + datenaissance_client +
                ", Users=" + Users +
                '}';
    }
}
