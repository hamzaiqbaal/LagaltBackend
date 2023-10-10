package com.example.lagaltcaseapplication.controllers;


import com.example.lagaltcaseapplication.dto.ProjectDTO;
import com.example.lagaltcaseapplication.dto.UserDTO;
import com.example.lagaltcaseapplication.exceptions.UserNotFoundException;
import com.example.lagaltcaseapplication.models.User;
import com.example.lagaltcaseapplication.repository.UserRepository;
import com.example.lagaltcaseapplication.services.project.ProjectService;
import com.example.lagaltcaseapplication.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            UserDTO userDTO = userService.getUserById(id);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/projects")
    public ResponseEntity<List<ProjectDTO>> getProjectsByUserId(@PathVariable Long userId) {
        List<ProjectDTO> userProjects = projectService.getProjectsByUserId(userId);
        return new ResponseEntity<>(userProjects, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}

