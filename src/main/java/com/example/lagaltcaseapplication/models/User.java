package com.example.lagaltcaseapplication.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private List<Project> projects;
}
