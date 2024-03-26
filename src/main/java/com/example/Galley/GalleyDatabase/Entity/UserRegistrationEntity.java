package com.example.Galley.GalleyDatabase.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "userregistration")
public class UserRegistrationEntity {

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "dob")
    private String dob;

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public UserRegistrationEntity() {
    }

    public UserRegistrationEntity(String fullname, String dob, String username, String password) {
        this.fullname = fullname;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
