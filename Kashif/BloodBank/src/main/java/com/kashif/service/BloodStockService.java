package com.kashif.service;

import com.kashif.dto.BloodStockDTO;
import com.kashif.entity.BloodStock;
import com.kashif.entity.UserRegistration;
import com.kashif.repository.BloodRepo;
import com.kashif.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class BloodStockService {


    @Autowired
    private  BloodRepo bloodRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BloodStock bloodStock;
    public void addBloodInfo(BloodStockDTO bloodStockDTO){
        if(bloodStockDTO.getQuantity()!=null){
            bloodStock.setQuantity(bloodStockDTO.getQuantity());
        }
        else {
            throw new RuntimeException("Quantity Can't be null or empty");
        }
        bloodStock.setBloodGroup(bloodStockDTO.getBloodGroup());
        bloodStock.setType(bloodStockDTO.getType());
        bloodStock.setCreationTime(new Date());
        bloodStock.setUpdatedTime(new Date());
        bloodStock.setCreatedBy(bloodStockDTO.getCreatedBy());
        bloodStock.setStatus("pending");
        bloodStock.setUser(bloodStockDTO.getUser());
        bloodStock.setUpdatedBy("-");
        bloodStock.setId(0L);
        bloodRepo.save(bloodStock);
    }

    public List<HashMap<String, Object>> getBloodBankList(){
        List<BloodStock> list = bloodRepo.findAll();
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        for (BloodStock stock : list) {
            HashMap<String, Object> myResult = new HashMap<>();

            myResult.put("id", stock.getId());
            myResult.put("bloodGroup", stock.getBloodGroup());
            myResult.put("type", stock.getType());
            myResult.put("status", stock.getStatus());
            myResult.put("quantity", stock.getQuantity());
            myResult.put("username", stock.getUser().getUsername());
            myResult.put("agent", stock.getUser().getCreatedBy());
            myResult.put("dob", stock.getUser().getDob());
            myResult.put("createdAt", stock.getCreationTime());
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

    public void updateStatusById(String status, Long id){
        bloodRepo.updateStatusById(status, id);
    }
//    public List<>
}
