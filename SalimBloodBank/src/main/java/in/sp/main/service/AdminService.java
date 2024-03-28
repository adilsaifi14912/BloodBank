package in.sp.main.service;

import in.sp.main.dto.BloodBankDTO;
import in.sp.main.dto.RegisterDTO;
import in.sp.main.entities.UserModel;

import java.util.List;

public interface AdminService {

    public List<BloodBankDTO> fetchBloodRequests();
    public List<RegisterDTO> fetchSignedupUsers();
    //List<RegisterDTO> fetchSortedUserByName();

    public List<BloodBankDTO> fetchBloodRequest();
        // Method to sort users by a specified field
        List<UserModel> sortUsers(String sortBy);
        List<UserModel> filterUsers(String filterBy);

    }



