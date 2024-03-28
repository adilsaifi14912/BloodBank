package in.sp.main.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RegisterDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String userEmail;

    @NotEmpty
    private  String password;

    @NotEmpty
    private String gender;

    @NotEmpty
    private String city;

    @NotNull
    private long phoneNumber;

    @NotEmpty
    private  String dateOfBirth;

    @NotEmpty
    private String bloodGroup;
    private int id;

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    private float commission;
    private String role;
    private LocalDate createdOn;
    private String createdBy;
    private String blockStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }




    public RegisterDTO() {
    }

    public RegisterDTO(String username, String userEmail, String password, String gender,
                       String city, long phoneNumber, String dateOfBirth, String bloodGroup) {
        this.username = username;
        this.userEmail = userEmail;
        this.password = password;
        this.gender = gender;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(String blockStatus) {
        this.blockStatus = blockStatus;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}