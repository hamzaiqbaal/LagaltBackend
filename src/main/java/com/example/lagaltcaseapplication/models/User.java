package com.example.lagaltcaseapplication.models;


import com.example.lagaltcaseapplication.enums.Skills;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_profile_visible", nullable = false)
    private boolean isProfileVisible = true;

    @Column(name = "profile_picture")
    private String profilePicture;


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

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Project> projects = new ArrayList<>();

    @ManyToMany(mappedBy = "participants")
    private List<Project> participatingProjects;

    @ElementCollection(targetClass = Skills.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private Set<Skills> skills;

}
