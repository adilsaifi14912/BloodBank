package in.sp.main.dto;

import jakarta.validation.constraints.NotEmpty;

public class LoginDTO {

    @NotEmpty
    private String userEmail;

    @NotEmpty
    private String password;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
