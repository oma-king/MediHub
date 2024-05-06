package com.kingoma.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentFormData {
    private Date appointmentDate;
    private String appointmentTime;
    private String description;
    private String symptoms;
    private Integer priority;
    private Long patientId;
    private Long doctorId;
    private Long nurseId;
    private String medicalFileId;
}