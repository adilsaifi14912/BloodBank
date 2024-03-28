package com.example.Charul_BloodBank.service;

import com.example.Charul_BloodBank.dto.UserSignUpDTO;
import com.example.Charul_BloodBank.model.StockModel;
import com.example.Charul_BloodBank.model.UserModel;
import com.example.Charul_BloodBank.repository.BloodInventoryRepository;
import com.example.Charul_BloodBank.repository.StockRepository;
import com.example.Charul_BloodBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SignUpService {
    @Autowired
    private UserRepository bloodBankRepository;
    @Autowired
    private BloodInventoryRepository bloodInventoryRepository;
    @Autowired
    private StockRepository stockRepository;
    public void insert(UserSignUpDTO userDto, String role, String created_by) {
        UserModel user1 = new UserModel();
        user1.setName(userDto.getName());
        user1.setUsername(userDto.getUsername());
        user1.setPassword(userDto.getPassword());
        user1.setDob(userDto.getDob());
        user1.setCreatedOn();
        user1.setRole(role);
        user1.setCreatedBy(created_by);
        user1.setBloodGroup(userDto.getBloodGroup());
        user1.setFirstLogin(true);
        if("enduser".equals(user1.getRole())&& "AUTO".equals(user1.getCreatedBy()))
            user1.setPassword(userDto.getPassword());
        else
            user1.setPassword(String.valueOf(userDto.getDob()));

        bloodBankRepository.save(user1);
    }
    public List<UserModel> getAllUsers() {
        return bloodBankRepository.findAll();
    }
    public List<UserModel> getAllUsers(String sortBy, String filterBy,String startDate, String endDate, String username) {
        List<UserModel> list=bloodBankRepository.findAll();

        switch(filterBy){
            case "agent":
                list = list.stream().
                        filter(user -> "agent".equalsIgnoreCase(user.getRole())).
                        collect(Collectors.toList());
                break;
            case "Users":
                list = list.stream().
                        filter(user -> "AUTO".equalsIgnoreCase(user.getCreatedBy())).
                        collect(Collectors.toList());
                break;
            case "createdOn":
                if (startDate != null && endDate != null) {
                    list = list.stream().
                            filter(user -> user.getCreatedOn().toLocalDate().isAfter(LocalDate.parse(startDate))
                                    && user.getCreatedOn().toLocalDate().isBefore(LocalDate.parse(endDate))).
                            collect(Collectors.toList());
                }
                break;
            default:
                break;
        }

        switch (sortBy) {
            case "username":
                list.sort(Comparator.comparing(UserModel::getUsername));
                break;
            case "bloodGroup":
                list.sort(Comparator.comparing(UserModel::getBloodGroup));
                break;
            case "createdBy":
                list.sort(Comparator.comparing(UserModel::getCreatedBy));
                break;
        }
        return list;
    }
    public void updateCoins(String username, String bloodGroup, int quantity) {
        UserModel user = bloodBankRepository.findByUsername(username);
        long userCoins = 0;
        StockModel stock = stockRepository.findByBloodGroup(bloodGroup)
                .orElseThrow(() -> new NoSuchElementException("Blood group not found"));
        userCoins += (long) quantity * stock.getCoinPerUnit();
        if(user.getCreatedBy().equalsIgnoreCase("AUTO")){
            UserModel admin = bloodBankRepository.findByUsername("admin");
            long adminCoins = userCoins + admin.getCoinValue();
            admin.setCoinValue(adminCoins);
            bloodBankRepository.save(admin);
        }
        else{
            String agentUsername = user.getCreatedBy();
            UserModel agent = bloodBankRepository.findByUsername(agentUsername);
            long agentCoins = (long) (userCoins * 0.1) + agent.getCoinValue();
            agent.setCoinValue(agentCoins);
            bloodBankRepository.save(agent);
            UserModel admin = bloodBankRepository.findByUsername("admin");
            long adminCoins = (userCoins - agentCoins) + admin.getCoinValue();
            admin.setCoinValue(adminCoins);
            bloodBankRepository.save(admin);
        }
        user.setCoinValue(userCoins + user.getCoinValue());
        bloodBankRepository.save(user);
    }
}

