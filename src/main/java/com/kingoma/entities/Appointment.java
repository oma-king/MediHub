package com.kingoma.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {
    @Id
    @GeneratedValue
    private Long appointmentId;

    private Date consultationDate;
    private Date appointmentDate;
    private String description;
    private String symptoms;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurseId")
    private Nurse nurse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalFileId")
    private MedicalFile medicalFile;
}
