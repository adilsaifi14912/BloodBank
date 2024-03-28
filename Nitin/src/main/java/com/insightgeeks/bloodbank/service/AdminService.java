package com.insightgeeks.bloodbank.service;

import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.entities.BloodBank;
import com.insightgeeks.bloodbank.entities.UserModel;
import com.insightgeeks.bloodbank.repository.BloodRepository;
import com.insightgeeks.bloodbank.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AdminService {

    @Autowired
    BloodRepository bloodRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LoginService loginService;

    private static final Map<String, SignupDTO> allActiveUsers = new HashMap<>();

//
//    public List<BloodRequestDTO> getBloodRequestLists() {
//        List<BloodRequestDTO> bloodRequests = new ArrayList<>();
//        Iterable<BloodBank> requests = bloodRepository.findAll();
//        for (BloodBank request : requests) {
//            BloodRequestDTO dto = convertToBloodRequestDTO(request);
////            convertToSignupDTO((userRepository.findById(request.getUserModel().getId())).get());
//            dto.setUserInfo(convertToSignupDTO((userRepository.findById(request.getUserModel().getId())).get()));
//            bloodRequests.add(dto);
//        }
//
//        return bloodRequests;
//    }
//
//    public BloodRequestDTO convertToBloodRequestDTO(BloodBank bloodBank) {
//        return modelMapper.map(bloodBank, BloodRequestDTO.class);
//    }

    public SignupDTO convertToSignupDTO(UserModel userModel) {
        return modelMapper.map(userModel, SignupDTO.class);
    }

    public List<SignupDTO> convertList(List<UserModel> users) {
        List<SignupDTO> convertedList = new ArrayList<>();
        for (UserModel user : users) {
            convertedList.add(convertToSignupDTO(user));
        }

        return convertedList;
    }

    public List<SignupDTO> sortUsers(String by) {

        switch (by) {
            case "nameAsc":
                return convertList(userRepository.findAllByRoleOrderByUsernameAsc("user"));

            case "createdByAsc":
                return convertList(userRepository.findAllByRoleOrderByCreatedByAsc("user"));

            case "bloodGroupAsc":
                return convertList(userRepository.findAllByRoleOrderByBloodGroupAsc("user"));

            case "nameDesc":
                return convertList(userRepository.findAllByRoleOrderByUsernameDesc("user"));

            case "createdByDesc":
                return convertList(userRepository.findAllByRoleOrderByCreatedByDesc("user"));

            case "bloodGroupDesc":
                return convertList(userRepository.findAllByRoleOrderByBloodGroupDesc("user"));
        }
        return new ArrayList<>();
    }


    public List<SignupDTO> filterSignedUpCreatedUsers(String by, String filterValue) {
        List<SignupDTO> filteredResults = new ArrayList<>();
        List<SignupDTO> allUsers = loginService.fetchSignedupUsers();
        switch (by) {

            case "name":
                allUsers.stream().filter(usr -> usr.getUsername().equals(filterValue))
                        .forEach(filteredResults::add);

                return filteredResults;



            case "agent":
                allUsers.stream()
                        .filter(usr -> !usr.getCreatedBy().equals("self"))
                        .forEach(filteredResults::add);
                return filteredResults;



            case "date":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate filteredValueConverted = LocalDate.parse(filterValue, formatter);

                allUsers.stream()
                        .filter(usr -> usr.getCreatedOn().isEqual(filteredValueConverted))
                        .forEach(filteredResults::add);
                return filteredResults;


            case "loggedInUsers":
                return notLoggedInUser(loginService.fetchSignedupUsers());
        }

        return new ArrayList<>();
        }





    public Map<String, SignupDTO> getAllActiveUsers() {
        return allActiveUsers;
    }

    public List<SignupDTO> notLoggedInUser(List<SignupDTO> allUserList){
        ArrayList<SignupDTO> list = new ArrayList<>();
        for(SignupDTO user : allUserList){
            if(!getAllActiveUsers().containsKey(user.getUsername())){
                list.add(user);
            }
        }
        return list;
    }


    public void updateActiveUsers(SignupDTO userRegistration, String cause){
        if(userRegistration!=null){
            if(cause.equalsIgnoreCase("add")){
                allActiveUsers.put(userRegistration.getUsername(), userRegistration);
            }
            if(cause.equalsIgnoreCase("remove")) {
                allActiveUsers.remove(userRegistration.getUsername());
            }
        }
    }

    public boolean acceptBloodRequest(int id)
    {
        BloodBank bloodRequest = (bloodRepository.findById(id)).get();
//        UserModel user = bloodRequest.getUserModel();
//
//        if(LocalDate.now().isBefore(user.getNextRequestEligibleDate()) || !(bloodRequest.getType().equals("donate"))) {
//            return false;
//        }
//        bloodRequest.setStatus("approved");
//        bloodRepository.save(bloodRequest);
//        return true;
        UserModel user = bloodRequest.getUserModel();


        if (LocalDate.now().isBefore(user.getNextRequestEligibleDate())) {
            if (bloodRequest.getType().equals("receive")) {
                bloodRequest.setStatus("approved");
                bloodRepository.save(bloodRequest);
                return true;
            } else {
                return false;
            }
        } else {
            if (!bloodRequest.getType().equals("donate")) {
                return false;
            }

            bloodRequest.setStatus("approved");
            bloodRepository.save(bloodRequest);
            return true;
        }
    }

    public void rejectBloodRequest(int id, String remark)
    {
        BloodBank bloodRequest = (bloodRepository.findById(id)).get();
        bloodRequest.setStatus("rejected");
        bloodRequest.setRemark(remark);

        bloodRepository.save(bloodRequest);
    }

    public int calculateTotalCoins()
    {
        List<BloodBank> bloodRequests = (List<BloodBank>)bloodRepository.findAll();
        int totalCoins = 0;

        for(BloodBank bloodRequest:bloodRequests)
        {
            if(bloodRequest.getStatus().equals("approved"))
            {
                totalCoins = totalCoins + bloodRequest.getCoins();
            }
        }
        return totalCoins;
    }

    public int getAllAgentCoins()
    {
        List<BloodBank> bloodRequests = (List<BloodBank>)bloodRepository.findAll();
        int allAgentCoins = 0;

        for(BloodBank bloodRequest:bloodRequests)
        {
            if (!(bloodRequest.getCreatedBy().equals("self")))
            {
                UserModel agent = (userRepository.findByUsername(bloodRequest.getCreatedBy())).get();
                allAgentCoins = allAgentCoins + (int)((agent.getCommision()/100.0) * bloodRequest.getCoins());
            }
        }
        return allAgentCoins;
    }

    public int getAllUsersPoints()
    {
        List<UserModel> allUsers = (List<UserModel>)userRepository.findAllByRole("user");
        int totalCoins = 0;
        for(UserModel user:allUsers)
        {
            totalCoins = totalCoins + user.getCoins();
        }

        return totalCoins;
    }


    public void updateCoins(int bloodRequestId)
    {
        BloodBank bloodRequest = (bloodRepository.findById(bloodRequestId)).get();
        UserModel admin = (userRepository.findByUsername("admin1")).get();

        if(bloodRequest.getCreatedBy().equals("self") && bloodRequest.getType().equals("donate"))
        {
           admin.setCoins(admin.getCoins() + bloodRequest.getCoins());
        }
        else if(!(bloodRequest.getCreatedBy().equals("self")) && bloodRequest.getType().equals("donate")){

            UserModel agent = (userRepository.findByUsername(bloodRequest.getCreatedBy())).get();
//            admin.setCoins(admin.getCoins() +(int) (bloodRequest.getCoins() - ((agent.getCommision()/100.0f)*bloodRequest.getCoins())));
            admin.setCoins(admin.getCoins() + (int)(bloodRequest.getCoins() - ((agent.getCommision() / 100.0f) * bloodRequest.getCoins())));

        }

        userRepository.save(admin);
    }
}
