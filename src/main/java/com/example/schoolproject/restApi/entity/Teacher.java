package com.example.schoolproject.restApi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teachers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private int age;

    private int yearsOfExperience;


    public Teacher(String firstName, String lastName, int age, int yearsOfExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.yearsOfExperience = yearsOfExperience;
    }
}
