package com.example.Galley.DAO;

import java.util.Date;

public class UserRegistrationDAO {
    private String fullName;
    private Date dateOfBirth;
    private String username;
    private String password;

    public UserRegistrationDAO() {
    }

    public UserRegistrationDAO(String fullName, Date dateOfBirth, String username, String password) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
