package BloodBank.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;
    private Date createdOn;
    private String createdby;
    private String username;
    private Date dob;
    private String contactNumber;
    private String address;
    private String password;
    private String bloodGroup;
    private boolean firstTimeLogin;
    private boolean blockedStatus = false;

    public void setId(Long id) {
        this.id = id;
    }
    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date date) {
        this.createdOn=date;
    }
    public String getCreatedby() {
        return createdby;
    }
    public void setCreatedby(String auto) {
        this.createdby=auto;
    }
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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

    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public boolean isFirstTimeLogin() {
        return firstTimeLogin;
    }
    public void setFirstTimeLogin(boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }

    public Boolean getBlockedStatus() {
        return blockedStatus;
    }

    public void setBlockedStatus(Boolean blockedStatus) {
        this.blockedStatus = blockedStatus;
    }
}

