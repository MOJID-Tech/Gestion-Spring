package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity

public class Demonstration  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private byte[] picByte;
    private String type;
    private String name;

    @ManyToOne(cascade= CascadeType.ALL )
    @JoinColumn(name = "prestation_id")
    private Prestation prestation;

    public Demonstration() {
    }

    public Demonstration(String type, String name, byte[] picByte) {
        this.id = id;
        this.picByte = picByte;
        this.type = type;
        this.name = name;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demonstration{" +
                "id=" + id +
                ", picByte=" + Arrays.toString(picByte) +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", prestation=" + prestation +
                '}';
    }
}

