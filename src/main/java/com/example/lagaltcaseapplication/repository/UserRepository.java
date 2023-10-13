package com.example.lagaltcaseapplication.repository;

import com.example.lagaltcaseapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
