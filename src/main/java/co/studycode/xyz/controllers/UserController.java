package co.studycode.xyz.controllers;


import co.studycode.xyz.models.UserSummary;
import co.studycode.xyz.repositories.UserRepository;
import co.studycode.xyz.security.CurrentUser;
import co.studycode.xyz.security.UserPrinciple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrinciple currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getFullName(), currentUser.getEmail());
    }

    @GetMapping("/users")
    public List<Object[]> getAllUsers(){
        return userRepository.findAllUsers();
    }

//    @PutMapping("/user/{id}")
//    public User updateUser()
}
