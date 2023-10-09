package com.example.lagaltcaseapplication.models;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skill {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @Getter
    @Column(name = "skill_name")
    private String skillName;

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @ManyToMany(mappedBy = "skills")
    private List<Project> projects;

}


