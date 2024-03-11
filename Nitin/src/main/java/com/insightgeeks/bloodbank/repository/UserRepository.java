package com.insightgeeks.bloodbank.repository;

import com.insightgeeks.bloodbank.entities.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer>{

}
