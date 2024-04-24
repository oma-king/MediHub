package com.kingoma.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "Nurses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Nurse extends User{

    private String shift;
}
