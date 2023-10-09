package com.example.lagaltcaseapplication.repository;

import com.example.lagaltcaseapplication.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner_UserId(Long userId);
}

