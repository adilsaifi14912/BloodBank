package com.insightgeeks.bloodbank.service;

import com.insightgeeks.bloodbank.dto.BloodRequestSummaryDTO;
import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.entities.BloodBank;
import com.insightgeeks.bloodbank.entities.UserModel;
import com.insightgeeks.bloodbank.repository.BloodRepository;
import com.insightgeeks.bloodbank.repository.UserRepository;
import com.insightgeeks.bloodbank.util.UserComparators;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class AgentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BloodRepository bloodRepository;

    @Autowired
    AdminService adminService;

    private static final Map<String, SignupDTO> allAgentCreatedActiveUsers = new HashMap<>();



    public SignupDTO convertToSignupDTO(UserModel userModel)
    {
        return modelMapper.map(userModel, SignupDTO.class);
    }


    public List<SignupDTO> convertList(List<UserModel> users)
    {
        List<SignupDTO> convertedList = new ArrayList<>();
        for(UserModel user:users)
        {
            convertedList.add(convertToSignupDTO(user));
        }

        return convertedList;
    }


    public List<SignupDTO> getAgentCreatedUsers(String agentName)
    {
        return convertList(userRepository.findByCreatedBy(agentName));
    }

    public List<SignupDTO> sortUsers(String by, String agentName)
    {
        List<SignupDTO> users = getAgentCreatedUsers(agentName);
        switch (by)
        {
            case "nameAsc":
                Collections.sort(users, new UserComparators.UsernameComparator(true));

                break;

            case "createdByAsc":
                Collections.sort(users, new UserComparators.CreatedByComparator(true));
                break;

            case "bloodGroupAsc":
                Collections.sort(users, new UserComparators.BloodGroupComparator(true));
                break;

            case "nameDesc":
                Collections.sort(users, new UserComparators.UsernameComparator(false));
                break;

            case "createdByDesc":
                Collections.sort(users, new UserComparators.CreatedByComparator(false));
                break;

            case "bloodGroupDesc":
                Collections.sort(users, new UserComparators.BloodGroupComparator(false));
                break;
        }
        return users;
    }
    public List<SignupDTO> filterAgentCreatedUsers(String by, String filterValue, String agentName) {
        List<SignupDTO> filteredResults = new ArrayList<>();
        List<SignupDTO> allAgentCreatedusers = getAgentCreatedUsers(agentName);

        switch (by) {
            case "name":
                allAgentCreatedusers.stream()
                        .filter(usr -> usr.getUsername().equals(filterValue))
                        .forEach(filteredResults::add);


                return filteredResults;

            case "agent":
                return allAgentCreatedusers;

            case "date":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate filteredValueConverted = LocalDate.parse(filterValue, formatter);
                allAgentCreatedusers.stream()
                        .filter(usr -> usr.getCreatedOn().isEqual(filteredValueConverted))
                        .forEach(filteredResults::add);


                return filteredResults;

            case "loggedInUsers":
                return adminService.notLoggedInUser(allAgentCreatedusers);
        }
        return new ArrayList<>();
    }
//    public Map<String, SignupDTO> getAllActiveUsers() {
//        return allAgentCreatedActiveUsers;
//    }
//
//
//    public List<SignupDTO> notLoggedInUser(List<SignupDTO> allUserList){
//        ArrayList<SignupDTO> list = new ArrayList<>();
//        for(SignupDTO user : allUserList){
//            if(!getAllActiveUsers().containsKey(user.getUsername())){
//                list.add(user);
//            }
//        }
//        return list;
//    }
//
//
//    public void updateActiveUsers(SignupDTO userRegistration, String cause){
//        if(userRegistration!=null){
//            if(cause.equalsIgnoreCase("add")){
//                allAgentCreatedActiveUsers.put(userRegistration.getUsername(), userRegistration);
//            }
//            if(cause.equalsIgnoreCase("remove")) {
//                allAgentCreatedActiveUsers.remove(userRegistration.getUsername());
//            }
//        }
//    }


        public int calculateTotalPointsForAgentCreatedUsers(SignupDTO user)
        {
            List<BloodBank> bloodRequests = bloodRepository.findAllBloodRequestsCreatedByAgent(user.getUsername());
            int totalPoints = 0;
            for(BloodBank bloodRequest:bloodRequests)
            {
                if(bloodRequest.getStatus().equals("approved"))
                { totalPoints = totalPoints + bloodRequest.getCoins();}
            }

            return totalPoints;
        }

            public int calculateCommissionEarned(SignupDTO user) {
            float commissionPercentage = user.getCommision();
            return (int)((commissionPercentage/100) * calculateTotalPointsForAgentCreatedUsers(user));
        }

    public int calculateUsersActualPoints(SignupDTO user)
    {
//        return (calculateTotalPointsForAgentCreatedUsers(user) - calculateCommissionEarned(user));
            List<UserModel> agentUsers = userRepository.findByCreatedBy(user.getUsername());
            int totalPoints = 0;
            for (UserModel agentUser : agentUsers) {
                totalPoints = totalPoints + agentUser.getCoins();            }

            return totalPoints;
    }


    public List<BloodRequestSummaryDTO> getBloodRequestSummaryForAgent(SignupDTO user) {

        List<BloodBank> bloodRequests = bloodRepository.findAllBloodRequestsCreatedByAgent(user.getUsername());


        Map<String, BloodRequestSummaryDTO> summaryMap = new HashMap<>();
        for (BloodBank request : bloodRequests) {
            String bloodGroup = request.getBloodGroup();
            int coins = request.getCoins();
            int commission = calculateCommission(request,user);
            if(request.getStatus().equals("approved")) {
                BloodRequestSummaryDTO summary = summaryMap.getOrDefault(bloodGroup, new BloodRequestSummaryDTO());
                summary.setBloodGroup(bloodGroup);
                summary.setTotalCoins(summary.getTotalCoins() + coins);
                summary.setCommission(summary.getCommission() + commission);
                summaryMap.put(bloodGroup, summary);
            }
        }

        return new ArrayList<>(summaryMap.values());
    }



    public int calculateCommission(BloodBank request, SignupDTO user) {
        float commissionRate = user.getCommision();
        float bloodRequestCoins = request.getCoins();
        float commission = (commissionRate / 100.0f) * bloodRequestCoins;
        
        return (int)commission;
    }



    public void updateCoins(int bloodRequestId)
    {

        BloodBank bloodRequest = (bloodRepository.findById(bloodRequestId)).get();

        if(!bloodRequest.getCreatedBy().equals("self")) {

            UserModel agent = (userRepository.findByUsername(bloodRequest.getCreatedBy())).get();
            float commission = (agent.getCommision() / 100.0f);
            int updatedCoins = agent.getCoins() + (int) (commission * bloodRequest.getCoins());
            agent.setCoins(updatedCoins);
            userRepository.save(agent);

        }
    }



}

