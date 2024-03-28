package com.insightgeeks.bloodbank.service;

import com.insightgeeks.bloodbank.dto.BloodRequestDTO;
import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.entities.BloodBank;
import com.insightgeeks.bloodbank.entities.UserModel;
import com.insightgeeks.bloodbank.repository.BloodRepository;
import com.insightgeeks.bloodbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Service
public class UserService {


    @Autowired
    BloodRepository bloodRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginService loginService;


    public void handleBloodRequirementRequest(BloodRequestDTO bloodRequestDTO, SignupDTO signupDTO)
    {
        BloodBank bloodBank = new BloodBank();

        if(LocalDate.now().isBefore(signupDTO.getNextRequestEligibleDate()) &&
                bloodRequestDTO.getType().equalsIgnoreCase("donate"))
        {
            throw new RuntimeException("You cannot make a request till you reach your next request Eligibility date");
        }

        if(bloodRequestDTO.getType().equalsIgnoreCase("donate") &&
                !bloodRequestDTO.getBloodGroup().equals(signupDTO.getBloodGroup()))
        {
         throw new RuntimeException("your bloodGroup does not match with your bloodGroup");
        }



        if(bloodRequestDTO.getType().equalsIgnoreCase("donate")
        && bloodRequestDTO.getQuantity() > 2)
        {
            throw new RuntimeException("you cannot donate more than 2 units of blood");
        }


        if(bloodRequestDTO.getType().equalsIgnoreCase("receive")
        && bloodRequestDTO.getQuantity() > 4)
        {
            throw new RuntimeException("you cannot receive more than 4 units of blood");
        }

        bloodBank.setBloodGroup(bloodRequestDTO.getBloodGroup());
        bloodBank.setType(bloodRequestDTO.getType());
        bloodBank.setQuantity(bloodRequestDTO.getQuantity());
        bloodBank.setCreatedOn(LocalDateTime.now());
//        bloodBank.setCreatedOn(LocalDateTime.of(2025, Month.DECEMBER, 9, 1, 45));
        bloodBank.setUpdatedOn(LocalDateTime.now());
        bloodBank.setCreatedBy(signupDTO.getCreatedBy());
        bloodBank.setUpdatedBy("admin");
        bloodBank.setStatus("pending");
        bloodBank.setUserModel(loginService.convertToUserModel(signupDTO));
        bloodBank.setRemark("-");
        setCoins(bloodRequestDTO.getBloodGroup(), bloodRequestDTO.getQuantity(), bloodBank);

        bloodRepository.save(bloodBank);

    }

    public void setCoins(String bloodGroup, int units, BloodBank bloodBank)
    {
        switch (bloodGroup)
        {
            case "A+":
                bloodBank.setCoins(10*units);

            case "A-":
                bloodBank.setCoins(15*units);

            case "B+":
                bloodBank.setCoins(20*units);

            case "B-":
                bloodBank.setCoins(25*units);

            case "AB+":
                bloodBank.setCoins(30*units);

            case "AB-":
                bloodBank.setCoins(25*units);

            case "O+":
                bloodBank.setCoins(18*units);

            case "O-":
                bloodBank.setCoins(22*units);

        }
    }

    public void updateNextRequestEligibleDate(int bloodRequestId)
    {
        BloodBank bloodRequest = (bloodRepository.findById(bloodRequestId)).get();
        LocalDate createdDate = bloodRequest.getCreatedOn().toLocalDate();
        UserModel user = bloodRequest.getUserModel();
        user.setNextRequestEligibleDate(createdDate.plusMonths(3));

        userRepository.save(user);

    }

    public void updateUsersCoins(int bloodRequestId)
    {
        BloodBank bloodRequest = (bloodRepository.findById(bloodRequestId)).get();
        UserModel user = bloodRequest.getUserModel();

        if(bloodRequest.getType().equals("donate") && bloodRequest.getCreatedBy().equals("self"))
        {
            user.setCoins(user.getCoins() + bloodRequest.getCoins());
        }
//        else if (bloodRequest.getType().equals("receive") && bloodRequest.getCreatedBy().equals("self")) {
//            user.setCoins(user.getCoins() - bloodRequest.getCoins());
//        }


        if(bloodRequest.getType().equals("donate") && !(bloodRequest.getCreatedBy().equals("self")))
        {
            UserModel agent = (userRepository.findByUsername(bloodRequest.getCreatedBy())).get();
            user.setCoins(user.getCoins() + (bloodRequest.getCoins() - (int)((agent.getCommision()/100.0f)*bloodRequest.getCoins())));
        }
//        else if(bloodRequest.getType().equals("receive") && !(bloodRequest.getCreatedBy().equals("self"))){
//            UserModel agent = (userRepository.findByUsername(bloodRequest.getCreatedBy())).get();
//            user.setCoins(user.getCoins() - (bloodRequest.getCoins() - (int)((agent.getCommision()/100.0f)*bloodRequest.getCoins())));
//        }

        userRepository.save(user);
    }

}
