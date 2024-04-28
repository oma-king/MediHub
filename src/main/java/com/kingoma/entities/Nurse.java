package com.kingoma.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "Nurses")
@PrimaryKeyJoinColumn(name = "nurse_id")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Nurse extends User{

    private String shift;

    public Nurse(String idCard, String gender, String lastName, String firstName, String phone1, String phone2, String email, String username, String password, String accountType, String shift) {
        super(idCard, gender, lastName, firstName, phone1, phone2, email, username, password, accountType);
        this.shift = shift;
    }
}
