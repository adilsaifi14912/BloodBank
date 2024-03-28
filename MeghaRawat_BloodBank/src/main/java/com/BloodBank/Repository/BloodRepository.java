package com.BloodBank.Repository;

import com.BloodBank.Model.BloodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface BloodRepository extends JpaRepository<BloodModel,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE BloodModel b SET b.remark = ?2 WHERE b.id = ?1")
    void addRemark(Long requestId, String remark);

    @Transactional
    @Modifying
    @Query("UPDATE BloodModel b SET b.status = ?2 WHERE b.id = ?1")
    void updateStatus(Long requestId, String status);

    int countByBloodGroupAndStatus(String bloodGroup, String status);

    List<BloodModel> findByAgentAndBloodGroup(String agent,String bloodGroup);

    int countByAgentAndBloodGroupAndStatus(String agent,String  bloodGroup,String status);

    List<BloodModel> findByBloodGroup(String bloodGroup);

    List<BloodModel> findByAgent(String agentUsername);

    Optional<BloodModel> findTopByUsernameOrderByUpdatedAtDesc(String username);

    @Transactional
    @Modifying
    @Query("UPDATE BloodModel b SET b.updatedAt = ?2 WHERE b.id = ?1")
    void updateTime(Long requestId, LocalDateTime updatedAt);
}

