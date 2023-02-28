package com.example.schoolproject.teacher.dto;

public record AddTeacherDto(
        String firstName,
        String lastName,
        int age,
        int yearsOfExperience) {
}
