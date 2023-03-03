package com.example.schoolproject.restApi.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;

@Table(name = "student")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;

    private String lastName;
    private int age;



//    @ManyToOne
//    @JoinColumn(name = "teacher_id")
//    private Teacher teacher;


    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
