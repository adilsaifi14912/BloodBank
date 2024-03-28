package com.example.Charul_BloodBank.service;

import com.example.Charul_BloodBank.dto.BloodInventoryDTO;
import com.example.Charul_BloodBank.model.BloodInventory;
import com.example.Charul_BloodBank.repository.BloodInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BloodInventoryService {
    @Autowired
    private BloodInventoryRepository bloodInventoryRepository;
    @Autowired
    private StockService stockService;

    public void addUser(BloodInventoryDTO bloodInventoryDTO, String created_by){
        BloodInventory bloodInventory=new BloodInventory();

        bloodInventory.setBloodGroup(bloodInventoryDTO.getBloodGroup());
        bloodInventory.setCreatedAt(LocalDateTime.now());
        bloodInventory.setCreatedBy(created_by);
        bloodInventory.setUpdatedAt(LocalDateTime.now());
        bloodInventory.setUpdatedBy("-");
        bloodInventory.setQuantity(bloodInventoryDTO.getQuantity());
        bloodInventory.setStatus("pending");
        bloodInventory.setType(bloodInventoryDTO.getType());
        bloodInventory.setUserid(bloodInventoryDTO.getUserid());
        bloodInventoryRepository.save(bloodInventory);
    }

    public List<HashMap<String, Object>> getBloodBankList(){
        List<BloodInventory> list = bloodInventoryRepository.findAll();
        return convertToHashMapList(list);
    }

    public List<HashMap<String, Object>> getFilteredBloodBankList(String startDate, String endDate, String agentUsername, String endUserUsername) {
        List<BloodInventory> filteredList = bloodInventoryRepository.findAll();
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
        // Filter by agent username
        if (agentUsername != null && !agentUsername.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(request -> request.getUserid().getCreatedBy().equals(agentUsername))
                    .collect(Collectors.toList());
        }
        // Filter by end user username
        if (endUserUsername != null && !endUserUsername.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(request -> request.getUserid().getUsername().equals(endUserUsername))
                    .collect(Collectors.toList());
        }
        return convertToHashMapList(filteredList);
    }

    private List<HashMap<String, Object>> convertToHashMapList(List<BloodInventory> list) {
        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        for (BloodInventory entity : list) {
            HashMap<String, Object> bloodResult = new HashMap<>();
            bloodResult.put("id", entity.getId());
            bloodResult.put("username", entity.getUserid().getUsername());
            bloodResult.put("createdAt", entity.getCreatedAt());
            bloodResult.put("createdBy", entity.getCreatedBy());
            bloodResult.put("status", entity.getStatus());
            bloodResult.put("agent", entity.getUserid().getCreatedBy());
            bloodResult.put("type", entity.getType());
            bloodResult.put("bloodGroup", entity.getBloodGroup());
            bloodResult.put("dob", entity.getUserid().getDob());
            bloodResult.put("quantity", entity.getQuantity());
            bloodResult.put("remarks", entity.getRemarks());
            // Age Calculation
            Date birthDate = entity.getUserid().getDob();
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
        // Reverse the list
        Collections.reverse(hashMapList);
        return hashMapList;
    }

    public void declineRequestWithRemarks(Long requestId, String remarks) {
        BloodInventory request = bloodInventoryRepository.findById(requestId)
                .orElseThrow(() -> new NoSuchElementException("Request not found"));
        request.setStatus("Decline");
        request.setRemarks(remarks);
        bloodInventoryRepository.save(request);
    }
    public void acceptRequest(Long requestId, int unitsDonated) {
        BloodInventory request = bloodInventoryRepository.findById(requestId)
                .orElseThrow(() -> new NoSuchElementException("Request not found"));
        request.setStatus("Accept");
        request.setUpdatedAt(LocalDateTime.now());
        bloodInventoryRepository.save(request);
        stockService.updateStockWithDonation(request.getBloodGroup(), unitsDonated, request.getType(), "Accept");
    }

    public Optional<LocalDateTime> getLastDonationDate(String username) {
        return bloodInventoryRepository.findTopByUseridUsernameAndTypeOrderByUpdatedAtDesc(username, "donate")
                .map(BloodInventory::getUpdatedAt);
    }
    public List<Map<String, Object>> generateRequestReport() {
        List<Map<String, Object>> requestReport = new ArrayList<>();
        List<String> bloodGroups = stockService.getBloodGroups();

        for (String bloodGroup : bloodGroups) {
            int coinPerUnit = stockService.getCoinPerUnitByBloodGroup(bloodGroup);
            int units = stockService.getUnitsByBloodGroup(bloodGroup);
            int coinValue = coinPerUnit * units;
            int acceptCount = bloodInventoryRepository.countByBloodGroupAndStatus(bloodGroup, "Accept");
            int declineCount = bloodInventoryRepository.countByBloodGroupAndStatus(bloodGroup, "Decline");

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

    public List<HashMap<String, Object>> agentRequests(String agentUsername)
    {
        List<BloodInventory> list = bloodInventoryRepository.findAll();
        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        for (BloodInventory entity : list) {
            HashMap<String, Object> bloodResult = new HashMap<>();
            if(agentUsername.equals(entity.getCreatedBy()))
            {
                bloodResult.put("id", entity.getId());
                bloodResult.put("username", entity.getUserid().getUsername());
                bloodResult.put("createdAt", entity.getCreatedAt());
                bloodResult.put("status", entity.getStatus());
                bloodResult.put("user_createdBy", entity.getCreatedBy());
                bloodResult.put("type", entity.getType());
                bloodResult.put("bloodGroup", entity.getBloodGroup());
                bloodResult.put("quantity", entity.getQuantity());
                bloodResult.put("remark",entity.getRemarks());
            }
            else
                continue;
            hashMapList.add(bloodResult);
        }
        return hashMapList;
    }

    public List<HashMap<String, Object>> filteredAgentRequests(String startDate, String endDate, String endUserUsername, String agentUsername) {
        List<BloodInventory> filteredList = bloodInventoryRepository.findAll();
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
        if (endUserUsername != null && !endUserUsername.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(request -> request.getUserid().getUsername().equals(endUserUsername))
                    .collect(Collectors.toList());
        }
        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        for (BloodInventory entity : filteredList) {
            HashMap<String, Object> bloodResult = new HashMap<>();
            if(agentUsername.equals(entity.getCreatedBy()))
            {
                bloodResult.put("id", entity.getId());
                bloodResult.put("username", entity.getUserid().getUsername());
                bloodResult.put("createdAt", entity.getCreatedAt());
                bloodResult.put("status", entity.getStatus());
                bloodResult.put("user_createdBy", entity.getCreatedBy());
                bloodResult.put("type", entity.getType());
                bloodResult.put("bloodGroup", entity.getBloodGroup());
                bloodResult.put("quantity", entity.getQuantity());
                bloodResult.put("remark",entity.getRemarks());
            }
            else
                continue;
            hashMapList.add(bloodResult);
        }
        return hashMapList;
    }

    public List<HashMap<String, Object>> euRequests(String username) {
        List<BloodInventory> list = bloodInventoryRepository.findAll();
        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        for (BloodInventory entity : list) {
            HashMap<String, Object> bloodResult = new HashMap<>();
            if(username.equals(entity.getUserid().getUsername()))
            {
                bloodResult.put("id", entity.getId());
                bloodResult.put("username", entity.getUserid().getUsername());
                bloodResult.put("createdAt", entity.getCreatedAt());
                bloodResult.put("status", entity.getStatus());
                bloodResult.put("user_createdBy", entity.getCreatedBy());
                bloodResult.put("type", entity.getType());
                bloodResult.put("bloodGroup", entity.getBloodGroup());
                bloodResult.put("quantity", entity.getQuantity());
                bloodResult.put("remark",entity.getRemarks());
            }
            else
                continue;
            hashMapList.add(bloodResult);
        }
        return hashMapList;
    }

    public List<HashMap<String, Object>> filteredEURequests(String startDate, String endDate, String username) {
        List<BloodInventory> filteredList = bloodInventoryRepository.findAll();
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
        for (BloodInventory entity : filteredList) {
            HashMap<String, Object> bloodResult = new HashMap<>();
            if(username.equals(entity.getUserid().getUsername()))
            {
                bloodResult.put("id", entity.getId());
                bloodResult.put("username", entity.getUserid().getUsername());
                bloodResult.put("createdAt", entity.getCreatedAt());
                bloodResult.put("status", entity.getStatus());
                bloodResult.put("user_createdBy", entity.getCreatedBy());
                bloodResult.put("type", entity.getType());
                bloodResult.put("bloodGroup", entity.getBloodGroup());
                bloodResult.put("quantity", entity.getQuantity());
                bloodResult.put("remark",entity.getRemarks());
            }
            else
                continue;
            hashMapList.add(bloodResult);
        }
        return hashMapList;
    }
    public List<Map<String, Object>> agentRequestReport(String agentUsername) {
        List<Map<String, Object>> requestReport = new ArrayList<>();
        List<String> bloodGroups = stockService.getBloodGroups();

        for (String bloodGroup : bloodGroups) {
            List<BloodInventory> agentEndUserRequests = bloodInventoryRepository.findByUseridCreatedBy(agentUsername);
            List<BloodInventory> filteredRequests = agentEndUserRequests.stream()
                    .filter(request -> request.getBloodGroup().equals(bloodGroup))
                    .collect(Collectors.toList());
            int coinValue = 0;
            int units = 0;
            for (BloodInventory request : filteredRequests) {
                int coinPerUnit = stockService.getCoinPerUnitByBloodGroup(bloodGroup);
                int requestUnits = request.getQuantity();
                coinValue += coinPerUnit * requestUnits;
                units += requestUnits;
            }
            int acceptCount = (int) filteredRequests.stream()
                    .filter(request -> request.getStatus().equals("Accept"))
                    .count();
            int declineCount = (int) filteredRequests.stream()
                    .filter(request -> request.getStatus().equals("Decline"))
                    .count();
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

    public List<Map<String, Object>> agentRate(String agentUsername) {
        List<Map<String, Object>> requestReport = agentRequestReport(agentUsername);
        List<Map<String, Object>> agentRateReport = new ArrayList<>();

        for (Map<String, Object> report : requestReport) {
            String bloodGroup = (String) report.get("bloodGroup");
            int coinValue = (int) report.get("coinValue");

            // Calculate agent coins (commission rate = 10%)
            double rate = 0.1;
            int agentCoins = (int) (coinValue * rate);
            Map<String, Object> rateMap = new HashMap<>();
            rateMap.put("bloodGroup", bloodGroup);
            rateMap.put("Coins", coinValue);
            rateMap.put("agentCoins", agentCoins);
            agentRateReport.add(rateMap);
        }
        return agentRateReport;
    }

    public List<Map<String, Object>> getUsersCoins() {
        List<Map<String, Object>> userCoinReport = new ArrayList<>();
        List<BloodInventory> allRequests = bloodInventoryRepository.findAll();
        for (BloodInventory request : allRequests) {
            // Get request details
            String username = request.getUserid().getUsername();
            String agent = request.getCreatedBy();
            String bloodGroup = request.getBloodGroup();
            int quantity = request.getQuantity();
            String status = request.getStatus();
            // Get coin details
            int coinPerUnit = stockService.getCoinPerUnitByBloodGroup(bloodGroup);
            int totalCoins = coinPerUnit * quantity;
            int userCoins = 0;
            int agentCoins = 0;
            int adminCoins = 0;
            if (status.equalsIgnoreCase("Accept")) {
                userCoins = totalCoins;
                if (!agent.equalsIgnoreCase("AUTO")) {
                    agentCoins = (int) (userCoins * 0.1);
                    adminCoins = userCoins - agentCoins;
                } else {
                    adminCoins = userCoins;
                }
            }
            Map<String, Object> coinMap = new HashMap<>();
            coinMap.put("username", username);
            coinMap.put("bloodGroup", bloodGroup);
            coinMap.put("userCoins", userCoins);
            coinMap.put("agentCoins", agentCoins);
            coinMap.put("adminCoins", adminCoins);
            coinMap.put("status", request.getStatus());
            userCoinReport.add(coinMap);
        }
        return userCoinReport;
    }
    public List<Map<String, Object>> getAgentCoinReport(String agentUsername) {
        List<Map<String, Object>> agentCoinReport = new ArrayList<>();
        List<BloodInventory> agentRequests = bloodInventoryRepository.findByUseridCreatedBy(agentUsername);
        for (BloodInventory request : agentRequests) {
            String username = request.getUserid().getUsername();
            String bloodGroup = request.getBloodGroup();
            int quantity = request.getQuantity();
            String status = request.getStatus();

            int coinPerUnit = stockService.getCoinPerUnitByBloodGroup(bloodGroup);
            int totalCoins = coinPerUnit * quantity;
            int userCoins = 0;
            int agentCoins = 0;

            if (status.equalsIgnoreCase("Accept") ) {
                userCoins=totalCoins;
                agentCoins = (int) (userCoins * 0.1);
            }
            Map<String, Object> coinMap = new HashMap<>();
            coinMap.put("username", username);
            coinMap.put("bloodGroup", bloodGroup);
            coinMap.put("userCoins", userCoins);
            coinMap.put("agentCoins", agentCoins);
            coinMap.put("status",request.getStatus());
            agentCoinReport.add(coinMap);
        }
        return agentCoinReport;
    }
}
