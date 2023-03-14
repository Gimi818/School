package com.example.schoolproject.restApi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class AddSchoolClassDto {


    private String name;
    private int numberOfStudents;
}
