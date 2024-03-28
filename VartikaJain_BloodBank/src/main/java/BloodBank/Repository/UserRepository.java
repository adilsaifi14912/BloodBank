package BloodBank.Repository;

import BloodBank.Entity.UserModel;
import BloodBank.Entity.UserRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.password = ?1 WHERE u.username = ?2")
    void updatePasswordByUsername(String password, String Username);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.firstTimeLogin = ?1 WHERE u.username = ?2")
    void updateFirstTimeLoginByUsername(boolean firstTimeLogin, String Username);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.blockedStatus = ?1 WHERE u.username = ?2")
    void updateUserBlockedStatusByUsername(boolean blockedStatus, String Username);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.coin = ?1 WHERE u.username = ?2")
    void updateCoin(int coin, String Username);
    UserModel findByUsername(String username);

    }


