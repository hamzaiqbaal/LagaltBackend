package com.example.lagaltcaseapplication.models;


import com.example.lagaltcaseapplication.enums.Skills;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Project> projects;

    @ElementCollection(targetClass = Skills.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private Set<Skills> skills;

}
