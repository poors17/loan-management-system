// com.loan.demo.dto.UserDTO.java
package com.loan.demo.dto;

public class UserDTO {

    private Long id;
    private String username;
    private String role;

    // Constructor for 3 parameters (id, username, role)
    public UserDTO(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
