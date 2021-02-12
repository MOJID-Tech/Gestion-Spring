package com.example.demo.entity;



import javax.persistence.*;
import java.io.Serializable;
@Entity

public class user_role implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    @ManyToOne
    @JoinColumn(name="ROLE_ID")
    private Role role ;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User  user;

    public user_role() {
    }
    public user_role(User user, Role role ) {
        this.user=user;
        this.role=role;
    }

    public Integer getId() {
        return id;
    }





    public Role getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public void setRole(Role role) {
        this.role = role;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
