package com.kingoma.dtos;

import com.kingoma.entities.MedicalFile;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsultationFormData {

    @NotNull
    private String medicalFileId;

    @NotNull
    private Long doctorId;

    @NotEmpty
    @Size(max = 255)
    private String reason;

    @NotEmpty
    @Size(max = 1000)
    private String description;

    @NotNull
    private Date consultationDate;

    @NotNull
    private String consultationTime;

    @NotNull
    private double price;
}
