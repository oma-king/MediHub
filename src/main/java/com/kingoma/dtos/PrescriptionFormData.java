package com.kingoma.dtos;

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
public class PrescriptionFormData {

    @NotNull
    private Long consultationId;

    @NotEmpty
    @Size(max = 255)
    private String title;

    @NotNull
    private Date prescriptionDate;

    @NotEmpty
    @Size(max = 1000)
    private String description;

    @NotEmpty
    private String itemList;
}
