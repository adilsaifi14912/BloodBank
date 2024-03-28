package com.blood.Shreya_BloodBank.Repository;


import com.blood.Shreya_BloodBank.Entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsernameAndPassword(String username, String password);
    UserModel findByUsername(String username);
}
