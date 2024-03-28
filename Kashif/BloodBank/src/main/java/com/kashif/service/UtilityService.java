package com.kashif.service;

import com.kashif.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UtilityService {
    @Autowired
    UserRegistrationService userRegistrationService;
    @Autowired
    BloodRequestService bloodRequestService;

    private static final Set<String> allActiveUsers = new HashSet<>();


    public void updateActiveUsers(String username, String cause) {
        if (username != null) {
            if (cause.equalsIgnoreCase("add")) {
                allActiveUsers.add(username);
            }
            if (cause.equalsIgnoreCase("remove")) {
                allActiveUsers.remove(username);
            }
        }
    }

    public List<RegistrationDTO> getAllActiveUsers() {
        List<RegistrationDTO> list = new ArrayList<>();
        for (String username : allActiveUsers) {
            list.add(userRegistrationService.getUserByUsername(username).get());
        }
        return list;
    }


    public List<RegistrationDTO> sortUsers(String sortOption, List<RegistrationDTO> allUserList) {

        switch (sortOption) {
            case "name":
                return allUserList.stream().sorted(Comparator.comparing(i -> i.getName().toLowerCase()))
                        .collect(Collectors.toList());
            case "createdBy":
                return allUserList.stream().sorted(Comparator.comparing(i -> i.getCreatedBy().toLowerCase()))
                        .collect(Collectors.toList());
            case "bloodGroup":
                return allUserList.stream().sorted(Comparator.comparing(RegistrationDTO::getBloodGroup))
                        .collect(Collectors.toList());

        }
        return allUserList;

    }

    public List<RegistrationDTO> filterByAgent(List<RegistrationDTO> allUsers, String agent) {
        System.out.println(agent);
        return allUsers.stream()
                .filter(user -> user.getRole().equalsIgnoreCase("endUser")
                        && user.getCreatedBy().toLowerCase().startsWith(agent.toLowerCase()))
                .collect(Collectors.toList());

    }

    public List<RegistrationDTO> filterByNonLoggedInUser(List<RegistrationDTO> allUsers) {
        return allUsers.stream()
                .filter(RegistrationDTO::isNewUser)
                .collect(Collectors.toList());

    }


    public List<RegistrationDTO> filterByCurrentAgent(List<RegistrationDTO> allUsers, RegistrationDTO dto) {
        return allUsers.stream().filter(u -> u.getCreatedBy().equalsIgnoreCase(dto.getUsername())).collect(Collectors.toList());
    }

    public List<RegistrationDTO> filterByUsername(String username, List<RegistrationDTO> allUsers) {
        return allUsers.stream().filter(user -> user.getUsername().toLowerCase().startsWith(username.toLowerCase())).collect(Collectors.toList());
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

    //For blood request date filter
    public List<HashMap<String, Object>> filterBloodRequestByDate(String startDate, String endDate, List<HashMap<String, Object>> list) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("date hu mai-----");
        try {
            Date sdate = dateFormat.parse(startDate);
            Date edate = dateFormat.parse(endDate);

            // Filter the list based on createdAt field
            return list.stream()
                    .filter(user -> {
                        Date createdAt = (Date) user.get("createdAt");
                        return createdAt.compareTo(sdate) >= 0 && createdAt.compareTo(edate) <= 0;
                    })
                    .collect(Collectors.toList());
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        return list;
    }

    // filter by status, username and agent
    public List<HashMap<String, Object>> filterByQueryBloodRequest(List<HashMap<String, Object>> list, String filter, String input) {

        switch (filter) {
            case "byStatus":
                return list.stream().filter(i -> i.get("status").equals(input)).collect(Collectors.toList());
            case "byAgent":
                return list.stream().filter(i -> i.get("userCreatedBy").toString().startsWith(input)).collect(Collectors.toList());
            case "byUsername":
                return list.stream().filter(i -> i.get("username").toString().startsWith(input)).collect(Collectors.toList());
        }
        return list;
    }


    public String eligibleToDonate(String username) {

        // Here I have checked whether the person has requested the blood less than 3 months or not.
        Optional<Object> updatedAtAccepted = bloodRequestService.getBloodBankList()
                .stream().filter(i -> i.get("username").equals(username)
                        && i.get("status").equals("accepted")
                        && i.get("type").equals("donor"))
                .map(i -> i.get("updatedAt")).findFirst();

//        List<HashMap<String, Object>> maps = bloodRequestService.requestBloodStatus(username, "EndUser");
//        Optional<Object> updatedAtAccepted = maps.stream()
//                .filter(i -> i.get("status").equals("accepted") && i.get("type").equals("donor"))
//                .map(i -> i.get("updatedAt")).findFirst();

        if (updatedAtAccepted.isPresent()) {
            Date acceptedDate = (Date) updatedAtAccepted.get();
            LocalDate acceptedLocalDate = acceptedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();

            // Calculate the duration between accepted date and current date
            Duration duration = Duration.between(acceptedLocalDate.atStartOfDay(), currentDate.atStartOfDay());
            long daysRemaining = (3 * 30) - duration.toDays();
            if (daysRemaining > 0) {
                return "You are not eligible. You have still have " + daysRemaining + " days remaining to complete 3 months.";
            }
        }
        return "ok";
    }

    //Get Coins for EndUser(Donate Coin, Receive Coin, Balance COin)
    public long getEndUserCoins(String endUsername) {
        return bloodRequestService.requestBloodStatus(endUsername, "EndUser")
                .stream().filter(i -> i.get("type").equals("donor"))
                .filter(i -> i.get("status").equals("accepted"))
                .mapToLong(coin -> (long) coin.get("coins")).sum();
    }

    //Get coins for agent and admin :Coins Report
    public ArrayList<HashMap<String, Object>> getAgentAndAdminCoins(String agentUsername, String role) {
        List<RegistrationDTO> userList;
        if (role.equalsIgnoreCase("admin")) {
            userList = userRegistrationService.getAllUsers().stream()
                    .filter(i -> !i.getCreatedBy().equalsIgnoreCase("admin"))
                    .collect(Collectors.toList());
        } else {
            userList = userRegistrationService.getAllUsers().stream()
                    .filter(i -> i.getCreatedBy().equalsIgnoreCase(agentUsername))
                    .collect(Collectors.toList());
        }
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (RegistrationDTO user : userList) {
            HashMap<String, Object> res = new HashMap<>();
            res.put("username", user.getUsername());
            res.put("bloodGroup", user.getBloodGroup());
            double endUserCoins = getEndUserCoins(user.getUsername());
            res.put("endUserCoins", endUserCoins);
            double commission;
            if (role.equalsIgnoreCase("admin")) {
                commission = userRegistrationService.getAllUsers()
                        .stream()
                        .filter(i -> i.getUsername().equalsIgnoreCase(user.getCreatedBy()))
                        .mapToDouble(RegistrationDTO::getCommission)
                        .findFirst()
                        .orElse(0); // If not found, default to 0

            } else {
                commission = userRegistrationService.getAllUsers()
                        .stream()
                        .filter(i -> i.getUsername().equalsIgnoreCase(agentUsername))
                        .mapToDouble(RegistrationDTO::getCommission)
                        .findFirst()
                        .orElse(0); // If not found, default to 0
            }

            res.put("commission", commission);
            double com = commission / 100;
            res.put("agentCoins", endUserCoins * com);
            res.put("adminCoins", endUserCoins * (1 - com));
            if (endUserCoins != 0)
                list.add(res);
        }
        return list;
    }

}
