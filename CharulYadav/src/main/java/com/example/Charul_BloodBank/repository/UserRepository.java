package com.example.Charul_BloodBank.repository;
import com.example.Charul_BloodBank.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsernameAndPassword(String username, String password);

    UserModel findByUsername(String username);
}
