package com.example.lagaltcaseapplication.mapper;

import com.example.lagaltcaseapplication.dto.UserDTO;
import com.example.lagaltcaseapplication.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setForName(user.getForName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDescription(user.getDescription());
        userDTO.setCountry(user.getCountry());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserRole(user.getUserRole());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setForName(userDTO.getForName());
        user.setLastName(userDTO.getLastName());
        user.setDescription(userDTO.getDescription());
        user.setCountry(userDTO.getCountry());
        user.setEmail(userDTO.getEmail());
        user.setUserRole(userDTO.getUserRole());
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
        return existingUser;
    }


}
