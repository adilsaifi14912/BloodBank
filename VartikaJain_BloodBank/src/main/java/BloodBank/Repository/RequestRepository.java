package BloodBank.Repository;

import BloodBank.Entity.UserRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<UserRequestModel,Long> {

    @Override
    UserRequestModel getById(Long aLong);

    @Transactional
    @Modifying
    @Query("UPDATE UserRequestModel u SET u.status =?1 WHERE u.reqID =?2")
    void updateStatus(String status,Long reqId);

    @Transactional
    @Modifying
    @Query("UPDATE UserRequestModel u SET u.remark =?1 WHERE u.reqID =?2")
    void updateRemark(String Remark,Long reqId);

    UserRequestModel findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserRequestModel u SET u.updated_on = ?1 WHERE u.reqID = ?2")
    void DateUpdate(LocalDateTime updated_on, Long reqId);

    List<UserRequestModel> findByStatusAndBloodGroup(String status, String bloodGroup);
}
