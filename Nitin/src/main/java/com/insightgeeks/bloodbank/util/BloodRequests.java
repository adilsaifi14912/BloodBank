package com.insightgeeks.bloodbank.util;

import com.insightgeeks.bloodbank.dto.BloodRequestDTO;
import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.entities.BloodBank;
import com.insightgeeks.bloodbank.entities.UserModel;
import com.insightgeeks.bloodbank.repository.BloodRepository;
import com.insightgeeks.bloodbank.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BloodRequests {

    @Autowired
    BloodRepository bloodRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<BloodRequestDTO> getBloodRequestLists(SignupDTO user, String from, String to, String status) {
        List<BloodRequestDTO> bloodRequests = new ArrayList<>();
        Iterable<BloodBank> requests = bloodRepository.findAll();

        switch (user.getRole()){

            case "admin":
                bloodRequests = StreamSupport.stream(requests.spliterator(), false)
                        .filter(request -> "admin".equals(user.getRole()))
                        .map(request -> {
                            BloodRequestDTO dto = convertToBloodRequestDTO(request);
                            dto.setUserInfo(convertToSignupDTO(userRepository.findById(request.getUserModel().getId()).orElse(null)));
                            return dto;
                        })
                        .collect(Collectors.toList());
                        break;

            case "user":
                bloodRequests = StreamSupport.stream(requests.spliterator(), false)
                        .filter(request -> user.getUsername().equals(request.getUserModel().getUsername()))
                        .map(this::convertToBloodRequestDTO)
                        .collect(Collectors.toList());
                        break;


            case "agent":
                bloodRequests = StreamSupport.stream(requests.spliterator(), false)
                        .filter(request -> user.getUsername().equals(request.getCreatedBy()))
                        .map(request -> {
                            BloodRequestDTO dto = convertToBloodRequestDTO(request);
                            dto.setUserInfo(convertToSignupDTO(userRepository.findById(request.getUserModel().getId()).orElse(null)));
                            return dto;
                        })
                        .collect(Collectors.toList());

        }

        if(!(from.equals("") && to.equals("")))
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fromConverted = LocalDate.parse(from, formatter);
            LocalDate toConverted = LocalDate.parse(to, formatter);


            bloodRequests = bloodRequests.stream()
                    .filter(request -> {
                        LocalDate createdDate = request.getCreatedOn().toLocalDate();
                        return !createdDate.isBefore(fromConverted) && !createdDate.isAfter(toConverted);
                    })
                    .collect(Collectors.toList());
        }

        if(!status.equals(""))
        {
            bloodRequests = bloodRequests.stream().filter(request -> request.getStatus()
                    .equals(status)).collect(Collectors.toList());
        }
        return bloodRequests;
    }

    public BloodRequestDTO convertToBloodRequestDTO(BloodBank bloodBank) {
        return modelMapper.map(bloodBank, BloodRequestDTO.class);
    }

    public SignupDTO convertToSignupDTO(UserModel userModel) {
        return modelMapper.map(userModel, SignupDTO.class);
    }

//    public List<BloodRequestDTO> getBloodRequestsBetweenwDates(SignupDTO user, String from, String to)
//    {
//
//    }
}
