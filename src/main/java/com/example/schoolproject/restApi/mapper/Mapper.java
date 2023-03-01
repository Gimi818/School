package com.example.schoolproject.restApi.mapper;

import com.example.schoolproject.restApi.dto.AddStudentDto;
import com.example.schoolproject.restApi.dto.StudentDto;
import com.example.schoolproject.restApi.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class Mapper {


    public Student dtoToStudent(AddStudentDto addStudentDto) {
        return new Student(
                addStudentDto.getFirstName(),
                addStudentDto.getLastName(),
                addStudentDto.getAge()
        );
    }


    public Student dtoToStudent2(Student student, AddStudentDto addStudentDto) {
        student.setFirstName(addStudentDto.getFirstName());
        student.setLastName(addStudentDto.getLastName());
        student.setAge(addStudentDto.getAge());
        return student;
    }

    public StudentDto entityToStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAge());


    }

}
