package com.kashif.service;

import com.kashif.dto.BloodRequestDTO;
import com.kashif.dto.RegistrationDTO;
import com.kashif.entity.BloodRequest;
import com.kashif.repository.BloodRequestRepo;
import com.kashif.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class BloodRequestService {

    @Autowired
    private UserRegistrationService registrationService;
    @Autowired
    private BloodRequestRepo bloodRequestRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BloodRequest bloodRequest;

    public void addBloodInfo(BloodRequestDTO bloodRequestDTO) {
        if (bloodRequestDTO.getQuantity() != null) {
            bloodRequest.setQuantity(bloodRequestDTO.getQuantity());
        } else {
            throw new RuntimeException("Quantity Can't be null or empty");
        }
        bloodRequest.setRemark("-"); //added after remark new column
        bloodRequest.setBloodGroup(bloodRequestDTO.getBloodGroup());
        bloodRequest.setType(bloodRequestDTO.getType());
        bloodRequest.setCreationTime(new Date());
        bloodRequest.setUpdatedTime(new Date());
        bloodRequest.setCreatedBy(bloodRequestDTO.getCreatedBy());
        bloodRequest.setStatus("pending");
        bloodRequest.setUser(registrationService.getUserRegistration(bloodRequestDTO.getUser()));
        bloodRequest.setUpdatedBy("-");
        bloodRequest.setId(0L);
        bloodRequestRepo.save(bloodRequest);
    }

    public final Map<String, Integer> coinPrice = new TreeMap<>() {{
        put("A+", 10);
        put("A-", 15);
        put("B+", 20);
        put("B-", 25);
        put("AB+", 30);
        put("AB-", 35);
        put("O+", 40);
        put("O-", 45);
    }};

    public List<HashMap<String, Object>> getBloodBankList() {
        List<BloodRequest> list = bloodRequestRepo.findAll();
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        for (BloodRequest stock : list) {
            HashMap<String, Object> myResult = new HashMap<>();

            myResult.put("id", stock.getId());
            myResult.put("bloodGroup", stock.getBloodGroup());
            myResult.put("type", stock.getType());
            myResult.put("status", stock.getStatus());
            myResult.put("quantity", stock.getQuantity());
            myResult.put("coins", stock.getType().equalsIgnoreCase("donor")
                    ? coinPrice.get(stock.getBloodGroup()) * stock.getQuantity()
                    : (coinPrice.get(stock.getBloodGroup())+10 ) * stock.getQuantity());
            myResult.put("username", stock.getUser().getUsername());
            myResult.put("agent", stock.getUser().getCreatedBy());
            myResult.put("dob", stock.getUser().getDob());
            myResult.put("createdAt", stock.getCreationTime());
            myResult.put("updatedAt", stock.getUpdatedTime());
            myResult.put("userCreatedBy", stock.getUser().getCreatedBy());
            myResult.put("remark", stock.getRemark());
            // Age Calculation
            LocalDate birthDate = stock.getUser().getDob();
            LocalDate now = LocalDate.now();
            int age = now.getYear() - birthDate.getYear();
            // Adjust age if the current date hasn't passed the birthday yet
            if (now.getDayOfYear() < birthDate.getDayOfYear()) {
                age--;
            }
            myResult.put("age", age);
            resultList.add(myResult);
        }
        Collections.reverse(resultList);
        return resultList;
    }


    //Blood Request (Status) of users who requested for bloodGroup(includes: rejected, pending, and accepted and filtered by role: enduser/agent)
    public List<HashMap<String, Object>> requestBloodStatus(String username, String role) {
        List<HashMap<String, Object>> bloodBankList = getBloodBankList();

        if (role.equalsIgnoreCase("agent"))
            return bloodBankList.stream()
                    .filter(i -> i.get("userCreatedBy").equals(username)).collect(Collectors.toList());
        else
            return bloodBankList.stream()
                    .filter(i -> i.get("username").equals(username)).collect(Collectors.toList());
    }

    //Grouping bloodGroup with coins, quantity and total request
    public Map<String, Long> requestsByBloodGroup(String status, String username, String role) {
        if (role.equalsIgnoreCase("admin") && (status.equalsIgnoreCase("accepted") || status.equalsIgnoreCase("rejected")))
            return getBloodBankList().stream()
                    .filter(i->i.get("type").equals("donor"))
                    .filter(i->i.get("status").equals(status)).collect(Collectors.groupingBy(i -> (String) i.get("bloodGroup"), Collectors.counting()));

        if (!role.equalsIgnoreCase("EndUser") && (status.equalsIgnoreCase("accepted") || status.equalsIgnoreCase("rejected")))
            return getBloodBankList().stream()
                    .filter(i->i.get("userCreatedBy").toString().equalsIgnoreCase(username))
                    .filter(i->i.get("type").equals("donor"))
                    .filter(i->i.get("status").equals(status)).collect(Collectors.groupingBy(i -> (String) i.get("bloodGroup"), Collectors.counting()));
        return new HashMap<>();
    }

    public Map<String, Long> coinsByBloodGroup(String status, String username, String role) {
        if (role.equalsIgnoreCase("admin") && (status.equalsIgnoreCase("accepted") || status.equalsIgnoreCase("rejected")))
            return getBloodBankList().stream()
                    .filter(i->i.get("type").equals("donor"))
                    .filter(i->i.get("status").equals(status)).collect(Collectors.groupingBy(i -> (String) i.get("bloodGroup"), Collectors.summingLong(i -> (long) i.get("coins"))));
        if (!role.equalsIgnoreCase("EndUser") && (status.equalsIgnoreCase("accepted") || status.equalsIgnoreCase("rejected")))
            return getBloodBankList().stream()
                    .filter(i->i.get("userCreatedBy").toString().equalsIgnoreCase(username))
                    .filter(i->i.get("type").equals("donor"))
                    .filter(i->i.get("status").equals(status)).collect(Collectors.groupingBy(i -> (String) i.get("bloodGroup"), Collectors.summingLong(i -> (long) i.get("coins"))));
        return new HashMap<>();
    }

    public Map<String, Long> quantityByBloodGroup(String status, String username, String role) {
        if (role.equalsIgnoreCase("admin") && (status.equalsIgnoreCase("accepted") || status.equalsIgnoreCase("rejected")))
            return getBloodBankList().stream()
                    .filter(i->i.get("type").equals("donor"))
                    .filter(i->i.get("status").equals(status)).filter(i -> i.get("status").equals(status)).collect(Collectors.groupingBy(i -> (String) i.get("bloodGroup"), Collectors.summingLong(i -> (long) i.get("quantity"))));
        if (!role.equalsIgnoreCase("EndUser") && (status.equalsIgnoreCase("accepted") || status.equalsIgnoreCase("rejected")))
            return getBloodBankList().stream()
                    .filter(i->i.get("userCreatedBy").toString().equalsIgnoreCase(username))
                    .filter(i->i.get("type").equals("donor"))
                    .filter(i->i.get("status").equals(status)).filter(i -> i.get("status").equals(status)).collect(Collectors.groupingBy(i -> (String) i.get("bloodGroup"), Collectors.summingLong(i -> (long) i.get("quantity"))));
        return new HashMap<>();
    }

    //Blood-Report---
    public List<Map<String, Object>> listReport(String status, String username, String role){
        List<Map<String, Object>> combinedData = new ArrayList<>();
        // Iterate through each blood group
        for (String bloodGroup : requestsByBloodGroup(status, username, role).keySet()) {
            // Create a new map to hold combined data for the current blood group
            Map<String, Object> bloodGroupData = new HashMap<>();
            bloodGroupData.put("bloodGroup", bloodGroup);
            bloodGroupData.put("requests", requestsByBloodGroup(status, username, role).getOrDefault(bloodGroup, 0L));
            double endUserCoins = coinsByBloodGroup(status, username, role).getOrDefault(bloodGroup, 0L);
            bloodGroupData.put("coins", endUserCoins);
            bloodGroupData.put("quantity", quantityByBloodGroup(status, username, role).getOrDefault(bloodGroup, 0L));
            // Add the combined data for the current blood group to the combined list
            combinedData.add(bloodGroupData);
        }
        double totalCoins = combinedData.stream().mapToDouble(i-> (double) i.get("coins")).sum();
        Long totalReq = combinedData.stream().mapToLong(i-> (long) i.get("requests")).sum();
        Long totalQuan = combinedData.stream().mapToLong(i-> (long) i.get("quantity")).sum();

        combinedData.add(Map.of("bloodGroup", "Total","coins", totalCoins, "requests", totalReq, "quantity", totalQuan));
        return combinedData;
    }


    public void updateStatusAndRemarkById(String status, String remark, String username,  Long id) {
        bloodRequestRepo.updateStatusAndRemarkById(status, remark, new Date(), username, id);
    }
//    public List<>
}
