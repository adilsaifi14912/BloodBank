package BloodBank.Services;

import BloodBank.Entity.BloodInfoModel;
import BloodBank.Entity.UserRequestDTO;
import BloodBank.Entity.UserRequestModel;
import BloodBank.Repository.BloodInfoRepository;
import BloodBank.Repository.RequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class BloodInfoServices {

    @Autowired
    BloodInfoRepository bloodInfoRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    ModelMapper modelMapper;
    public HashMap<String,Integer> bloodstock(){
        List<BloodInfoModel> all= bloodInfoRepository.findAll();
        HashMap<String,Integer> map=new HashMap<>();
        for(BloodInfoModel bloodInfoModel:all){
            map.put(bloodInfoModel.getBloodgroup(),bloodInfoModel.getStockInUnit());
        }
        return map;
    }
    public UserRequestDTO convertToRequestDTO(UserRequestModel userRequestModel) {
        if (userRequestModel != null) {
            return modelMapper.map(userRequestModel, UserRequestDTO.class);
        } else {
            return null;
        }
    }
    @Transactional
    public void updateStock(Long reqId){
        UserRequestModel userRequestModel=requestRepository.getById(reqId);
        if(userRequestModel != null) {
        int quantity=bloodInfoRepository.findByBloodGroup(userRequestModel.getBloodGroup()).getStockInUnit();
        if(userRequestModel.getType().equals("Donate")){
            quantity=quantity+userRequestModel.getQuantity();
            bloodInfoRepository.stockUpdate(quantity, userRequestModel.getBloodGroup());
            bloodInfoRepository.DateUpdate(LocalDateTime.now(), userRequestModel.getBloodGroup());
        }
        else{
            quantity=quantity- userRequestModel.getQuantity();
            bloodInfoRepository.stockUpdate(quantity, userRequestModel.getBloodGroup());
            bloodInfoRepository.DateUpdate(LocalDateTime.now(), userRequestModel.getBloodGroup());
        }
        }
    }
}
