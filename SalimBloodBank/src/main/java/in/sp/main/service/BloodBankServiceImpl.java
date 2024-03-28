package in.sp.main.service;

import in.sp.main.dao.BloodBankRepository;
import in.sp.main.dao.UserRepository;
import in.sp.main.dto.BloodBankDTO;
import in.sp.main.dto.RegisterDTO;
import in.sp.main.entities.BloodBankModel;
import in.sp.main.entities.UserModel;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BloodBankServiceImpl implements BloodBankService{

    @Autowired
    BloodBankRepository bloodBankRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminServiceImpl adminServiceImpl;

    public UserModel convertToUserModel(RegisterDTO registerDTO)
    {
        return modelMapper.map(registerDTO, UserModel.class);
    }

    @Override
    public void bloodBankService(BloodBankDTO bloodBankDTO, RegisterDTO registerDTO) {

        BloodBankModel bloodBankModel=new BloodBankModel();
        bloodBankModel.setBloodGroup(bloodBankDTO.getBloodGroup());
        bloodBankModel.setStatus("pending");
        bloodBankModel.setCreatedBy(registerDTO.getUsername());
        bloodBankModel.setQuantity(bloodBankDTO.getQuantity());
        bloodBankModel.setCreatedAt(String.valueOf(LocalDateTime.now()));
        bloodBankModel.setRequestType(bloodBankDTO.getRequestType());
        bloodBankModel.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        bloodBankModel.setUpdatedBy("admin");
        bloodBankModel.setId(bloodBankDTO.getId());
        bloodBankModel.setUser(convertToUserModel(registerDTO));

        // Add coins according to the blood group
        int coinsToAdd = 0;
        int increment= (int) bloodBankDTO.getQuantity();
        switch (bloodBankDTO.getBloodGroup()) {
            case "A+":
                coinsToAdd = 10*increment; // Example: Add 10 coins for blood group A+
                break;
            case "B+":
                coinsToAdd = 15*increment; // Example: Add 15 coins for blood group B+
                break;
            case "O-":
                coinsToAdd=20*increment;
            case "O+":
                coinsToAdd=25*increment;
            case "AB+":
                coinsToAdd=30*increment;
            case "AB-":
                coinsToAdd=35*increment;

            default:
                // Handle other blood groups or set coinsToAdd to a default value
                break;
        }

        bloodBankModel.setCoins(coinsToAdd);

        bloodBankRepository.save(bloodBankModel);

    }

//    public List<BloodBankModel> getBloodStock() {
//        return (List<BloodBankModel>) bloodBankRepository.findAll();
//    }
//    @Override
//    public List<BloodBankDTO> getBloodDataForAgent(String agentUsername) {
//        List<UserModel> users = userRepository.findByCreatedBy(agentUsername);
//        List<BloodBankDTO> bloodData = new ArrayList<>();
//        for (UserModel user : users) {
//            List<BloodBankModel> userBloodBanks = userRepository.findByUser(user);
//            for (BloodBankModel bloodBank : userBloodBanks) {
//                bloodData.add(convertToBloodBankDTO(bloodBank));
//            }
//        }
//        return bloodData;
//    }
//
//    private BloodBankDTO convertToBloodBankDTO(BloodBankModel bloodRequest) {
//        return modelMapper.map(bloodRequest, BloodBankDTO.class);
//    }



    @Override
    public int userTotalCoins(String userEmail) {

        int coins = adminServiceImpl.fetchBloodRequest().stream().filter(user->user.getUser().getUserEmail().equalsIgnoreCase(userEmail)).mapToInt(BloodBankDTO::getCoins).sum();

        return coins;
    }

    @Override
    public List<BloodBankDTO> agentCoins(String userName) {
        List<BloodBankDTO> collectCoins = adminServiceImpl.fetchBloodRequest().stream().filter(user -> user.getUser().getCreatedBy().equalsIgnoreCase(userName)).collect(Collectors.toList());
        return collectCoins;
    }

    //Blood request send by user (agent)
    @Override
    public List<BloodBankDTO> bloodRequestUserAgent(String userName) {
//        List<BloodBankDTO> acceptedUser=adminServiceImpl.fetchBloodRequest().stream().filter(user->user.getStatus().equalsIgnoreCase("accepted")).collect(Collectors.toList());
        List<BloodBankDTO> agentUserBloodRequest =  adminServiceImpl.fetchBloodRequest().stream().filter(request -> request.getUser().getCreatedBy().equals(userName)).collect(Collectors.toList());

       // System.out.println();
//        System.out.println(agentUserBloodRequest );
        return agentUserBloodRequest ;
    }

    //show blood request on user dashboard
    @Override
    public List<BloodBankDTO> getBloodRequestUserDash(String userName) {
     return adminServiceImpl.fetchBloodRequest().stream().filter(request->request.getUser().getUsername().equals(userName)).collect(Collectors.toList());

    }


    public boolean canDonateBlood(String username) {
//        System.out.println("user: " + username);
        List<BloodBankDTO> bloodBankDTOList = adminServiceImpl.fetchBloodRequest();
        Collections.reverse(bloodBankDTOList);

        // Get the user's last donation date
        Optional<String> donationDateOptional = bloodBankDTOList.stream()
                .filter(i -> i.getUser().getUsername().equalsIgnoreCase(username))
                .filter(i -> i.getRequestType().equalsIgnoreCase("donation"))
                .map(BloodBankDTO::getCreatedAt)
                .findFirst();

        // Check if donation is present
        // Check if donation date is present
        if (donationDateOptional.isPresent()) {
            LocalDateTime lastDonationDate = LocalDateTime.parse(donationDateOptional.get());
//            System.out.println(lastDonationDate + "++++++++++++++++++++++++++++++++++++++++++++");

            // Calculate duration between last donation and current date in days
            long daysSinceLastDonation = Duration.between(lastDonationDate, LocalDateTime.now()).toDays();
//            System.out.println("-----days-------"+daysSinceLastDonation);
            // Proceed with your logic here using daysSinceLastDonation
            if(daysSinceLastDonation<90){
                return false;
            }

        } else {
            // Handle the case where no donation date is found for the user
//            System.out.println("No donation date found for user: " + username);
            // Handle accordingly based on your requirements

        }
        return  true;
    }

//    public void updateCoins(Long id, int coin)
//    {
//        Optional<BloodBankModel> bloodBankOptional = bloodBankRepository.findById(id);
//        if (bloodBankOptional.isPresent()) {
//            BloodBankModel bloodBank = bloodBankOptional.get();
//            if(bloodBank.getStatus().equalsIgnoreCase("accepted")) {
//                long coins = (long) bloodBank.getCoins();
//                coin += (Long) coins;
//                bloodBankRepository.updateCoinsById(id, coin);
//            }
//            if(bloodBank.getStatus().equalsIgnoreCase("pending"))
//            {
//
//            }
//            // Use 'coins' as needed
//        }
//    }

    public void updateStatus(Long id,String status)
    {

        bloodBankRepository.updateStatusById(id, status);
    }

    //filtering blood request
//    @Override
//    public List<BloodBankDTO> filterByStatusAdmin(String userName) {
//        adminServiceImpl.fetchBloodRequest().stream().filter(status->status.getStatus().equalsIgnoreCase("pending")).collect(Collectors.toList());
//        return null;
//    }

}




