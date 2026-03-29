// com.loan.demo.controller.AuthController.java
package com.loan.demo.controller;

import com.loan.demo.model.User;
import com.loan.demo.dto.UserDTO;
import com.loan.demo.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ✅ LOGIN endpoint - returns UserDTO (safe)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        Optional<User> user = authService.authenticate(loginData.get("username"), loginData.get("password"));
        
        if (user.isPresent()) {
            User u = user.get();
            // Using the 3-parameter constructor here
            UserDTO dto = new UserDTO(u.getId(), u.getUsername(), u.getRole());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // ✅ REGISTER endpoint - returns UserDTO (safe)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> registerData) {
        try {
            User newUser = authService.register(
                registerData.get("username"),
                registerData.get("password"),
                registerData.get("role")
            );
            // Using the 3-parameter constructor here
            UserDTO dto = new UserDTO(newUser.getId(), newUser.getUsername(), newUser.getRole());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (RuntimeException e) {
            // Return error as JSON
            Map<String, String> errorResponse = Map.of("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    // ✅ GET ALL USERS - return list of UserDTOs instead of full entities
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = authService.getAllUsers();
        List<UserDTO> dtoList = users.stream()
            .map(u -> new UserDTO(u.getId(), u.getUsername(), u.getRole())) // Using 3-parameter constructor here
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // ✅ DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        authService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
