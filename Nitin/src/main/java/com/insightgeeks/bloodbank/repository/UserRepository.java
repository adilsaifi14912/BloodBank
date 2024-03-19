package com.insightgeeks.bloodbank.repository;

import com.insightgeeks.bloodbank.entities.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer>{
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByRole(String role);
}
