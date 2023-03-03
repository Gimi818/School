package com.example.schoolproject.restApi.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AddTeacherDto{

    private String firstName;
    private String lastName;
    private  int age;
    private int yearsOfExperience;

}



