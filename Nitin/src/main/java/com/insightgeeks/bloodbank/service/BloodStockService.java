package com.insightgeeks.bloodbank.service;

import com.insightgeeks.bloodbank.dto.BloodRequestDTO;
import com.insightgeeks.bloodbank.dto.BloodStockDTO;
import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.entities.BloodBank;
import com.insightgeeks.bloodbank.entities.BloodStock;
import com.insightgeeks.bloodbank.entities.UserModel;
import com.insightgeeks.bloodbank.repository.BloodRepository;
import com.insightgeeks.bloodbank.repository.BloodStockRepository;
import com.insightgeeks.bloodbank.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BloodStockService {

    @Autowired
    BloodStockRepository bloodStockRepository;

    @Autowired
    BloodRepository bloodRepository;

    @Autowired
    ModelMapper modelMapper;


    public void updateBloodStock(int bloodRequestId)
    {
        BloodBank bloodRequest = (bloodRepository.findById(bloodRequestId)).get();
        Optional<BloodStock> bloodGroupOptional = bloodStockRepository.findById(bloodRequest.getBloodGroup());
        BloodStock bloodStockData = bloodGroupOptional.get();

        if(bloodRequest.getType().equals("donate"))
        {
            bloodStockData.setUnits(bloodStockData.getUnits()+bloodRequest.getQuantity());
        }
        else{
            bloodStockData.setUnits(bloodStockData.getUnits() - bloodRequest.getQuantity());
        }
        bloodStockData.setLastUpdated(LocalDateTime.now());
        bloodStockRepository.save(bloodStockData);
    }




    public SignupDTO convertToSignupDTO(UserModel userModel) {
        return modelMapper.map(userModel, SignupDTO.class);
    }


    public BloodStockDTO convertToBloodStockDTO(BloodStock bloodStock)
    {
        return modelMapper.map(bloodStock, BloodStockDTO.class);
    }


    public List<BloodStockDTO> getBloodStock()
    {
        List<BloodStockDTO> bloodStockDTOS = new ArrayList<>();
        List<BloodStock> bloodStocks = (List<BloodStock>)bloodStockRepository.findAll();
        for(BloodStock bloodStock:bloodStocks)
        {
            bloodStockDTOS.add(convertToBloodStockDTO(bloodStock));
        }

        return bloodStockDTOS;
    }


    public Map<String, Map<String, Map<String, Integer>>> calculateStatistics(SignupDTO user) {

        Map<String, Map<String, Map<String, Integer>>> stats = new HashMap<>();
        List<BloodBank> bloodRequests = new ArrayList<>();

        if(user.getRole().equals("admin")) {
            bloodRequests = (List<BloodBank>) bloodRepository.findAll();
        } else {
            Iterable<BloodBank> bloodIterable = bloodRepository.findAll();
            List<BloodBank> tempBloodRequests = new ArrayList<>();
            bloodIterable.forEach(tempBloodRequests::add);
            bloodRequests = tempBloodRequests.stream()
                    .filter(request -> request.getUserModel().getCreatedBy().equals(user.getUsername()))
                    .collect(Collectors.toList());
        }

        for (BloodBank request : bloodRequests) {
            String bloodGroup = request.getBloodGroup();
            Map<String, Map<String, Integer>> groupStats = stats.getOrDefault(bloodGroup, new HashMap<>());

            Map<String, Integer> statusStats = groupStats.getOrDefault(request.getStatus(), new HashMap<>());

            statusStats.put("count", statusStats.getOrDefault("count", 0) + 1);
            statusStats.put("units", statusStats.getOrDefault("units", 0) + request.getQuantity());
            statusStats.put("coins", statusStats.getOrDefault("coins", 0) + request.getCoins());

            groupStats.put(request.getStatus(), statusStats);
            stats.put(bloodGroup, groupStats);
        }

        Set<String> allBloodGroups = new HashSet<>(Arrays.asList("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"));
        for (String bloodGroup : allBloodGroups) {
            Map<String, Map<String, Integer>> groupStats = stats.getOrDefault(bloodGroup, new HashMap<>());

            boolean hasApprovedRequests = groupStats.containsKey("approved");
            boolean hasRejectedRequests = groupStats.containsKey("rejected");

            if (!hasApprovedRequests) {

                Map<String, Integer> approvedStats = new HashMap<>();
                approvedStats.put("count", 0);
                approvedStats.put("units", 0);
                approvedStats.put("coins", 0);

                groupStats.put("approved", approvedStats);
            }

            if (!hasRejectedRequests) {

                Map<String, Integer> rejectedStats = new HashMap<>();
                rejectedStats.put("count", 0);
                rejectedStats.put("units", 0);
                rejectedStats.put("coins", 0);

                groupStats.put("rejected", rejectedStats);
            }
            stats.put(bloodGroup, groupStats);
        }
        return stats;
    }



}
