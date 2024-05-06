package com.kingoma.dtos;

import com.kingoma.enums.AccountType;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NurseFormData {
    private String idCard;
    private String gender;
    private String lastName;
    private String firstName;
    private String phone1;
    private String phone2;
    private String email;
    private String password;
    private AccountType accountType;
    private String shift;
}
