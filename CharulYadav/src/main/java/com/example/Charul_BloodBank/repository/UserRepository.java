package com.example.Charul_BloodBank.repository;
import com.example.Charul_BloodBank.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
