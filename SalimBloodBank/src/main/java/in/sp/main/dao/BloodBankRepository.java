package in.sp.main.dao;

import in.sp.main.entities.BloodBankModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BloodBankRepository extends CrudRepository<BloodBankModel, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE BloodBankModel b SET b.coins = :coins WHERE b.id = :id")
    void updateCoinsById(Long id, int coins);

    @Transactional
    @Modifying
    @Query("UPDATE BloodBankModel b SET b.status = :status WHERE b.id = :id")
    void updateStatusById(Long id, String status);


    Optional<BloodBankModel> findById(Long id);

}
