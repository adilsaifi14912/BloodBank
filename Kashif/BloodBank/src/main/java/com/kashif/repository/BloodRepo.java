package com.kashif.repository;

import com.kashif.entity.BloodStock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.List;


public interface BloodRepo extends CrudRepository<BloodStock, Long> {
    List<BloodStock> findAll();
    @Transactional
    @Modifying
    @Query("UPDATE BloodStock b SET b.status = ?1 WHERE b.id = ?2")
    void updateStatusById(String status, Long id);
}
