package com.example.lagaltcaseapplication.models;

import com.example.lagaltcaseapplication.enums.Industry;
import com.example.lagaltcaseapplication.enums.Skills;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "owner_user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private User owner;

    @Enumerated(EnumType.STRING)
    private Industry industry;


    @ElementCollection(targetClass = Skills.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "project_skills_required", joinColumns = @JoinColumn(name = "project_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "skill")
    private Set<Skills> skillsRequired;
}

