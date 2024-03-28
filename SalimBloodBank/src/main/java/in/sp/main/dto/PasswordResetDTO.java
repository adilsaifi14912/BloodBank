package in.sp.main.dto;


import javax.validation.constraints.NotEmpty;

public class PasswordResetDTO{

    @NotEmpty(message = "user email cannot be empty or null")
    private String userEmail;

    @NotEmpty(message = "password cannot be empty or null")
    private String password;

    @NotEmpty(message = "password cannot be empty or null")
    private String confirmPassword;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String username) {
        this.userEmail = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
