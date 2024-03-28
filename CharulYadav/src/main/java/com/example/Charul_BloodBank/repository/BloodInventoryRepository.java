package com.example.Charul_BloodBank.repository;


import com.example.Charul_BloodBank.model.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodInventoryRepository extends JpaRepository<BloodInventory, Long>
{
    List<BloodInventory> findByUseridCreatedBy(String username);

    Optional<BloodInventory> findTopByUseridUsernameAndTypeOrderByUpdatedAtDesc(String username, String type);

    int countByBloodGroupAndStatus(String bloodGroup, String accept);

    List<BloodInventory> findByUseridUsernameAndStatus(String username, String accept);

    //List<BloodInventory> findByBloodGroupAndCreatedByStartingWith(String bloodGroup, String agentUsername);
}
