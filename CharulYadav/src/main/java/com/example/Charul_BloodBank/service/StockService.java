package com.example.Charul_BloodBank.service;

import com.example.Charul_BloodBank.model.StockModel;
import com.example.Charul_BloodBank.repository.BloodInventoryRepository;
import com.example.Charul_BloodBank.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    BloodInventoryRepository bloodInventoryRepository;
    public StockModel saveOrUpdateStock(StockModel stockModel) {
        stockModel.setLastUpdate(LocalDateTime.now());
        return stockRepository.save(stockModel);
    }

    public List<StockModel> getAllStocks() {
        return stockRepository.findAll();
    }

    public Map<String, Integer> getUnitsByBloodGroup() {
        List<StockModel> stockList = getAllStocks();
        return stockList.stream()
                .collect(Collectors.toMap(StockModel::getBloodGroup, StockModel::getUnits));
    }

    public void updateStockWithDonation(String bloodGroup, int unitsDonated, String type, String status) {
        StockModel stock = stockRepository.findByBloodGroup(bloodGroup)
                .orElseThrow(() -> new NoSuchElementException("Blood group not found"));
        if(type.equalsIgnoreCase("Donate") && status.equalsIgnoreCase("Accept")) {
            stock.setUnits(stock.getUnits() + unitsDonated);
            stock.setLastUpdate(LocalDateTime.now());
            stockRepository.save(stock);
        }
    }
    public List<String> getBloodGroups() {
        return stockRepository.findAllBloodGroups();
    }

    public int getCoinPerUnitByBloodGroup(String bloodGroup) {
        StockModel stock = stockRepository.findByBloodGroup(bloodGroup)
                .orElseThrow(() -> new NoSuchElementException("Blood group not found"));
        return stock.getCoinPerUnit();
    }

    public int getUnitsByBloodGroup(String bloodGroup) {
        StockModel stock = stockRepository.findByBloodGroup(bloodGroup)
                .orElseThrow(() -> new NoSuchElementException("Blood group not found"));
        return stock.getUnits();
    }
}
