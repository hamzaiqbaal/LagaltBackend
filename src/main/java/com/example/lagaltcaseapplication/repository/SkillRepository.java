package com.example.lagaltcaseapplication.repository;

import com.example.lagaltcaseapplication.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query("select s from Skill s join s.users u where u.userId = :userId")
    List<Skill> findSkillsByUserId(@Param("userId") Long userId);
}

