package com.kingoma.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "Patients")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient extends User{

    private String birthDate;
    private String address;
    private String city;
    private String socialSecurityType;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private MedicalFile medicalFile;
}
