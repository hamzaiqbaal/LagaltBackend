package com.example.lagaltcaseapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;

public class JwtAuthController {
}


/*
UserController
Create user from token, will create user with id from sub, and maybe also give the role of the user

    @PostMapping("register")
    public ResponseEntity addNewUserFromJwt(@AuthenticationPrincipal Jwt jwt) {
    AppUser user = userService.add(jwt.getClaimAsString("sub"));
    URI uri = URI.create("api/v1/users/" + user.getUid());
    return ResponseEntity.created(uri).build();
}




UserService
Method to add a user

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




@GetMapping("current")
public ResponseEntity getCurrentlyLoggedUser(@AuthenticationPrincipal Jwt jwt) {
    return ResponseEntity.ok(
        userService.findById(jwt.getClaimAsString("sub")));
}



UserService

Is in userService
@Override
public AppUser getById(String uid) {
    return userRepository.findById(uid)
        .orElseThrow(() -> new UserNotFoundException());
}



@GetMapping("principal")
public ResponseEntity getPrincipal(Principal user){
    return ResponseEntity.ok(user);
}


{
    "authorities": [
        {
            "authority": "ROLE_ADMIN"
        }
    ],
    "details": {
        "remoteAddress": "0:0:0:0:0:0:0:1",
        "sessionId": null
    },
    "authenticated": true,
    "principal": {
        "tokenValue": "..."
        // omitted - far more printed out
    }
}



@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @GetMapping("info")
    public ResponseEntity getLoggedInUserInfo(@AuthenticationPrincipal Jwt principal) {
        Map<String, String> map = new HashMap<>();
        map.put("subject", principal.getClaimAsString("sub"));
        map.put("user_name", principal.getClaimAsString("preferred_username"));
        map.put("email", principal.getClaimAsString("email"));
        map.put("first_name", principal.getClaimAsString("given_name"));
        map.put("last_name", principal.getClaimAsString("family_name"));
        map.put("roles", String.valueOf(principal.getClaimAsStringList("roles")));
        return ResponseEntity.ok(map);
    }
}

    }

*/