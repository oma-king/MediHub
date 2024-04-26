package com.kingoma.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "Consultations")
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
    private String consultationTime;
    private double price;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
    private List<Prescription> prescriptionList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_file_id")
    private MedicalFile medicalFile;
}
