package in.sp.main.service;

import javax.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
@Entity
@Table(name = "users_reg")
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private String password;
    private LocalDate dob;
    private String username;
    private String role;
    private String address;
    private Date creationTime;
    private String createdBy;
    private String modifyBy;
    private Date updatedTime;

    //    ---Three New Fields Added -----
    private boolean newUser;
    private boolean blockedStatus;
    private String bloodGroup;

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

    public boolean isBlockedStatus() {
        return blockedStatus;
    }

    public void setBlockedStatus(boolean blockedStatus) {
        this.blockedStatus = blockedStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreationTime() {
        return creationTime.toString();
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getUpdatedTime() {
        return updatedTime.toString();
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob.toString();
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
