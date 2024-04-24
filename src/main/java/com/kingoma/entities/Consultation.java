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
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int consultationId;

    private String reason;
    private Date consulationDate;
    private double price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescriptionId")
    private Prescription prescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalFileId")
    private MedicalFile medicalFile;
}
