package com.example.lagaltcaseapplication.repository;

import com.example.lagaltcaseapplication.models.Skill;
import com.example.lagaltcaseapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
