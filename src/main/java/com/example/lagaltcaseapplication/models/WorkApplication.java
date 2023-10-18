package com.example.lagaltcaseapplication.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "work_application")
public class WorkApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long application_id;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "is_accepted")
    private boolean isAccepted;

    @Column(name = "motivation")
    private String motivation;

}
