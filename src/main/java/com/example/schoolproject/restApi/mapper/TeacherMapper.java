package com.example.schoolproject.restApi.mapper;

import com.example.schoolproject.restApi.dto.AddTeacherDto;
import com.example.schoolproject.restApi.dto.TeacherDto;
import com.example.schoolproject.restApi.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {


    public Teacher dtoToEntity(AddTeacherDto addTeacherDto) {
        return new Teacher(
                addTeacherDto.getFirstName(),
                addTeacherDto.getLastName(),
                addTeacherDto.getAge(),
                addTeacherDto.getYearsOfExperience()
        );
    }

    public Teacher dtoToEntity(Teacher teacher, AddTeacherDto addTeacherDto) {
        teacher.setFirstName(addTeacherDto.getFirstName());
        teacher.setLastName(addTeacherDto.getLastName());
        teacher.setAge(addTeacherDto.getAge());
        teacher.setYearsOfExperience(addTeacherDto.getYearsOfExperience());
        return teacher;
    }

    public TeacherDto entityToDto(Teacher teacher) {
        return new TeacherDto(

                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getAge(),
                teacher.getYearsOfExperience()
        );
    }


}
