package com.example.lagaltcaseapplication.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "for_name")
    private String forName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;

    @Column(name = "email")
    private String email;

    @Column(name = "user_role")
    private String userRole;


    public User(Long userId, String forName, String lastName, String description, String country, String email, String userRole) {
        this.userId = userId;
        this.forName = forName;
        this.lastName = lastName;
        this.description = description;
        this.country = country;
        this.email = email;
        this.userRole = userRole;
    }
}
