package in.sp.main.service;

import in.sp.main.dto.BloodBankDTO;
import in.sp.main.dto.RegisterDTO;
import in.sp.main.entities.BloodBankModel;
import in.sp.main.entities.UserModel;

import java.util.List;

public interface BloodBankService {
    public void bloodBankService(BloodBankDTO bloodBankDTO, RegisterDTO registerDTO);
   // List<BloodBankModel> getBloodStock();
   // List<BloodBankDTO> getBloodDataForAgent(String agentUsername);
    public int userTotalCoins(String userEmail);
    List<BloodBankDTO> agentCoins(String userName);
    List<BloodBankDTO> bloodRequestUserAgent(String userName);
    public List<BloodBankDTO> getBloodRequestUserDash(String userName) ;
    //public List<BloodBankDTO> filterByStatusAdmin(String userName) ;


}
