package com.example.Repository;

import com.example.Model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BloodBankRepository extends CrudRepository<UserModel, Long> {

    @Query(value = "SELECT * FROM user_model WHERE username = :username and password= :password", nativeQuery = true)
    UserModel checkForLogin(String username, String password);

    @Query(value = "SELECT * FROM user_model WHERE username = :username", nativeQuery = true)
    UserModel checkForSignup(String username);

}

