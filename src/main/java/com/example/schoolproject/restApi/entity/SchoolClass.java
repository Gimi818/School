package com.example.schoolproject.restApi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "schoolClass")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int numberOfStudents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Teacher> teachersList;

    public SchoolClass(String name, int numberOfStudents, Set<Teacher> teachersList) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
        this.teachersList = teachersList;
    }
}
