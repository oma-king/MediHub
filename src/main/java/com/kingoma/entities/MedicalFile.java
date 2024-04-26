package com.kingoma.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name= "Medical_Files")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicalFile {
    @Id
    private String medicalFileId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany (mappedBy = "medicalFile", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();

    @OneToMany(mappedBy = "medicalFile", cascade = CascadeType.ALL)
    private List<Consultation> consultationList = new ArrayList<>();
}
