package com.kingoma.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long consultationId;

    private String reason;
    private String description;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date consultationDate;
    private String consultationTime;
    private double price;

    @JsonBackReference
    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
    private List<Prescription> prescriptionList = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_file_id")
    private MedicalFile medicalFile;

    public Consultation(String reason, String description, Date consultationDate, String consultationTime, double price, Doctor doctor, MedicalFile medicalFile) {
        this.reason = reason;
        this.description = description;
        this.consultationDate = consultationDate;
        this.consultationTime = consultationTime;
        this.price = price;
        this.doctor = doctor;
        this.medicalFile = medicalFile;
    }


}
