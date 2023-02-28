package com.example.schoolproject.student.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

public class  AddStudentDto {

    private String firstName;

    private String lastName;
    private int age;

}
