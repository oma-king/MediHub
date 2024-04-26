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
}
