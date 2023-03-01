package com.example.schoolproject.restApi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddTeacherDto {


    private String firstName;
    private String lastName;

    private int age;

    private int yearsOfExperience;
}
