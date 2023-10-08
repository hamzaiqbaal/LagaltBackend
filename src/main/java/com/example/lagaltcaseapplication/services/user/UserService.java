package com.example.lagaltcaseapplication.services.user;

import com.example.lagaltcaseapplication.dto.UserDTO;

public interface UserService {
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);

}

