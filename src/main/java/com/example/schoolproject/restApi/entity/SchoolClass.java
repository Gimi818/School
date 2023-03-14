package com.example.schoolproject.restApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolClass", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Student> studentList;

    public SchoolClass(String name, int numberOfStudents, Set<Student> studentList) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
        this.studentList = studentList;
    }
}
