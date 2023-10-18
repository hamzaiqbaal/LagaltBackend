package com.example.lagaltcaseapplication.services.comment;

import com.example.lagaltcaseapplication.dto.CommentDTO;
import com.example.lagaltcaseapplication.models.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByProject(Long projectId);

    Comment addCommentToProject(Long projectId, CommentDTO commentDTO);

    void deleteComment(Long commentId);

}
