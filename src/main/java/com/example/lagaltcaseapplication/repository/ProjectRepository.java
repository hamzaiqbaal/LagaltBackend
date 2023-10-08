package com.example.lagaltcaseapplication.repository;

import com.example.lagaltcaseapplication.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

