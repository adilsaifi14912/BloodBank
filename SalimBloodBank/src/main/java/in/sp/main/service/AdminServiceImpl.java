package in.sp.main.service;

import in.sp.main.dao.BloodBankRepository;
import in.sp.main.dao.UserRepository;
import in.sp.main.dto.BloodBankDTO;
import in.sp.main.dto.RegisterDTO;
import in.sp.main.entities.BloodBankModel;
import in.sp.main.entities.UserModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;

@Service
class AdminServiceImpl implements AdminService{

    @Autowired
    BloodBankRepository bloodBankRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<BloodBankDTO> fetchBloodRequests() {
        List<BloodBankDTO> bloodRequests = new ArrayList<>();
        Iterable<BloodBankModel> requests =  bloodBankRepository.findAll();
        for(BloodBankModel request:requests)
        {
            BloodBankDTO dto = convertToBloodBankDTO(request);
            dto.setUser(convertToRegisterDTO(userRepository.findById(request.getUser().getId()).get()));
            bloodRequests.add(dto);
        }

        return bloodRequests;
    }

    @Override
    public List<RegisterDTO> fetchSignedupUsers() {
        List<RegisterDTO> signedupUsers = new ArrayList<>();
        Iterable<UserModel> users = userRepository.findAll();
        for (UserModel user : users) {
//            if (user.getRole().equalsIgnoreCase("user")) {
//                signedupUsers.add(convertToRegisterDTO(user));
//            }
            signedupUsers.add(convertToRegisterDTO(user));
        }
        return signedupUsers;
    }

    @Override
    public List<BloodBankDTO> fetchBloodRequest() {
        List<BloodBankDTO> bloodRequestDTOs = new ArrayList<>();
        Iterable<BloodBankModel> bloodRequests = bloodBankRepository.findAll();
//        System.out.println(bloodRequests+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (BloodBankModel bloodRequest : bloodRequests) {
            bloodRequestDTOs.add(convertToBloodBankDTO(bloodRequest));
        }
        return bloodRequestDTOs;
    }

    @Override
    public List<UserModel> sortUsers(String sortBy) {
        List<UserModel> allUsers = (List<UserModel>) userRepository.findAll();
        return sortUserModels(allUsers, sortBy);
    }

    private List<UserModel> sortUserModels(List<UserModel> userModels, String sortBy) {
        Comparator<UserModel> comparator = getComparator(sortBy);
        return userModels.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private Comparator<UserModel> getComparator(String sortBy) {
        Comparator<UserModel> comparator;
        switch (sortBy) {
            case "name":
                comparator = Comparator.comparing(i->i.getUsername().toLowerCase());
                break;
            case "CreatedBy":
                comparator = Comparator.comparing(UserModel::getCreatedBy);
                break;
            case "BloodGroup":
                comparator = Comparator.comparing(UserModel::getBloodGroup);
                break;
            // Add more cases for other fields if needed
            default:
                // Default sorting by name
                comparator = Comparator.comparing(UserModel::getUsername);
                break;
        }
        return comparator;
    }
    @Override
    public List<UserModel> filterUsers(String filterBy) {
        List<UserModel> allUsers = (List<UserModel>) userRepository.findAll();
        return filterUserModels(allUsers, filterBy);
    }

    private List<UserModel> filterUserModels(List<UserModel> userModels, String filterBy) {
        switch (filterBy) {
            case "byAgent":
                return userModels.stream()
                        .filter(user -> user.getRole().equalsIgnoreCase("agent"))
                        .collect(Collectors.toList());

            case "byUserName":


            case "createdAt":
                // Implement filtering logic for "createdAt" if needed
                break;
            // Add more cases for other filter criteria if needed
            default:
                // Return all users if no valid filter criteria provided
                return userModels;
        }
        return userModels; // Return all users if no specific filtering is done
    }

public BloodBankDTO convertToBloodBankDTO(BloodBankModel bloodBankModel)
{
    return modelMapper.map(bloodBankModel, BloodBankDTO.class);
}
public RegisterDTO convertToRegisterDTO(UserModel userModel)
{
    return modelMapper.map(userModel, RegisterDTO.class);
}
}


