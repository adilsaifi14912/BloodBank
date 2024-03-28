package BloodBank.Services;

import BloodBank.Entity.UserModel;
import BloodBank.Entity.UserRequestModel;
import BloodBank.Entity.UserSignUpDetailsDTO;
import BloodBank.Repository.BloodInfoRepository;
import BloodBank.Repository.RequestRepository;
import BloodBank.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private BloodInfoRepository bloodInfoRepository;

    public List<UserSignUpDetailsDTO> getAllUsers(String sortBy, String filterBy,String startDate, String endDate, String username) {
        List<UserModel> all = userRepository.findAll();
        List<UserSignUpDetailsDTO> list = new ArrayList<>();
        for (UserModel userModel : all) {
            list.add(convertToSignupDTO(userModel));
        }
        switch (filterBy) {
            case "agent":
                list = list.stream().filter(user -> "AG".equals(user.getRole())).collect(Collectors.toList());
                break;
            case "self":
                list = list.stream().filter(user -> "AUTO".equals(user.getCreatedby())).collect(Collectors.toList());
                break;
            case "aguser":
                list = list.stream().filter(user -> "AGENT".equals(user.getCreatedby())).collect(Collectors.toList());
                break;
            case "username":
                if (username != null && !username.isEmpty()) {
                    list = list.stream().filter(user -> user.getUsername().toLowerCase().startsWith(username.toLowerCase())).collect(Collectors.toList());
                }
                break;
            case "createdBetween":
                if (startDate != null && endDate != null) {
                    list = list.stream().filter(user -> user.getCreatedOn().toLocalDate().isAfter(LocalDate.parse(startDate)) && user.getCreatedOn().toLocalDate().isBefore(LocalDate.parse(endDate))).collect(Collectors.toList());
                }
                break;
            default:
                break;
        }

        // Apply sorting based on the provided sortBy parameter
        switch (sortBy) {
            case "username":
                list.sort(Comparator.comparing(UserSignUpDetailsDTO::getUsername));
                break;
            case "bloodGroup":
                list.sort(Comparator.comparing(UserSignUpDetailsDTO::getBloodGroup));
                break;
            case "createdby":
                list.sort(Comparator.comparing(UserSignUpDetailsDTO::getCreatedby));
                break;
            default:
                list.sort(Comparator.comparing(UserSignUpDetailsDTO::getId));
                break;
        }
        return list;
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

    public UserSignUpDetailsDTO authenticateUser(String username) {

        return convertToSignupDTO(userRepository.findByUsername(username));
    }
    public UserSignUpDetailsDTO convertToSignupDTO(UserModel userModel) {
        if (userModel != null) {
            return modelMapper.map(userModel, UserSignUpDetailsDTO.class);
        } else {
            return null;
        }
    }
    public List<UserSignUpDetailsDTO> getAllUsersByAgent(String agentname,String sortBy) {
        List<UserModel> all = userRepository.findAll();
        List<UserSignUpDetailsDTO> list = new ArrayList<>();
        for (UserModel userModel : all) {
            if(userModel.getCreatedby().equals(agentname))
                list.add(convertToSignupDTO(userModel));
        }

        // Apply sorting based on the provided sortBy parameter
        switch (sortBy) {
            case "username":
                list.sort(Comparator.comparing(UserSignUpDetailsDTO::getUsername));
                break;
            case "bloodGroup":
                list.sort(Comparator.comparing(UserSignUpDetailsDTO::getBloodGroup));
                break;
            default:
                list.sort(Comparator.comparing(UserSignUpDetailsDTO::getId));
                break;
        }
        return list;
    }
    @Transactional
    public void coinupdate(Long reqID){
        UserRequestModel userRequestModel=requestRepository.getById(reqID);
        String bloodGroup=userRequestModel.getBloodGroup();
        String type=userRequestModel.getType();
        int quantity=userRequestModel.getQuantity();
        int coinRate= bloodInfoRepository.findByBloodGroup(bloodGroup).getCoinPerUnit();
        String username=userRequestModel.getUsername();
        String createdBy=userRepository.findByUsername(username).getCreatedby();
        int userCoinValue=userRepository.findByUsername(username).getCoin();
        int adminCoinValue=userRepository.findByUsername("ADMIN").getCoin();
        int i = (quantity * coinRate) - ((quantity * coinRate * 10) / 100);
        if(type.equals("Donate")){
            userCoinValue=userCoinValue+(quantity*coinRate);
        }
        else{
            userCoinValue=userCoinValue-(quantity*coinRate);

        }
        if(createdBy.equals("AUTO"))
            adminCoinValue=adminCoinValue+(quantity*coinRate);
        else{
            int agentCoinValue=userRepository.findByUsername(createdBy).getCoin();
            agentCoinValue=agentCoinValue+((quantity*coinRate*10)/100);
            adminCoinValue=adminCoinValue+ i;
            userRepository.updateCoin(agentCoinValue,createdBy);}
        userRepository.updateCoin(userCoinValue,username);
        userRepository.updateCoin(adminCoinValue,"ADMIN");
    }
    public List<HashMap<String,Object>> getCoinReportAgent(String username) {
        List<UserModel> userModelList=userRepository.findAll().stream().filter(u->u.getCreatedby().equals(username)).collect(Collectors.toList());
        List<HashMap<String,Object>> list=new ArrayList<>();
        for (UserModel userModel : userModelList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("username", userModel.getUsername());
            map.put("rate", "10%");
            map.put("endUserCoin", userModel.getCoin());
            map.put("agentCoin", (userModel.getCoin() * 10) / 100);
            list.add(map);
        }
        return list;
    }
    public List<HashMap<String, Object>> getCoinReportAdmin(){
        List<UserModel> userModelList=userRepository.findAll().stream().filter(u->u.getRole().equals("EU")).collect(Collectors.toList());
        List<HashMap<String,Object>> list=new ArrayList<>();
        for(UserModel userModel:userModelList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("username", userModel.getUsername());
            map.put("endUserCoin", userModel.getCoin());
            if (userModel.getCreatedby().equals("AUTO")) {
                map.put("agentCoin", "-");
                map.put("adminCoin", userModel.getCoin());
            } else {
                map.put("agentCoin", ((userModel.getCoin() * 10) / 100));
                map.put("adminCoin", ((userModel.getCoin() - (userModel.getCoin() * 10) / 100)));
            }
            list.add(map);
        }
        return list;
    }
}

