package com.example.lagaltcaseapplication.services.comment;

import com.example.lagaltcaseapplication.dto.CommentDTO;
import com.example.lagaltcaseapplication.exceptions.ProjectNotFoundException;
import com.example.lagaltcaseapplication.mapper.CommentMapper;
import com.example.lagaltcaseapplication.models.Comment;
import com.example.lagaltcaseapplication.models.Project;
import com.example.lagaltcaseapplication.repository.CommentRepository;
import com.example.lagaltcaseapplication.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getCommentsByProject(Long projectId) {
        return commentRepository.findByProjectId(projectId);
    }

    @Override
    public void addCommentToProject(Long projectId, CommentDTO commentDTO) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            Comment comment = commentMapper.toEntity(commentDTO);
            comment.setProject(project);  // setting project here
            commentRepository.save(comment);
        } else {
            throw new ProjectNotFoundException(projectId);
        }
    }

}
