package com.insightgeeks.bloodbank.repository;

import com.insightgeeks.bloodbank.entities.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer>{
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByRole(String role);
    List<UserModel> findAllByRole(String role);
    List<UserModel> findAllByRoleOrderByUsernameAsc(String role);
    List<UserModel> findAllByRoleOrderByCreatedByAsc(String role);
    List<UserModel> findAllByRoleOrderByBloodGroupAsc(String role);
    List<UserModel> findAllByRoleOrderByUsernameDesc(String role);
    List<UserModel> findAllByRoleOrderByCreatedByDesc(String role);
    List<UserModel> findAllByRoleOrderByBloodGroupDesc(String role);
    List<UserModel> findByCreatedBy(String createdBy);
}
