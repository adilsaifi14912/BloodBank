package com.example.Charul_BloodBank.repository;
import com.example.Charul_BloodBank.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsernameAndPassword(String username, String password);
    UserModel findByUsername(String username);
    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.locked = ?1 WHERE u.username = ?2")
    void updateLockedByUsername(boolean locked, String username);
    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.firstLogin = ?1 WHERE u.username = ?2")
    void updateFirstLogin(boolean firstLogin, String username);
    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.password = ?1 WHERE u.username = ?2")
    void updatePassword(String password, String username);
}
