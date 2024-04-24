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
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prescriptionId;

    private String title;
    private Date prescriptionDate;
    private String description;
    private String drugList;
}
