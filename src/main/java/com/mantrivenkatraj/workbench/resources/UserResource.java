package com.mantrivenkatraj.workbench.resources;

import com.mantrivenkatraj.workbench.dtos.UserDTO;
import com.mantrivenkatraj.workbench.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userDetails/{username}")
    public UserDTO getUserDetails(@PathVariable String username){
        return userService.getUserDetails(username);
    }

}
