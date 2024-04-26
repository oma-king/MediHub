package com.kingoma.dtos;

import java.util.Date;

public class PatientFormData {
    private String email;
    private String password;
    private String socialSecurityType;
    private Date birthdate;

    public PatientFormData(String email, String password, String socialSecurityType, Date birthdate) {
        this.email = email;
        this.password = password;
        this.socialSecurityType = socialSecurityType;
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocialSecurityType() {
        return socialSecurityType;
    }

    public void setSocialSecurityType(String socialSecurityType) {
        this.socialSecurityType = socialSecurityType;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
