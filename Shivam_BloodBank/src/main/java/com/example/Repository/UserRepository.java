package com.example.Repository;

import com.example.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel,Long> {
    @Query(value = "SELECT * FROM user_model WHERE username = :username and password= :password", nativeQuery = true)
    UserModel checkIfExist(String username,String password);

}

