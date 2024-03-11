package com.example.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long blood_bank_id;
    @Column
    @NotEmpty
    private String username;
    @Column
    @NotEmpty
    private String name;
    @Column
    @NotNull
    private Date dob;
    @Column
    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setDob(Date dob){this.dob=dob;}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    public Date getDob(){return dob;}
}
