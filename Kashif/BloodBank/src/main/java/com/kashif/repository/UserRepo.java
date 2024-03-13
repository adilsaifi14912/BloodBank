package com.kashif.repository;

import com.kashif.entity.UserRegistration;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Query("SELECT u.password FROM UserRegistration u WHERE u.username = ?1")
    String getPasswordByUsername(String username);

    @Transactional
    @Query("SELECT u.blockedStatus FROM UserRegistration u WHERE u.username = ?1")
    boolean getBlockedStatusByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByRole(String role);
    List<UserRegistration> findAll();
    @Query("SELECT u FROM UserRegistration u WHERE u.role = 'EndUser'")
    List<UserRegistration> findAllEndUsers();

    Optional<UserRegistration> findByUsername(String username);
}
