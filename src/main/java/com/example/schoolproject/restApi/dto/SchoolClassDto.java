package com.example.schoolproject.restApi.dto;

import com.example.schoolproject.restApi.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
public class SchoolClassDto {
    private Long id;
    private String name;
    private int numberOfStudents;
    Set<Student> studentList;
}
