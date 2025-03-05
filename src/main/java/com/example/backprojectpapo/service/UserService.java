package com.example.backprojectpapo.service;

import com.example.backprojectpapo.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findUserByEmail(String email);
}
