package in.sp.main.entities;

import javax.persistence.*;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Entity
@Component
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String username;

    @Column
    private String role;

    @Column
    private String password;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private long phoneNumber;

    @Column
    private String city;

    @Column
    private String bloodGroup;

    @Column
    private LocalDate createdOn;

    @Column
    private String createdBy;

    @Column
    private String blockStatus;

    @Column
    private String userEmail;

    @Column
    private String gender;

    @Column
    private float commission;


    public UserModel() {
    }

    public UserModel(int id, String username, String role, String password, LocalDate dateOfBirth,
                     long phoneNumber, String city, String bloodGroup, LocalDate createdOn, String createdBy,
                     String blockStatus, String userEmail, String gender) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.bloodGroup = bloodGroup;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.blockStatus = blockStatus;
        this.userEmail = userEmail;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(String blockStatus) {
        this.blockStatus = blockStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    public String toString()
    {
        return username;
    }
}