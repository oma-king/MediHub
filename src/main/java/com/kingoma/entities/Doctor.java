package com.kingoma.entities;

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
}