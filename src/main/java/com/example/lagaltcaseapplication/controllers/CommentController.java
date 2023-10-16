package com.example.lagaltcaseapplication.controllers;

import com.example.lagaltcaseapplication.dto.CommentDTO;
import com.example.lagaltcaseapplication.mapper.CommentMapper;
import com.example.lagaltcaseapplication.models.Comment;
import com.example.lagaltcaseapplication.services.comment.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @ApiOperation(value = "Get all comments for a project")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByProject(@PathVariable Long projectId) {
        List<Comment> comments = commentService.getCommentsByProject(projectId);
        List<CommentDTO> commentDTOs = comments.stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
    }

    @GetMapping("/projects/{projectId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByProjectId(@PathVariable Long projectId) {
        List<Comment> comments = commentService.getCommentsByProject(projectId);
        List<CommentDTO> commentDTOs = comments.stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a comment to a project")
    @PostMapping("/project/{projectId}")
    public ResponseEntity<CommentDTO> addCommentToProject(@PathVariable Long projectId, @RequestBody CommentDTO commentDTO) {
        Comment comment = commentMapper.toEntity(commentDTO);
        commentService.addCommentToProject(projectId, commentDTO);
        CommentDTO createdCommentDTO = commentMapper.toDTO(comment);
        return new ResponseEntity<>(createdCommentDTO, HttpStatus.CREATED);
    }
}


