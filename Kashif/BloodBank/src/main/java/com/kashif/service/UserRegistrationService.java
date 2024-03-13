package com.kashif.service;
import com.kashif.dto.RegistrationDTO;
import com.kashif.entity.UserRegistration;
import com.kashif.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRegistration userRegistration ;

    @Autowired
    private UserRepo repo;


    public RegistrationDTO registerUser(RegistrationDTO registrationDTO){

        if(registrationDTO.getUsername()!=null && !registrationDTO.getUsername().isBlank() && !repo.existsByUsername(registrationDTO.getUsername())){
            userRegistration.setUsername(registrationDTO.getUsername());
        }else {
            throw new RuntimeException("Username might be  null or Already Exists");
        }
        if(registrationDTO.getName()!=null && !registrationDTO.getName().isBlank()){
            userRegistration.setName(registrationDTO.getName());
        }else {
            throw new RuntimeException("Name Can't be null");
        }
        if(registrationDTO.getEmail()!=null && !registrationDTO.getEmail().isBlank()){
            userRegistration.setEmail(registrationDTO.getEmail());
        }else {
            throw new RuntimeException("Email Can't be null");
        }
        if(registrationDTO.getAddress()!=null && !registrationDTO.getAddress().isBlank()) {
            userRegistration.setAddress(registrationDTO.getAddress());
        }else {
            throw new RuntimeException("Address Can't be null");
        }
        if(registrationDTO.getPassword()!=null && !registrationDTO.getPassword().isBlank()) {
            userRegistration.setPassword(registrationDTO.getPassword());
            registrationDTO.setPassword("*******");
        }else {
            throw new RuntimeException("Password Can't be null");
        }

        if(registrationDTO.getDob()!=null && !registrationDTO.getDob().isBlank()) {
            userRegistration.setDob(LocalDate.parse(registrationDTO.getDob()));

        }else {
            throw new RuntimeException("DOB Can't be null");
        }
        if(registrationDTO.getBloodGroup()!=null && !registrationDTO.getBloodGroup().isBlank()) {
            userRegistration.setBloodGroup(registrationDTO.getBloodGroup());
        }else {
            throw new RuntimeException("Blood Group Can't be null");
        }

        userRegistration.setId(0);
        userRegistration.setRole("EndUser");
        userRegistration.setCreatedBy("-");
        userRegistration.setCreationTime(new Date());
        userRegistration.setUpdatedTime(new Date());
        userRegistration.setModifyBy("-");
        //--------------- Extra fields Default data -------
        userRegistration.setNewUser(true);
        userRegistration.setBlockedStatus(false);

        repo.save(userRegistration);
        return registrationDTO;
    }









}
