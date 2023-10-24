package com.example.lagaltcaseapplication.controllers;

import com.example.lagaltcaseapplication.dto.CommentDTO;
import com.example.lagaltcaseapplication.exceptions.CommentNotFoundException;
import com.example.lagaltcaseapplication.exceptions.ProjectNotFoundException;
import com.example.lagaltcaseapplication.mapper.CommentMapper;
import com.example.lagaltcaseapplication.models.Comment;
import com.example.lagaltcaseapplication.services.comment.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @PreAuthorize("hasRole('ROLE_user') or hasRole('ROLE_admin')")
    public ResponseEntity<List<CommentDTO>> getCommentsByProject(@PathVariable Long projectId) {
        List<Comment> comments = commentService.getCommentsByProject(projectId);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CommentDTO> commentDTOs = comments.stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a comment to a project")
    @PostMapping("/project/{projectId}")
    @PreAuthorize("hasRole('ROLE_user') or hasRole('ROLE_admin')")
    public ResponseEntity<CommentDTO> addCommentToProject(@PathVariable Long projectId, @RequestBody CommentDTO commentDTO) {
        try {
            Comment comment = commentService.addCommentToProject(projectId, commentDTO);  // Assumes your service method returns Comment
            CommentDTO createdCommentDTO = commentMapper.toDTO(comment);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(createdCommentDTO.getId()).toUri();
            return ResponseEntity.created(location).body(createdCommentDTO);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete a comment by its ID")
    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasRole('ROLE_user') or hasRole('ROLE_admin')")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        try {
            commentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CommentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}




