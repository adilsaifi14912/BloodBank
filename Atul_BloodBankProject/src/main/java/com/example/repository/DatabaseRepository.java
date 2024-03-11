package com.example.repository;

import com.example.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DatabaseRepository extends CrudRepository<UserModel,Long> {
    //---2nd approach instead of loop
//    @Query(value = "SELECT * FROM user_model WHERE user_Name = :username and password= :password", nativeQuery = true)
//    UserModel checkForLogin(String username, String password);
//
//    @Query(value = "SELECT * FROM user_model WHERE user_Name = :username", nativeQuery = true)
//    UserModel checkForSignup(String username);

}
