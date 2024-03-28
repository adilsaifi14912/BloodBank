package com.kashif.service;

import com.kashif.dto.RegistrationDTO;
import com.kashif.entity.UserRegistration;
import com.kashif.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserRegistrationService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserRegistration userRegistration;

    @Autowired
    private UserRepo userRepo;


    public RegistrationDTO registerUser(RegistrationDTO registrationDTO) {

        if (registrationDTO.getUsername() != null && !registrationDTO.getUsername().isEmpty() && !existsByUsername(registrationDTO.getUsername())) {
            userRegistration.setUsername(registrationDTO.getUsername());
        } else {

            throw new RuntimeException("Username might be  null or Already Exists");
        }
        if (registrationDTO.getName() != null && !registrationDTO.getName().isEmpty()) {
            userRegistration.setName(registrationDTO.getName());
        } else {
            throw new RuntimeException("Name Can't be null");
        }
        if (registrationDTO.getEmail() != null && !registrationDTO.getEmail().isEmpty()) {
            userRegistration.setEmail(registrationDTO.getEmail());
        } else {
            throw new RuntimeException("Email Can't be null");
        }
        if (registrationDTO.getAddress() != null && !registrationDTO.getAddress().isEmpty()) {
            userRegistration.setAddress(registrationDTO.getAddress());
        } else {
            throw new RuntimeException("Address Can't be null");
        }
        if (registrationDTO.getPassword() != null && !registrationDTO.getPassword().isEmpty()) {
            userRegistration.setPassword(registrationDTO.getPassword());

        } else {
            throw new RuntimeException("Password Can't be null");
        }

        if (registrationDTO.getDob() != null && !registrationDTO.getDob().isEmpty()) {
            userRegistration.setDob(LocalDate.parse(registrationDTO.getDob()));

        } else {
            throw new RuntimeException("DOB Can't be null");
        }
        if (registrationDTO.getBloodGroup() != null && !registrationDTO.getBloodGroup().isEmpty()) {
            userRegistration.setBloodGroup(registrationDTO.getBloodGroup());
        } else {
            throw new RuntimeException("Blood.java Group Can't be null");
        }

        if (registrationDTO.getRole() == null) {
            userRegistration.setRole("EndUser");
        } else {
            userRegistration.setRole(registrationDTO.getRole());
        }
        if (registrationDTO.getCreatedBy() == null) {
            userRegistration.setCreatedBy("Self");
        } else {
            userRegistration.setCreatedBy(registrationDTO.getCreatedBy());
        }
        if (registrationDTO.getCommission() == null) {
            userRegistration.setCommission(0L);
        }
        userRegistration.setCommission(registrationDTO.getCommission());
        userRegistration.setId(0);
        userRegistration.setCreationTime(new Date());

        //--------------- Extra fields Default data -------
        if ((!userRegistration.getRole().equalsIgnoreCase("admin") &&
                userRegistration.getRole().equalsIgnoreCase("EndUser") &&
                userRegistration.getCreatedBy().equalsIgnoreCase("Self")))
            userRegistration.setNewUser(false);
        else userRegistration.setNewUser(true);
        userRegistration.setBlockedStatus(false);
        userRegistration.setUpdatedTime(new Date());
        userRegistration.setModifyBy("NA");
        userRepo.save(userRegistration);
        return registrationDTO;
    }

    public UserRegistration getUserRegistration(RegistrationDTO dto) {
        return userRepo.findByUsername(dto.getUsername()).get();
    }

    private RegistrationDTO convertEntityToDto(UserRegistration userRegistration) {
        return this.modelMapper.map(userRegistration, RegistrationDTO.class);

    }

    public List<RegistrationDTO> getAllUsers() {
        return userRepo.findAll().stream().filter(user -> !user.getUsername().equalsIgnoreCase("admin")).map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public boolean existsByUsername(String username) {
        return getUserByUsername(username).isPresent();
    }

    public boolean getBlockedStatusByUsername(String username) {
        return getUserByUsername(username).isPresent() && getUserByUsername(username).get().isBlockedStatus();
    }

    public void updateBlockedStatusByUsername(boolean blockedStatus, String username) {
        userRepo.updateBlockedStatusByUsername(true, username);
    }

    public String getPasswordByUsername(String username) {
        return getUserByUsername(username).isPresent() ? getUserByUsername(username).get().getPassword() : "";
    }


    public void updatePasswordByUsername(String newPassword, String username) {
        userRepo.updatePasswordByUsername(newPassword, username);
    }

    public void updateNewUserByUsername(boolean newUserStatus, String username) {
        userRepo.updateNewUserByUsername(newUserStatus, username);
    }

    public Optional<RegistrationDTO> getUserByUsername(String username) {
        Optional<UserRegistration> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            RegistrationDTO registrationDTO = convertEntityToDto(user.get());
            return Optional.of(registrationDTO);
        }

        return Optional.empty();
    }


}
