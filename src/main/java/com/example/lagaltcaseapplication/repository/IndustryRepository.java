package com.example.lagaltcaseapplication.repository;

import com.example.lagaltcaseapplication.models.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {
}

