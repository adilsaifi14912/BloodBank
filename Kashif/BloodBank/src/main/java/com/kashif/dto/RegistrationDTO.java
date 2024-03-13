package com.kashif.dto;


import org.checkerframework.checker.nullness.qual.NonNull;


public class RegistrationDTO {


    private String email;
    private String name;
    private String password;
    private String dob;
    private String username;
    private String role;
    private String address;
    private String bloodGroup;  //added extra on 11 Mar

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.strip();
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name.strip();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.strip();
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase().strip();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.strip();
    }

    @Override
    public String toString() {
        return "UserDTO{" + "email='" + email + '\'' + ", name='" + name + '\'' + ", password='" + password + '\'' + ", dob='" + dob + '\'' + ", username='" + username + '\'' + ", role='" + role + '\'' + ", address='" + address + '\'' + '}';
    }
}
