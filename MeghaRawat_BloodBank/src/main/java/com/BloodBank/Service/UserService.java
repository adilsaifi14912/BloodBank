package com.BloodBank.Service;

import com.BloodBank.Model.BloodStockModel;
import com.BloodBank.Model.UserModel;
import com.BloodBank.Repository.BloodStockRepository;
import com.BloodBank.Repository.UserRepository;
import com.BloodBank.dto.UserSignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BloodStockRepository bloodStockRepository;

    public void addUser(UserSignUpDTO userSignUpDTO,String role,String createdBy) {
        UserModel userModel = new UserModel();
        userModel.setUsername(userSignUpDTO.getUsername());
        if(userSignUpDTO.getPassword()==null)
            userModel.setPassword(String.valueOf(userSignUpDTO.getDob()));
        else
            userModel.setPassword(userSignUpDTO.getPassword());
        userModel.setName(userSignUpDTO.getName());
        userModel.setPhone(userSignUpDTO.getPhone());
        userModel.setDob(userSignUpDTO.getDob());
        userModel.setRole(role);
        if(Objects.equals(role, "EU"))
        {
            userModel.setCreatedBy(createdBy);
            if(createdBy.equals("auto"))
            {
                userModel.setModifyBy("self");
                userModel.setFirstTimeLogin(false);
            }
            else
            {
                userModel.setModifyBy(createdBy);
            }
        }
        else if (Objects.equals(role, "AG"))
        {
            userModel.setCreatedBy(createdBy);
            userModel.setModifyBy("admin");
            userModel.setCommission(10L);
        }
        userModel.setCreated_date_time(LocalDateTime.now());
        userModel.setUpdated_date_time(LocalDateTime.now());
        userModel.setAddress(userSignUpDTO.getAddress());
        userModel.setBloodGroup(userSignUpDTO.getBloodGroup());
        userModel.setCoins(0);
        userRepository.save(userModel);
    }

    public boolean isUsernameTaken(String username) {
        // Assuming you have a UserRepository interface that extends JpaRepository<User, Long>
        // This assumes that you have a User entity with a field named "username"
        Optional<UserModel> userOptional = Optional.ofNullable(userRepository.findByUsername(username));

        // If a user with the provided username exists, return true; otherwise, return false
        return userOptional.isPresent();
    }


    public void updateCoins(String username, String bloodGroup, int quantity) {
        // Step 1: Retrieve the user by ID
        UserModel User = userRepository.findByUsername(username);
        UserModel admin = userRepository.findByUsername("admin");

        // Step 2: Update coins based on blood group and quantity
        //int userCoins = User.getCoins();    //get user's previous coins

        int userCoins=0;
        BloodStockModel stock = bloodStockRepository.findByBloodGroup(bloodGroup)
            .orElseThrow(() -> new NoSuchElementException("Blood group not found"));
        userCoins += quantity * stock.getCoinsPerUnit(); //update user coins

        if(!User.getCreatedBy().equals("auto"))
        {
            String agentUsername = User.getCreatedBy();
            UserModel agent = userRepository.findByUsername(agentUsername);

            if (agent != null) {
                int agentCoins = (int) (0.1 * userCoins); // 10% of the user's coins
                agent.setCoins(agent.getCoins() + agentCoins);
                admin.setCoins(admin.getCoins()+(userCoins-agentCoins));   //set admin coins (difference of user and agent coins)
                userRepository.save(agent); // Save the updated agent
            }
        }
        else
            admin.setCoins(admin.getCoins()+userCoins);  //admin will also get user's coins

        // Step 3: Update coins in the UserModel

        User.setCoins(User.getCoins()+userCoins);
        userRepository.save(admin); // Save the updated admin
        userRepository.save(User); // Save the updated user
    }

    public void updateUserPassword(String newPassword, String username) {
        userRepository.updatePasswordByUsername(newPassword, username);
        updateNewUserLogin(false,username);
    }

    public void  updateNewUserLogin(boolean firstTimeLogin, String username){
        userRepository.updateFirstTimeLoginByUsername(firstTimeLogin, username);
    }

    public void updateBlockedStatus(boolean blockedStatus,String username){
        userRepository.updateUserBlockedStatusByUsername(blockedStatus,username);
    }

    public UserModel authenticateUser(String username) {

        return userRepository.findByUsername(username);
    }

    public List<UserModel> getAllUsers(String sortBy, String filterBy) {
        List<UserModel> list = userRepository.findAll();

        // Apply filtering based on the provided filterBy parameter
        switch (filterBy) {
            case "agent":
                list = filterByAgent(list);
                break;
            case "auto":
                list = filterByAuto(list);
                break;
            default:
                list=filterByBlockedStatus(list);
                break;
        }

        // Apply sorting based on the provided sortBy parameter
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
            default:
                list.sort(Comparator.comparing(UserModel::getId));
                break;
        }
        return list;
    }

    // Helper method to filter the list by agent
    private List<UserModel> filterByAgent(List<UserModel> userList) {
        List<UserModel> filteredList = new ArrayList<>();
        for (UserModel user : userList) {
            if ("AG".equals(user.getRole())) {
                filteredList.add(user);
            }
        }
        return filteredList;
    }


    private List<UserModel> filterByBlockedStatus(List<UserModel> userList){
        List<UserModel> filteredList = new ArrayList<>();
        for (UserModel user : userList) {
            if(!user.getBlockedStatus()){
                filteredList.add(user);
            }
        }
        return filteredList;
    }

    // Helper method to filter the list by auto
    private List<UserModel> filterByAuto(List<UserModel> userList) {
        List<UserModel> filteredList = new ArrayList<>();
        for (UserModel user : userList) {
            if ("auto".equals(user.getCreatedBy())) {
                filteredList.add(user);
            }
        }
        return filteredList;
    }

    private List<UserModel> filterByNotSignedIn(List<UserModel> userList) {
        return userList.stream()
                .filter(user -> user.isFirstTimeLogin())
                .collect(Collectors.toList());
    }

    private List<UserModel> filterByUsername(List<UserModel> userList, String usernameFilter) {
        return userList.stream()
                .filter(user -> user.getUsername().contains(usernameFilter))
                .collect(Collectors.toList());
    }

    // Helper method to filter the list by date range
    private List<UserModel> filterByDateRange(List<UserModel> userList, LocalDate startDate, LocalDate endDate) {
        return userList.stream()
                .filter(user -> {
                    LocalDateTime userDateTime = user.getCreated_date_time();
                    LocalDate userDate = userDateTime.toLocalDate();
                    return !userDate.isBefore(startDate) && !userDate.isAfter(endDate);
                })
                .collect(Collectors.toList());
    }

    public List<UserModel> getAllUsersByAgent(String agentName,String sortBy) {
        List<UserModel> all = userRepository.findAll();
        List<UserModel> list = new ArrayList<>();
        for (UserModel userModel : all) {
            if(userModel.getCreatedBy().equals(agentName))
                list.add(userModel);
        }

        // Apply sorting based on the provided sortBy parameter
        switch (sortBy) {
            case "username":
                list.sort(Comparator.comparing(UserModel::getUsername));
                break;
            case "bloodGroup":
                list.sort(Comparator.comparing(UserModel::getBloodGroup));
                break;
            default:
                list.sort(Comparator.comparing(UserModel::getId));
                break;
        }
        return list;
    }
}
