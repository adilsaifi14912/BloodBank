package com.kashif.repository;
import com.kashif.entity.BloodStock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface BloodStockRepo extends CrudRepository<BloodStock, Long> {

    List<BloodStock> findAll();
    @Transactional
    @Modifying
    @Query("UPDATE BloodStock b SET b.unit = ?1, b.lastUpdate = ?2 WHERE b.bloodGroup = ?3")
    void updateBloodStockByBloodGroup(Long unit, Date lastUpdate, String bloodGroup);
}
