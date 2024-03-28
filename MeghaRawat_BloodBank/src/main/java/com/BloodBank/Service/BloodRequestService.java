package com.BloodBank.Service;

import com.BloodBank.Model.BloodModel;
import com.BloodBank.Model.UserModel;
import com.BloodBank.Repository.BloodRepository;
import com.BloodBank.Repository.UserRepository;
import com.BloodBank.dto.BloodRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BloodRequestService {
    @Autowired
    private BloodRepository bloodRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BloodStockService bloodStockService;
    @Autowired
    private HttpSession session;

    public void addRequest(BloodRequestDTO bloodRequestDTO)
    {
        BloodModel bloodModel=new BloodModel();
        bloodModel.setUserId(bloodRequestDTO.getUserId());
        bloodModel.setBloodGroup(bloodRequestDTO.getBloodGroup());
        bloodModel.setType(bloodRequestDTO.getType());
        bloodModel.setQuantity(bloodRequestDTO.getQuantity());
        bloodModel.setCreatedAt(LocalDateTime.now());
        bloodModel.setUpdatedAt(LocalDateTime.now());
        bloodModel.setCreatedBy("self");
        bloodModel.setUpdatedBy("admin");
        bloodModel.setStatus("Pending");
        bloodModel.setUsername(((UserModel)session.getAttribute("userLoggedIn")).getUsername());
        bloodModel.setAgent(((UserModel)session.getAttribute("userLoggedIn")).getCreatedBy());
        if(bloodModel.getAgent().equals("auto"))
            bloodModel.setAgent("-");
        bloodModel.setDob(((UserModel)session.getAttribute("userLoggedIn")).getDob());
        bloodModel.setRemark("None");
        if(bloodModel.getRemark().equals("null"))
            bloodModel.setRemark("None");
        bloodRepository.save(bloodModel);
    }

    public List<HashMap<String, Object>> getAllRequests(){
        List<BloodModel> list = bloodRepository.findAll();
        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        for (BloodModel entity : list) {
            HashMap<String, Object> bloodResult = new HashMap<>();

            bloodResult.put("id", entity.getRequestId());
            bloodResult.put("username", entity.getUsername());
            bloodResult.put("createdAt", entity.getCreatedAt());
            bloodResult.put("createdBy", entity.getCreatedBy());
            bloodResult.put("status", entity.getStatus());
            bloodResult.put("agent", entity.getAgent());
            bloodResult.put("type", entity.getType());
            bloodResult.put("bloodGroup", entity.getBloodGroup());
            bloodResult.put("dob", entity.getDob());
            bloodResult.put("quantity", entity.getQuantity());
            bloodResult.put("remark",entity.getRemark());
            // Age Calculation
            Date birthDate = entity.getDob();
            LocalDate now = LocalDate.now();
            LocalDate birthLocalDate = birthDate.toLocalDate();
            int age = now.getYear() - birthLocalDate.getYear();
            if (now.getMonthValue() < birthLocalDate.getMonthValue() ||
                    (now.getMonthValue() == birthLocalDate.getMonthValue() &&
                            now.getDayOfMonth() < birthLocalDate.getDayOfMonth())) {
                age--;
            }
            bloodResult.put("age", age);

            hashMapList.add(bloodResult);
        }
        return hashMapList;
    }

    public List<HashMap<String, Object>> getFilteredAllRequests(String startDate, String endDate, String agentUsername, String endUserUsername) {
        List<BloodModel> filteredList = bloodRepository.findAll();
        List<HashMap<String, Object>> hashMapList = new ArrayList<>();

        for (BloodModel entity : filteredList) {
            if ((startDate == null || startDate.isEmpty() || entity.getCreatedAt().toLocalDate().compareTo(LocalDate.parse(startDate)) >= 0) &&
                    (endDate == null || endDate.isEmpty() || entity.getCreatedAt().toLocalDate().compareTo(LocalDate.parse(endDate)) <= 0) &&
                    (agentUsername == null || agentUsername.isEmpty() || entity.getUserId().getCreatedBy().equals(agentUsername)) &&
                    (endUserUsername == null || endUserUsername.isEmpty() || entity.getUserId().getUsername().equals(endUserUsername))) {

                HashMap<String, Object> bloodResult = new HashMap<>();
                bloodResult.put("id", entity.getRequestId());
                bloodResult.put("username", entity.getUserId().getUsername());
                bloodResult.put("createdAt", entity.getCreatedAt());
                bloodResult.put("createdBy", entity.getCreatedBy());
                bloodResult.put("status", entity.getStatus());
                bloodResult.put("agent", entity.getUserId().getCreatedBy());
                bloodResult.put("type", entity.getType());
                bloodResult.put("bloodGroup", entity.getBloodGroup());
                bloodResult.put("dob", entity.getUserId().getDob());
                bloodResult.put("quantity", entity.getQuantity());
                bloodResult.put("remarks", entity.getRemark());
                // Age Calculation
                Date birthDate = entity.getUserId().getDob();
                LocalDate now = LocalDate.now();
                LocalDate birthLocalDate = birthDate.toLocalDate();
                int age = now.getYear() - birthLocalDate.getYear();
                if (now.getMonthValue() < birthLocalDate.getMonthValue() ||
                        (now.getMonthValue() == birthLocalDate.getMonthValue() &&
                                now.getDayOfMonth() < birthLocalDate.getDayOfMonth())) {
                    age--;
                }
                bloodResult.put("age", age);
                hashMapList.add(bloodResult);
            }
        }
        return hashMapList;
    }

    public Optional<LocalDateTime> getLastDonationDate(String username)
    {
        return bloodRepository.findTopByUsernameOrderByUpdatedAtDesc(username)
                .map(BloodModel::getUpdatedAt);
    }

    @Transactional
    public void addRemarkToRequest(Long requestId,String remark) {
        bloodRepository.addRemark(requestId,remark);
    }

    @Transactional
    public void updateStatus(Long requestId, String status) {
        bloodRepository.updateStatus(requestId, status);
    }

    @Transactional
    public void updateTime(Long requestId) {
        bloodRepository.updateTime(requestId, LocalDateTime.now());
    }

    public List<HashMap<String, Object>> endUserRequestStatus(String username)
    {
        List<BloodModel> list = bloodRepository.findAll();
        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        for (BloodModel entity : list) {
            HashMap<String, Object> bloodResult = new HashMap<>();
            if(username.equals(entity.getUsername()))
            {
                bloodResult.put("id", entity.getUserId());
                bloodResult.put("username", entity.getUsername());
                bloodResult.put("createdAt", entity.getCreatedAt());
                bloodResult.put("status", entity.getStatus());
                bloodResult.put("agent", entity.getAgent());
                bloodResult.put("type", entity.getType());
                bloodResult.put("bloodGroup", entity.getBloodGroup());
                bloodResult.put("dob", entity.getDob());
                bloodResult.put("quantity", entity.getQuantity());
                bloodResult.put("remark",entity.getRemark());
            }
            else
                continue;

            hashMapList.add(bloodResult);
        }
        return hashMapList;
    }

    public List<HashMap<String, Object>> filteredEURequests(String startDate, String endDate, String username) {
        List<BloodModel> filteredList = bloodRepository.findAll();
        // Filter by start date
        if (startDate != null && !startDate.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(request -> request.getCreatedAt().toLocalDate().compareTo(LocalDate.parse(startDate)) >= 0)
                    .collect(Collectors.toList());
        }
        // Filter by end date
        if (endDate != null && !endDate.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(request -> request.getCreatedAt().toLocalDate().compareTo(LocalDate.parse(endDate)) <= 0)
                    .collect(Collectors.toList());
        }
        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        for (BloodModel entity : filteredList) {
            HashMap<String, Object> bloodResult = new HashMap<>();
            if(username.equals(entity.getUserId().getUsername()))
            {
                bloodResult.put("id", entity.getRequestId());
                bloodResult.put("username", entity.getUserId().getUsername());
                bloodResult.put("createdAt", entity.getCreatedAt());
                bloodResult.put("status", entity.getStatus());
                bloodResult.put("user_createdBy", entity.getCreatedBy());
                bloodResult.put("type", entity.getType());
                bloodResult.put("bloodGroup", entity.getBloodGroup());
                bloodResult.put("quantity", entity.getQuantity());
                bloodResult.put("remark",entity.getRemark());
            }
            else
                continue;
            hashMapList.add(bloodResult);
        }
        return hashMapList;
    }

    public List<HashMap<String, Object>> agentRequestStatus(String agentUsername)
    {
        List<BloodModel> list = bloodRepository.findAll();
        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        for (BloodModel entity : list) {
            HashMap<String, Object> bloodResult = new HashMap<>();
            if(agentUsername.equals(entity.getAgent()))
            {
                bloodResult.put("id", entity.getUserId());
                bloodResult.put("username", entity.getUsername());
                bloodResult.put("createdAt", entity.getCreatedAt());
                bloodResult.put("status", entity.getStatus());
                bloodResult.put("agent", entity.getAgent());
                bloodResult.put("type", entity.getType());
                bloodResult.put("bloodGroup", entity.getBloodGroup());
                bloodResult.put("dob", entity.getDob());
                bloodResult.put("quantity", entity.getQuantity());
                bloodResult.put("remark",entity.getRemark());
            }
            else
                continue;

            hashMapList.add(bloodResult);
        }
        return hashMapList;

    }

    public List<HashMap<String, Object>> filteredAgentRequests(String startDate, String endDate,String endUsername, String username) {
        List<BloodModel> filteredList = bloodRepository.findAll();
        // Filter by start date
        if (startDate != null && !startDate.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(request -> request.getCreatedAt().toLocalDate().compareTo(LocalDate.parse(startDate)) >= 0)
                    .collect(Collectors.toList());
        }
        // Filter by end date
        if (endDate != null && !endDate.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(request -> request.getCreatedAt().toLocalDate().compareTo(LocalDate.parse(endDate)) <= 0)
                    .collect(Collectors.toList());
        }

        // Filter by end user username
        if (endUsername != null && !endUsername.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(request -> request.getUserId().getUsername().equals(endUsername))
                    .collect(Collectors.toList());
        }

        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        for (BloodModel entity : filteredList) {
            HashMap<String, Object> bloodResult = new HashMap<>();
            if(username.equals(entity.getUserId().getUsername()))
            {
                bloodResult.put("id", entity.getRequestId());
                bloodResult.put("username", entity.getUserId().getUsername());
                bloodResult.put("createdAt", entity.getCreatedAt());
                bloodResult.put("status", entity.getStatus());
                bloodResult.put("user_createdBy", entity.getCreatedBy());
                bloodResult.put("type", entity.getType());
                bloodResult.put("bloodGroup", entity.getBloodGroup());
                bloodResult.put("quantity", entity.getQuantity());
                bloodResult.put("remark",entity.getRemark());
            }
            else
                continue;
            hashMapList.add(bloodResult);
        }
        return hashMapList;
    }
    public List<Map<String, Object>> getAdminBloodReport() {
        List<Map<String, Object>> requestReport = new ArrayList<>();
        List<String> bloodGroups = bloodStockService.getBloodGroups();

        for (String bloodGroup : bloodGroups) {
            int coinPerUnit = bloodStockService.getCoinPerUnitByBloodGroup(bloodGroup);
            int units = bloodStockService.getUnitsByBloodGroup(bloodGroup);
            int coinValue = coinPerUnit * units;
            int acceptCount = bloodRepository.countByBloodGroupAndStatus(bloodGroup, "Approved");
            int declineCount = bloodRepository.countByBloodGroupAndStatus(bloodGroup, "Rejected");

            Map<String, Object> reportMap = new HashMap<>();
            reportMap.put("bloodGroup", bloodGroup);
            reportMap.put("coinValue", coinValue);
            reportMap.put("units", units);
            reportMap.put("acceptCount", acceptCount);
            reportMap.put("declineCount", declineCount);

            requestReport.add(reportMap);
        }

        return requestReport;
    }

    public List<Map<String, Object>> getAdminCoinReport() {
        List<Map<String, Object>> userCoinReport = new ArrayList<>();

        // Retrieve all requests
        List<BloodModel> allRequests = bloodRepository.findAll();

        // Iterate over each request
        for (BloodModel request : allRequests) {
            // Get request details
            String username = request.getUsername();
            String agent = request.getAgent();
            String bloodGroup = request.getBloodGroup();
            int quantity = request.getQuantity();
            String status = request.getStatus();

            // Get coin details
            int coinPerUnit = bloodStockService.getCoinPerUnitByBloodGroup(bloodGroup);
            int totalCoins = coinPerUnit * quantity;
            int userCoins = 0;
            int agentCoins = 0;
            int adminCoins = 0;

            // Check if the request is approved
            if (status.equals("Approved")) {
                userCoins=totalCoins;
                // Calculate user coins based on whether the user is created by an agent or not
                if (!agent.equals("-") ){ // If user is created by an agent
                    agentCoins = (int) (userCoins * 0.1); // User gets 10% of coins
                    adminCoins = userCoins - agentCoins; // Admin gets the remaining coins
                } else { // If user is not created by an agent
                    adminCoins = userCoins; // Admin gets all the coins
                }
            }

            // Construct the report map
            Map<String, Object> coinMap = new HashMap<>();
            coinMap.put("username", username);
            coinMap.put("bloodGroup", bloodGroup);
            coinMap.put("userCoins", userCoins);
            coinMap.put("agentCoins", agentCoins);
            coinMap.put("adminCoins", adminCoins);
            coinMap.put("status",request.getStatus());

            // Add the report map to the list
            userCoinReport.add(coinMap);
        }

        return userCoinReport;
    }


    public List<Map<String, Object>> getAgentBloodReport(String agentUsername) {
        List<Map<String, Object>> requestReport = new ArrayList<>();
        List<String> bloodGroups = bloodStockService.getBloodGroups();

        for (String bloodGroup : bloodGroups) {
            int totalUnits = 0;
            int totalCoinValue = 0;
            int totalAcceptCount = 0;
            int totalDeclineCount = 0;

            List<BloodModel> requestsList = bloodRepository.findByAgentAndBloodGroup(agentUsername, bloodGroup);

            for (BloodModel request : requestsList) {
                int units = request.getQuantity();
                int coinPerUnit = bloodStockService.getCoinPerUnitByBloodGroup(bloodGroup);

                if (request.getStatus().equals("Approved")) {
                    int coinValue = coinPerUnit * units;
                    totalUnits += units;
                    totalCoinValue += coinValue;
                    totalAcceptCount++;
                } else if (request.getStatus().equals("Rejected")) {
                    totalDeclineCount++;
                }
            }

            Map<String, Object> reportMap = new HashMap<>();
            reportMap.put("bloodGroup", bloodGroup);
            reportMap.put("coinValue", totalCoinValue);
            reportMap.put("units", totalUnits);
            reportMap.put("acceptCount", totalAcceptCount);
            reportMap.put("declineCount", totalDeclineCount);

            requestReport.add(reportMap);
        }

        return requestReport;
    }

    public List<Map<String, Object>> getAgentCoinReport(String agentUsername) {
        List<Map<String, Object>> agentCoinReport = new ArrayList<>();

        // Retrieve all requests for the specified agent
        List<BloodModel> agentRequests = bloodRepository.findByAgent(agentUsername);

        // Iterate over each request
        for (BloodModel request : agentRequests) {
            // Get request details
            String username = request.getUsername();
            String bloodGroup = request.getBloodGroup();
            int quantity = request.getQuantity();
            String status = request.getStatus();

            // Get coin details
            int coinPerUnit = bloodStockService.getCoinPerUnitByBloodGroup(bloodGroup);
            int totalCoins = coinPerUnit * quantity;
            int userCoins = 0;
            int agentCoins = 0;
            int adminCoins = 0;

            // Check if the request is approved and created by the agent
            if (status.equals("Approved") ) {
                userCoins=totalCoins;
                // Calculate agent coins as 10% of total coins
                agentCoins = (int) (userCoins * 0.1);
                // Admin gets the remaining coins
                adminCoins = userCoins - agentCoins;
            }
            // Construct the report map
            Map<String, Object> coinMap = new HashMap<>();
            coinMap.put("username", username);
            coinMap.put("bloodGroup", bloodGroup);
            coinMap.put("userCoins", userCoins);
            coinMap.put("agentCoins", agentCoins);
            coinMap.put("adminCoins", adminCoins);
            coinMap.put("status",request.getStatus());

            // Add the report map to the list
            agentCoinReport.add(coinMap);
        }

        return agentCoinReport;
    }

    public List<Map<String, Object>> getAgentCoinsByRate(String agentUsername) {
        List<Map<String, Object>> agentCoinsByRate = new ArrayList<>();

        // Retrieve all blood groups
        List<String> bloodGroups = bloodStockService.getBloodGroups();

        // Iterate over each blood group
        for (String bloodGroup : bloodGroups) {
            // Retrieve all requests for the specified agent and blood group
            List<BloodModel> agentRequests = bloodRepository.findByAgentAndBloodGroup(agentUsername, bloodGroup);

            // Calculate coins for the current blood group
            int totalUserCoins = 0;
            int totalAgentCoins = 0;
            int totalAdminCoins = 0;

            // Iterate over each request for the current blood group
            for (BloodModel request : agentRequests) {
                if (request.getStatus().equals("Approved")) { // Only process approved requests
                    int quantity = request.getQuantity();
                    int coinPerUnit = bloodStockService.getCoinPerUnitByBloodGroup(bloodGroup);
                    int totalCoins = coinPerUnit * quantity;

                    // Calculate coins distribution
                    totalUserCoins += totalCoins;
                    totalAgentCoins += (int) (totalCoins * 0.1);
                    totalAdminCoins += totalCoins - totalAgentCoins;
                }
            }

            // Construct the report map for the current blood group
            Map<String, Object> coinMap = new HashMap<>();
            coinMap.put("bloodGroup", bloodGroup);
            coinMap.put("userCoins", totalUserCoins);
            coinMap.put("agentCoins", totalAgentCoins);
            coinMap.put("adminCoins", totalAdminCoins);

            // Add the report map to the list
            agentCoinsByRate.add(coinMap);
        }

        return agentCoinsByRate;
    }
}
