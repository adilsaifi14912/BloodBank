package com.BloodBank.Service;

import com.BloodBank.Model.BloodStockModel;
import com.BloodBank.Repository.BloodStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import java.util.NoSuchElementException;


@Service
public class BloodStockService {
    @Autowired
    public BloodStockRepository bloodStockRepository;
    public List<BloodStockModel> getBloodStock() {
        return bloodStockRepository.findAll();
    }

    @Transactional
    public void updateStock(String bloodGroup, int quantity) {
        List<BloodStockModel> list=getBloodStock();
        for(BloodStockModel entity:list)
        {
            if(entity.getBloodGroup().equals(bloodGroup))
                quantity=quantity+entity.getQuantity();
        }
        bloodStockRepository.updateTime(bloodGroup, LocalDateTime.now());
        bloodStockRepository.updateStock(bloodGroup, quantity);
    }

    public int getCoinPerUnitByBloodGroup(String bloodGroup) {
        BloodStockModel stock = bloodStockRepository.findByBloodGroup(bloodGroup)
                .orElseThrow(() -> new NoSuchElementException("Blood group not found"));
        return stock.getCoinsPerUnit();
    }
    public int getUnitsByBloodGroup(String bloodGroup) {
        BloodStockModel stock = bloodStockRepository.findByBloodGroup(bloodGroup)
                .orElseThrow(() -> new NoSuchElementException("Blood group not found"));
        return stock.getQuantity();
    }
    public List<String> getBloodGroups() {
        return bloodStockRepository.findAllBloodGroups();
    }
}


