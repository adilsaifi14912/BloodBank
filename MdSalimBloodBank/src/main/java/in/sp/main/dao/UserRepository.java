package in.sp.main.dao;

import in.sp.main.beans.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
//    Boolean findFirstLoginByUsername(String username);
//    @Transactional
//    @Modifying
//    @Query("UPDATE UserModel u SET u.firstLogin = :firstLogin WHERE u.username = :username")
//    void setFirstLoginByUsername(String username, boolean firstLogin);
}
