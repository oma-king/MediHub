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
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 8,max = 8)
    private String idCard;
    private String gender;
    @NotNull
    @Size(min = 2,max = 55)
    private String lastName;
    @NotNull
    @Size(min = 2,max = 55)
    private String firstName;
    @NotNull
    @Size(min = 8,max = 14)
    private String phone1;
    private String phone2;
    @Email
    private String email;
    @NotNull
    @Column(unique=true,nullable = false)
    private String username;
    private String password;
    @NotNull
    private String accountType;

    public User(String idCard, String gender, String lastName, String firstName, String phone1, String phone2, String email, String username, String password, String accountType) {
        this.idCard = idCard;
        this.gender = gender;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

}
