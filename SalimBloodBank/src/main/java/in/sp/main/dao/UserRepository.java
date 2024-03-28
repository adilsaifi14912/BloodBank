package in.sp.main.dao;

import in.sp.main.entities.BloodBankModel;
import in.sp.main.entities.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
public interface UserRepository extends CrudRepository<UserModel, Integer>{
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByRole(String role);

    Optional<UserModel> findByUserEmail(String userEmail);
    List<UserModel> findAllByRoleOrderByUsernameAsc(String role);
    List<UserModel> findAllByRoleOrderByCreatedByAsc(String role);
    List<UserModel> findAllByRoleOrderByBloodGroupAsc(String role);
    List<UserModel> findAllByRoleOrderByUsernameDesc(String role);
    List<UserModel> findAllByRoleOrderByCreatedByDesc(String role);
    List<UserModel> findAllByRoleOrderByBloodGroupDesc(String role);
    List<UserModel> findByCreatedBy(String createdBy);
    List<UserModel> findAllByCreatedBy(String createdBy);
    //List<BloodBankModel>findAllBloodStock(String )
}

