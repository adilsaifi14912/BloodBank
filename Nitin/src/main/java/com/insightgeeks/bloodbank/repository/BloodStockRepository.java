package com.insightgeeks.bloodbank.repository;

import com.insightgeeks.bloodbank.entities.BloodStock;
import org.springframework.data.repository.CrudRepository;

public interface BloodStockRepository extends CrudRepository<BloodStock, String> {
}
