package BloodBank.Entity;

import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class BloodInfoModel {

    @Id
    String bloodGroup;
    int stockInUnit;
    int coinPerUnit;
    LocalDateTime update_on;

    public String getBloodgroup() {
        return bloodGroup;
    }

    public void setBloodgroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getStockInUnit() {
        return stockInUnit;
    }

    public void setStockInUnit(int stockInUnit) {
        this.stockInUnit = stockInUnit;
    }

    public int getCoinPerUnit() {
        return coinPerUnit;
    }

    public void setCoinPerUnit(int coinPerUnit) {
        this.coinPerUnit = coinPerUnit;
    }

    public LocalDateTime getUpdate_on() {
        return update_on;
    }

    public void setUpdate_on(LocalDateTime update_on) {
        this.update_on = update_on;
    }
}
