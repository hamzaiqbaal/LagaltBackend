package com.example.lagaltcaseapplication.services.user;

import com.example.lagaltcaseapplication.dto.SkillDTO;
import com.example.lagaltcaseapplication.dto.UserDTO;
import com.example.lagaltcaseapplication.exceptions.SkillNotFoundException;
import com.example.lagaltcaseapplication.exceptions.UserNotFoundException;
import com.example.lagaltcaseapplication.mapper.SkillMapper;
import com.example.lagaltcaseapplication.mapper.UserMapper;
import com.example.lagaltcaseapplication.models.Skill;
import com.example.lagaltcaseapplication.models.User;
import com.example.lagaltcaseapplication.repository.SkillRepository;
import com.example.lagaltcaseapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private SkillRepository skillRepository;

    @Autowired
    private SkillMapper skillMapper;

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserMapper.toDTO(user);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update only non-null fields from the incoming DTO
            User updatedUser = UserMapper.updateEntity(existingUser, updatedUserDTO);

            // Save the updated entity back to the database
            updatedUser = userRepository.save(updatedUser);

            // Convert back to DTO and return
            return UserMapper.toDTO(updatedUser);
        } else {
            throw new UserNotFoundException(id);
        }
    }
    @Override
    public void addSkillToUser(Long userId, Long skillId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId)); // toString() because it expects String
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new SkillNotFoundException(skillId.toString())); // No toString() because it expects Long

        user.getSkills().add(skill);
        userRepository.save(user);
    }







}

