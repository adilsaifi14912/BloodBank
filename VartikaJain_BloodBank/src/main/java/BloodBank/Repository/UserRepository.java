package BloodBank.Repository;

import BloodBank.Entity.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsernameAndPassword(String username, String password);
    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.password = ?1 WHERE u.username = ?2")
    void updatePasswordByUsername(String password, String Username);



    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.firstTimeLogin = ?1 WHERE u.username = ?2")
    void updateFirstTimeLoginByUsername(boolean firstTimeLogin, String Username);

    UserModel findByUsername(String username);
}
