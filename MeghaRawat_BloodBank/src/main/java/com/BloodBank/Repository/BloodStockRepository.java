package com.BloodBank.Repository;

import com.BloodBank.Model.BloodStockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BloodStockRepository extends JpaRepository<BloodStockModel,Long> {

    Optional<BloodStockModel> findByBloodGroup(String bloodGroup);

    @Transactional
    @Modifying
    @Query("UPDATE BloodStockModel b SET b.quantity = ?2 WHERE b.bloodGroup = ?1")
    void updateStock(String bloodGroup,int quantity);

    @Transactional
    @Modifying
    @Query("UPDATE BloodStockModel b SET b.lastUpdate = ?2 WHERE b.bloodGroup = ?1")
    void updateTime(String bloodGroup, LocalDateTime time);

    @Query("SELECT s.bloodGroup FROM BloodStockModel s")
    List<String> findAllBloodGroups();
}