package com.kingoma.entities;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.*;
@Entity
@Table(name= "Users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 8,max = 8)
    private String idCard;
    private String gender;
    private String lastName;
    private String firstName;
    private String phone1;
    private String phone2;
    @Email
    private String email;
    private String password;
    private String accountType;
}
