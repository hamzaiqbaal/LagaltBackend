package com.example.lagaltcaseapplication.repository;

import com.example.lagaltcaseapplication.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.project.projectId = ?1")
    List<Comment> findByProjectId(Long projectId);
}
