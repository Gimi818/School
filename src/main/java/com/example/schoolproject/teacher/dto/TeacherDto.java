package com.example.schoolproject.teacher.dto;

public record TeacherDto(Long id,
                         String firstName,
                         String lastName,
                         int age,
                         int yearsOfExperience) {
}
