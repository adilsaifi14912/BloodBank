package BloodBank.Repository;

import BloodBank.Entity.BloodInfoModel;
import BloodBank.Entity.UserRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface BloodInfoRepository extends JpaRepository<BloodInfoModel,String> {

    @Transactional
    @Modifying
    @Query("UPDATE BloodInfoModel u SET u.stockInUnit=?1 WHERE bloodGroup=?2")
    void stockUpdate(int update,String group);

    @Transactional
    @Modifying
    @Query("UPDATE BloodInfoModel u SET u.update_on=?1 WHERE bloodGroup=?2")
    void DateUpdate(LocalDateTime updated_on, String group);

    BloodInfoModel findByBloodGroup(String bloodgroup);


}
