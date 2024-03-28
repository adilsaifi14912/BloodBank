package com.BloodBank.Repository;

import com.BloodBank.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long>{
    UserModel findByUsernameAndPassword(String username, String password);
    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.password = ?1 WHERE u.username = ?2")
    void updatePasswordByUsername(String password, String Username);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.firstTimeLogin = ?1 WHERE u.username = ?2")
    void updateFirstTimeLoginByUsername(boolean firstTimeLogin, String Username);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.blockedStatus = ?1 WHERE u.username = ?2")
    void updateUserBlockedStatusByUsername(boolean blockedStatus, String Username);

    UserModel findByUsername(String username);
}
