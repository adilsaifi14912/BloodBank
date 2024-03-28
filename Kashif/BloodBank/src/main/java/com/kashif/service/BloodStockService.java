package com.kashif.service;
import com.kashif.dto.BloodStockDTO;
import com.kashif.entity.BloodStock;
import com.kashif.repository.BloodStockRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BloodStockService {
    @Autowired
    private BloodRequestService bloodRequestService;
    @Autowired
    private BloodStockRepo bloodStockRepo;

    @Autowired
    private BloodStock bloodStock;

    @Autowired
    UtilityService utilityService;
    @Autowired
    ModelMapper modelMapper;

    private void registerBloodStock(BloodStockDTO bloodStockDTO) {
        bloodStock.setBloodGroup(bloodStockDTO.getBloodGroup());
        bloodStock.setUnit(bloodStockDTO.getUnit());
        bloodStock.setLastUpdate(bloodStockDTO.getLastUpdate());
        bloodStockRepo.save(bloodStock);
    }

    private BloodStockDTO convertEntityToDto(BloodStock bloodStock) {
        return this.modelMapper.map(bloodStock, BloodStockDTO.class);
    }


    public List<BloodStockDTO> allStockList() {
        return bloodStockRepo.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public String updateBloodStock(long addBloodUnit, Date lastUpdate, String bloodGroup, String type, Long id) {
        Optional<String> username = bloodRequestService.getBloodBankList().stream()
                .filter(i->i.get("type").toString().equalsIgnoreCase("donor"))
                .filter(i -> i.get("id").equals(id))
                .map(i -> i.get("username").toString()).findFirst();
        String msg = "ok";
        if(username.isPresent())
            msg = utilityService.eligibleToDonate(username.get());
        if(!msg.equalsIgnoreCase("ok"))
            return msg;

        List<BloodStockDTO> list = allStockList()
                .stream().filter(i -> i.getBloodGroup()
                        .equalsIgnoreCase(bloodGroup)).collect(Collectors.toList());
        if (list.isEmpty()) {
            if (type.equalsIgnoreCase("receiver")) return "Insufficient Stock!";
            BloodStockDTO bloodStockDTO = new BloodStockDTO();
            bloodStockDTO.setUnit(addBloodUnit);
            bloodStockDTO.setLastUpdate(lastUpdate);
            bloodStockDTO.setBloodGroup(bloodGroup);
            registerBloodStock(bloodStockDTO);

        }
        long totalUnits = list.stream()
                .filter(i -> i.getBloodGroup().equalsIgnoreCase(bloodGroup))
                .mapToLong(BloodStockDTO::getUnit)
                .sum();
        if (type.equalsIgnoreCase("donor"))
            totalUnits += addBloodUnit;
        else totalUnits -= addBloodUnit;
        if (totalUnits >= 0)
            bloodStockRepo.updateBloodStockByBloodGroup(totalUnits, lastUpdate, bloodGroup);
        return totalUnits < 0 ? "Insufficient Stock!" : "ok";
    }


}
