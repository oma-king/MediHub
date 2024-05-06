package com.kingoma.dtos;

import com.kingoma.entities.MedicalFile;
import com.kingoma.enums.AccountType;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientFormData {
    private String idCard;
    private String gender;
    private String lastName;
    private String firstName;
    private String phone1;
    private String phone2;
    private String email;
    private String password;
    private AccountType accountType;
    private Date birthDate;
    private String address;
    private String city;
    private String socialSecurityType;
}
