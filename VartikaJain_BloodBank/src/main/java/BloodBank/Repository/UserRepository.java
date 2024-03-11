package BloodBank.Repository;

import BloodBank.Entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsernameAndPasswordAndContactNumber(String username, String password, String contactNumber);
}
