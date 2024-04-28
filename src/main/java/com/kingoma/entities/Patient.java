package com.kingoma.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
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


    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String address;
    @NotNull
    @Size(min = 4,max= 30)
    private String city;
    @NotNull
    @Size(min = 4,max= 10)
    private String socialSecurityType;

    @JsonManagedReference
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MedicalFile medicalFile;

    public Patient(String idCard, String gender, String lastName, String firstName, String phone1, String phone2, String email, String username, String password, String accountType, Date birthDate, String address, String city, String socialSecurityType, MedicalFile medicalFile) {
        super(idCard, gender, lastName, firstName, phone1, phone2, email, username, password, accountType);
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.socialSecurityType = socialSecurityType;
        this.medicalFile = medicalFile;
    }
}
