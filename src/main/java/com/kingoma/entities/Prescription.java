package com.kingoma.entities;

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
    private int prescriptionId;

    private String title;
    private Date prescriptionDate;
    private String description;
    private String itemList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;
}
