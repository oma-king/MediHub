package com.kingoma.entities;

import com.kingoma.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "Doctors")
@PrimaryKeyJoinColumn(name = "doctor_id")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Doctor extends User{

    private String speciality;

    public Doctor(String idCard, String gender, String lastName, String firstName, String phone1, String phone2, String email, String username, String password, AccountType accountType, String speciality) {
        super(idCard, gender, lastName, firstName, phone1, phone2, email, username, password, accountType);
        this.speciality = speciality;
    }
}