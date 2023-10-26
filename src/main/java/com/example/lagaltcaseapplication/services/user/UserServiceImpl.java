package com.example.lagaltcaseapplication.services.user;

import com.example.lagaltcaseapplication.dto.ProjectDTO;
import com.example.lagaltcaseapplication.dto.UserDTO;
import com.example.lagaltcaseapplication.exceptions.UserNotFoundException;
import com.example.lagaltcaseapplication.mapper.ProjectMapper;
import com.example.lagaltcaseapplication.mapper.UserMapper;
import com.example.lagaltcaseapplication.models.User;
import com.example.lagaltcaseapplication.repository.ProjectRepository;
import com.example.lagaltcaseapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lagaltcaseapplication.models.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

        /*  For the keycloak
    @Override
    public AppUser getUserById(String uid) {
        return userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFoundException());
    }
    */

    /*
    @Override
    public AppUser addUser(String uid) {
        // Prevents internal server error for duplicates
        if (userRepository.existsById(uid)) {
            throw new UserAlreadyExistsException();
        }
        // Create new user
        AppUser user = new AppUser();
        user.setUid(uid);
        user.setComplete(false);
        return userRepository.save(user);
    }
    */


    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            List<Project> projects = projectRepository.findByOwner_UserId(id);

            List<ProjectDTO> projectDTOs = projects.stream()
                    .map(project -> projectMapper.toDTO(project))
                    .collect(Collectors.toList());

            UserDTO userDTO = userMapper.toDTO(user);

            userDTO.setProjects(projectDTOs);

            return userDTO;
        } else {
            throw new UserNotFoundException(id);
        }
    }




    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getProjects() == null) {
            userDTO.setProjects(new ArrayList<>());
        }
        if (userDTO.getSkillIds() == null) {
            userDTO.setSkillIds(new ArrayList<>());
        }

        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            User updatedUser = userMapper.updateEntity(existingUser, updatedUserDTO);

            updatedUser = userRepository.save(updatedUser);

            return userMapper.toDTO(updatedUser);
        } else {
            throw new UserNotFoundException(id);
        }
    }
    @Override
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }


}

