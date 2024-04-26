package com.kingoma.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name= "Patients")
@PrimaryKeyJoinColumn(name = "patient_id")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient extends User{

    private Date birthDate;
    private String address;
    private String city;
    private String socialSecurityType;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private MedicalFile medicalFile;


    public Patient(String email, String password, Date birthDate, String socialSecurityType) {
        super(email, password); // Initialize User fields using base class constructor
        this.birthDate = birthDate;
        this.socialSecurityType = socialSecurityType;
    }
}
