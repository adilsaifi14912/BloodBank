package com.kashif.service;
import com.kashif.dto.RegistrationDTO;
import com.kashif.entity.UserRegistration;
import com.kashif.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserRegistrationService {
    @Autowired
    private UserRegistration userRegistration;

    @Autowired
    private UserRepo userRepo;


    public RegistrationDTO registerUser(RegistrationDTO registrationDTO) {

        if (registrationDTO.getUsername() != null && !registrationDTO.getUsername().isEmpty() && !userRepo.existsByUsername(registrationDTO.getUsername())) {
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
            throw new RuntimeException("Blood Group Can't be null");
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
        if(registrationDTO.getCommission() == null){
            userRegistration.setCommission(-1L);
        }
        userRegistration.setCommission(registrationDTO.getCommission());
        userRegistration.setId(0);
        userRegistration.setCreationTime(new Date());

        //--------------- Extra fields Default data -------
        if((userRegistration.getRole().equalsIgnoreCase("enduser") && !userRegistration.getCreatedBy().equalsIgnoreCase("self")) || (userRegistration.getRole().equalsIgnoreCase("enduser") && userRegistration.getCreatedBy().equalsIgnoreCase("agent")))
            userRegistration.setNewUser(true);
        userRegistration.setBlockedStatus(false);
        userRegistration.setUpdatedTime(new Date());
        userRegistration.setModifyBy("NA");
        userRepo.save(userRegistration);
        return registrationDTO;
    }

    public UserRegistration convertRegistrationDTOtoEntity(RegistrationDTO dto){
        userRegistration.setPassword(dto.getPassword());
        userRegistration.setEmail(dto.getEmail());
        userRegistration.setRole(dto.getRole());
        userRegistration.setBloodGroup(dto.getBloodGroup());
        userRegistration.setCreationTime(dto.getCreationTime());
        userRegistration.setDob(LocalDate.parse(dto.getDob()));
        userRegistration.setCommission(dto.getCommission());
        userRegistration.setUsername(dto.getUsername());
        userRegistration.setAddress(dto.getAddress());
        userRegistration.setCreatedBy(dto.getCreatedBy());
        userRegistration.setModifyBy(dto.getModifyBy());
        userRegistration.setName(dto.getName());
        userRegistration.setUpdatedTime(dto.getUpdatedTime());
        userRegistration.setNewUser(dto.isNewUser());
        userRegistration.setBlockedStatus(dto.isBlockedStatus());
        return userRegistration;
    }
    private RegistrationDTO convertEntityToDto(UserRegistration userRegistration){
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setPassword(userRegistration.getPassword());
        registrationDTO.setEmail(userRegistration.getEmail());
        registrationDTO.setRole(userRegistration.getRole());
        registrationDTO.setBloodGroup(userRegistration.getBloodGroup());
        registrationDTO.setCreationTime(userRegistration.getCreationTime());
        registrationDTO.setDob(userRegistration.getDob().toString());
        registrationDTO.setCommission(userRegistration.getCommission());
        registrationDTO.setUsername(userRegistration.getUsername());
        registrationDTO.setAddress(userRegistration.getAddress());
        registrationDTO.setCreatedBy(userRegistration.getCreatedBy());
        registrationDTO.setModifyBy(userRegistration.getModifyBy());
        registrationDTO.setName(userRegistration.getName());
        registrationDTO.setUpdatedTime(userRegistration.getUpdatedTime());
        registrationDTO.setNewUser(userRegistration.isNewUser());
        registrationDTO.setBlockedStatus(userRegistration.isBlockedStatus());
        return registrationDTO;
    }

    public List<RegistrationDTO> getAllUsers(){
        return userRepo.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public boolean getBlockedStatusByUsername(String username) {
        return userRepo.findByUsername(username).get().isBlockedStatus();
    }

    public void updateBlockedStatusByUsername(boolean blockedStatus, String username) {
        userRepo.updateBlockedStatusByUsername(true, username);
    }

    public String getPasswordByUsername(String username) {
        return userRepo.findByUsername(username).get().getPassword();
    }

    public void updatePasswordByUsername(String newPassword, String username) {
        userRepo.updatePasswordByUsername(newPassword, username);
    }

    public void updateNewUserByUsername(boolean newUserStatus, String username) {
        userRepo.updateNewUserByUsername(newUserStatus, username);
    }

    public Optional<RegistrationDTO> findByUsername(String username) {
        Optional<UserRegistration> chk = userRepo.findByUsername(username);
        if(chk.isPresent()){
            RegistrationDTO registrationDTO = convertEntityToDto(chk.get());
            return Optional.of(registrationDTO);
        }

        return Optional.empty();
    }

    public List<RegistrationDTO> findAllEndUsers() {
        List<RegistrationDTO> allUserList = getAllUsers();
        return allUserList.stream()
                .filter(user -> user.getRole().equalsIgnoreCase("enduser"))
                .collect(Collectors.toList());
    }




}
