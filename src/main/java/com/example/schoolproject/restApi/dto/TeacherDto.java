package com.example.schoolproject.restApi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherDto{
    private Long id;

    private String firstName;
    private String lastName;

    private int age;

    private int yearsOfExperience;
}
