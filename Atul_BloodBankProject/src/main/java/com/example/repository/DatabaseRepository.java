package com.example.repository;

import com.example.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface DatabaseRepository extends CrudRepository<UserModel,Long> {

}
