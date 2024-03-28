package com.kashif.repository;

import com.kashif.entity.UserRegistration;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<UserRegistration, Integer> {


    @Transactional
    @Modifying
    @Query("UPDATE UserRegistration u SET u.password = ?1 WHERE u.username = ?2")
    void updatePasswordByUsername(String password, String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserRegistration u SET u.newUser = ?1 WHERE u.username = ?2")
    void updateNewUserByUsername(boolean newUser, String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserRegistration u SET u.blockedStatus = ?1 WHERE u.username = ?2")
    void updateBlockedStatusByUsername(boolean blockedStatus, String username);




    boolean existsByRole(String role);
    List<UserRegistration> findAll();



    Optional<UserRegistration> findByUsername(String username);
}
