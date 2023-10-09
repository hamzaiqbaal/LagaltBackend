package com.example.lagaltcaseapplication.repository;

import com.example.lagaltcaseapplication.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner_UserId(Long userId);

    @Query("select p from Project p join p.skills s where s.skillId = :skillId")
    List<Project> findProjectsBySkillId(@Param("skillId") Long skillId);

}

