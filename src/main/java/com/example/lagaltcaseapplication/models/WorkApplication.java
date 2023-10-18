package com.example.lagaltcaseapplication.models;

import javax.persistence.*;

@Entity
public class WorkApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long application_id;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private boolean isAccepted;

    private String motivation;

}
