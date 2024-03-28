package com.example.Charul_BloodBank.repository;

import com.example.Charul_BloodBank.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockModel, Long> {
    Optional<StockModel> findByBloodGroup(String bloodGroup);

    @Query("SELECT s.bloodGroup FROM StockModel s")
    List<String> findAllBloodGroups();
}
