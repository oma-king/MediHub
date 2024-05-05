package com.kingoma.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Table(name= "Prescriptions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    private String title;
    private Date prescriptionDate;
    private String description;
    private String itemList;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    public Prescription(String title, Date prescriptionDate, String description, String itemList, Consultation consultation) {
        this.title = title;
        this.prescriptionDate = prescriptionDate;
        this.description = description;
        this.itemList = itemList;
        this.consultation = consultation;
    }
}
