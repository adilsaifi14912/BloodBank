package com.insightgeeks.bloodbank.repository;

import com.insightgeeks.bloodbank.entities.BloodBank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BloodRepository extends CrudRepository<BloodBank, Integer> {

    @Query("SELECT b FROM BloodBank b WHERE b.userModel.createdBy = :agentName")
    List<BloodBank> findAllBloodRequestsCreatedByAgent(@Param("agentName") String agentName);
}
