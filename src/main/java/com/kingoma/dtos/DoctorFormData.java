package com.kingoma.dtos;

import com.kingoma.entities.MedicalFile;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorFormData {
    private String idCard;
    private String gender;
    private String lastName;
    private String firstName;
    private String phone1;
    private String phone2;
    private String email;
    private String password;
    private String accountType;
    private String speciality;
}
