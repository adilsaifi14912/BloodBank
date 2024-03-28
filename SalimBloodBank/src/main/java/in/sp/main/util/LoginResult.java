package in.sp.main.util;

import in.sp.main.dto.RegisterDTO;
import org.springframework.stereotype.Component;

@Component
public class LoginResult {
    private String status;
    private RegisterDTO user;
    private int blockStatus;


    public String getStatus() {
        return status;
    }

    public void setStatus(String  status) {
        this.status = status;
    }

    public RegisterDTO getUser() {
        return user;
    }

    public void setUser(RegisterDTO user) {
        this.user = user;
    }

    public int getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(int blockStatus) {
        this.blockStatus = blockStatus;
    }
}