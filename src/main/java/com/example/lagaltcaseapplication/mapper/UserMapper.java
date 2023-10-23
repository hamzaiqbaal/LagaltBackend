package com.example.lagaltcaseapplication.mapper;

import com.example.lagaltcaseapplication.dto.ProjectDTO;
import com.example.lagaltcaseapplication.dto.UserDTO;
import com.example.lagaltcaseapplication.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.lagaltcaseapplication.enums.Skills;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private ProjectMapper projectMapper;

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setForName(user.getForName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setAge(user.getAge());
        userDTO.setDescription(user.getDescription());
        userDTO.setCountry(user.getCountry());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserRole(user.getUserRole());
        if (userDTO.isIncludeProjects()) {
            userDTO.setProjects(user.getProjects().stream()
                    .map(projectMapper::toDTO) // this call will now omit nested participants
                    .collect(Collectors.toList()));
        }else {
            userDTO.setProjects(new ArrayList<>());
        }

        if (user.getSkills() != null) {
            List<Integer> skillIds = user.getSkills().stream()
                    .map(Skills::getId)
                    .collect(Collectors.toList());
            userDTO.setSkillIds(skillIds);

            List<String> skillNames = user.getSkills().stream()
                    .map(Skills::getName)
                    .collect(Collectors.toList());
            userDTO.setSkillNames(skillNames);
        }

        userDTO.setProfileVisible(user.isProfileVisible());


        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setForName(userDTO.getForName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setAge(userDTO.getAge());
        user.setDescription(userDTO.getDescription());
        user.setCountry(userDTO.getCountry());
        user.setEmail(userDTO.getEmail());
        user.setUserRole(userDTO.getUserRole());

        if (userDTO.getSkillIds() == null || userDTO.getSkillIds().isEmpty()) {
            user.setSkills(new HashSet<>());
        } else {
            Set<Skills> skills = userDTO.getSkillIds().stream()
                    .filter(id -> id > 0)
                    .map(Skills::getById)
                    .collect(Collectors.toSet());
            user.setSkills(skills);
        }

        user.setProfileVisible(userDTO.isProfileVisible());

        return user;
    }

    public static User updateEntity(User existingUser, UserDTO userDTO) {
        if (userDTO.getForName() != null) {
            existingUser.setForName(userDTO.getForName());
        }
        if (userDTO.getLastName() != null) {
            existingUser.setLastName(userDTO.getLastName());
        }
        if (userDTO.getDescription() != null) {
            existingUser.setDescription(userDTO.getDescription());
        }
        if (userDTO.getCountry() != null) {
            existingUser.setCountry(userDTO.getCountry());
        }
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getUserRole() != null) {
            existingUser.setUserRole(userDTO.getUserRole());
        }
        if (userDTO.getAge() != null) {
            existingUser.setAge(userDTO.getAge());
        }
        if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getPassword() != null) {
            existingUser.setPassword(userDTO.getPassword());
        }
        if (userDTO.getSkillIds() == null || userDTO.getSkillIds().isEmpty()) {
            existingUser.setSkills(new HashSet<>());
        } else {
            Set<Skills> skills = userDTO.getSkillIds().stream()
                    .filter(id -> id > 0)
                    .map(Skills::getById)
                    .collect(Collectors.toSet());
            existingUser.setSkills(skills);
        }
        existingUser.setProfileVisible(userDTO.isProfileVisible());

        return existingUser;
    }

    public UserDTO toSimplifiedDTO(User user) {
        UserDTO simplifiedUserDTO = new UserDTO();
        simplifiedUserDTO.setUserId(user.getUserId());
        simplifiedUserDTO.setForName(user.getForName());
        simplifiedUserDTO.setLastName(user.getLastName());
        simplifiedUserDTO.setUserRole(user.getUserRole());
        return simplifiedUserDTO;
    }


}
