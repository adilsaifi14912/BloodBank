package in.sp.main.dto;

import javax.validation.constraints.NotEmpty;

public class LoginDTO {
    
    @NotEmpty(message = "userEmail cannot be empty or null")
    private String userEmail;
    
    @NotEmpty(message = "password cannot be empty or null")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
