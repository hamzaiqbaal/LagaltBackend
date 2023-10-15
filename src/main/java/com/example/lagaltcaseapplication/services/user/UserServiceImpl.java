package com.example.lagaltcaseapplication.services.user;

import com.example.lagaltcaseapplication.dto.UserDTO;
import com.example.lagaltcaseapplication.exceptions.UserNotFoundException;
import com.example.lagaltcaseapplication.mapper.UserMapper;
import com.example.lagaltcaseapplication.models.User;
import com.example.lagaltcaseapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return userMapper.toDTO(user);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getProjects() == null) {
            userDTO.setProjects(new ArrayList<>());
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

}

