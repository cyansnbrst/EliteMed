package com.cyansnbrst.pr15.services;


import com.cyansnbrst.pr15.entities.Role;
import com.cyansnbrst.pr15.entities.User;
import com.cyansnbrst.pr15.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list() {
        return  userRepository.findAll();
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByUsername(principal.getName());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public void addUser(User user) {
        userRepository.save(user);
    }
}

