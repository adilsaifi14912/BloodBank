package com.kashif.service;
import com.kashif.dto.RegistrationDTO;
import com.kashif.entity.UserRegistration;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UtilityService {

//    private static final Map<String, RegistrationDTO> allActiveUsers = new HashMap<>();
//    public void updateActiveUsers(RegistrationDTO registrationDTO, String cause){
//        if(registrationDTO!=null){
//            if(cause.equalsIgnoreCase("add")){
//                allActiveUsers.put(registrationDTO.getUsername(), registrationDTO);
//            }
//            if(cause.equalsIgnoreCase("remove")) {
//                  allActiveUsers.remove(registrationDTO.getUsername());
//            }
//        }
//    }
//
//    public Map<String, RegistrationDTO> getAllActiveUsers() {
//        return allActiveUsers;
//    }
//
//    public List<RegistrationDTO> notLoggedInUser(List<RegistrationDTO> allUserList){
//        ArrayList<RegistrationDTO> list = new ArrayList<>();
//        for(RegistrationDTO user : allUserList){
//            if(!getAllActiveUsers().containsKey(user.getUsername())){
//                list.add(user);
//            }
//        }
//        return list;
//    }
private static final Map<String, Boolean> allActiveUsers = new HashMap<>();

    public void updateActiveUsers(RegistrationDTO registrationDTO, String cause){
        if(registrationDTO != null){
            if(cause.equalsIgnoreCase("add")){
                allActiveUsers.put(registrationDTO.getUsername(), true);
            }
            if(cause.equalsIgnoreCase("remove")) {
                allActiveUsers.remove(registrationDTO.getUsername());
            }
        }
    }

    public Map<String, Boolean> getAllActiveUsers() {
        return allActiveUsers;
    }

    public List<RegistrationDTO> notLoggedInUser(List<RegistrationDTO> allUserList){
        List<RegistrationDTO> notLoggedInUsers = new ArrayList<>();
        for (RegistrationDTO user : allUserList) {
            if (!allActiveUsers.containsKey(user.getUsername())) {
                notLoggedInUsers.add(user);
            }
        }
        return notLoggedInUsers;
    }

    public List<RegistrationDTO> sortUsers(String sortOption, List<RegistrationDTO> allEndUserList) {

        switch (sortOption){
            case "name":
                return allEndUserList.stream().sorted(Comparator.comparing(RegistrationDTO::getName))
                        .collect(Collectors.toList());
            case "createdBy":
                return allEndUserList.stream().sorted(Comparator.comparing(RegistrationDTO::getCreatedBy))
                        .collect(Collectors.toList());
            case "bloodGroup":
                return allEndUserList.stream().sorted(Comparator.comparing(RegistrationDTO::getBloodGroup))
                        .collect(Collectors.toList());

        }
        return allEndUserList;

    }

    public List<RegistrationDTO> filterByAgent(List<RegistrationDTO> allUsers){
        return allUsers.stream()
                .filter(user -> !user.getCreatedBy().equalsIgnoreCase("-") && !"self".equals(user.getCreatedBy()))
                .collect(Collectors.toList());

    }

    public List<RegistrationDTO> filterByCurrentAgent(List<RegistrationDTO> allUsers, RegistrationDTO dto){
        List<RegistrationDTO> allUsersByAllAgent = filterByAgent(allUsers);
        return allUsersByAllAgent.stream().filter(u->u.getCreatedBy().equalsIgnoreCase(dto.getUsername())).collect(Collectors.toList());
    }

    public List<RegistrationDTO> filterByUsername(String username, List<RegistrationDTO> allUsers){
        return allUsers.stream().filter(user->user.getUsername().startsWith(username)).collect(Collectors.toList());
    }
    public List<RegistrationDTO> filterByDate(String startDate, String endDate, List<RegistrationDTO> allUsers) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date sdate = dateFormat.parse(startDate);
            Date edate = dateFormat.parse(endDate);
            return allUsers.stream()
                    .filter(user -> user.getCreationTime().compareTo(sdate) >= 0 && user.getCreationTime().compareTo(edate) <= 0)
                    .collect(Collectors.toList());
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        return allUsers;
    }


}
