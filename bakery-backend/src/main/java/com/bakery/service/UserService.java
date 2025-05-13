package com.bakery.service;

import com.bakery.model.User;
import com.bakery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }
}
