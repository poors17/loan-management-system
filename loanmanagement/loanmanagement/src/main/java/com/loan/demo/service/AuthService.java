package com.loan.demo.service;

import com.loan.demo.model.User;
import com.loan.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Authenticate user by username and password
    public Optional<User> authenticate(String username, String password) {
        System.out.println("Authenticating user: " + username);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            System.out.println("User found: " + user.get().getUsername());
            if (user.get().getPassword().equals(password)) {
                System.out.println("Password matches");
                return user;
            } else {
                System.out.println("Password does NOT match");
            }
        } else {
            System.out.println("User not found");
        }
        return Optional.empty();
    }

    // Register new user, throws if username exists
    public User register(String username, String password, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User(username, password, role);
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
