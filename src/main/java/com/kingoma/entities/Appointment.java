package com.kingoma.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Table(name= "Appointments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private Date appointmentDate;
    private String appointmentTime;
    private String description;
    private String symptoms;
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_file_id")
    private MedicalFile medicalFile;
}
