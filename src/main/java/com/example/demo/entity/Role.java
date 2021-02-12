package com.example.demo.entity;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Role   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id  ;
    private String code_Role ;
    private String nomRole;
    @Transient
    @OneToMany(mappedBy="role" , fetch=FetchType.LAZY)
    private Set<user_role> User_Role  = new HashSet<user_role>(); ;



    public Role() {

    }
    public Role(String code, String Nom) {
        this.nomRole= Nom ;
        this.code_Role= code;

    }
    public Role(String code, String Nom, Set<user_role> user_role) {
        this.nomRole= Nom ;
        this.code_Role= code;
        User_Role = user_role;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode_Role(String code_Role) {
        this.code_Role = code_Role;
    }

    public void setNom_Role(String non_Role) {
        nomRole = non_Role;
    }

    public void setUser_Role(Set<user_role> user_Role) {
        User_Role = user_Role;
    }


    public Integer getId() {
        return id;
    }

    public String getCode_Role() {
        return code_Role;
    }

    public String getNomRole() {
        return nomRole;
    }
    @JsonManagedReference
    public Set<user_role> getUser_Role() {
        return User_Role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", code_Role='" + code_Role + '\'' +
                ", Nom_Role='" + nomRole + '\'' +
                ", User_Role=" + User_Role +
                '}';
    }
}

