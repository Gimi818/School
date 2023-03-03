package com.example.schoolproject.restApi.mapper;

import com.example.schoolproject.restApi.dto.AddStudentDto;
import com.example.schoolproject.restApi.dto.StudentDto;
import com.example.schoolproject.restApi.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapperStudent {
    MapperStudent MAPPER = Mappers.getMapper(MapperStudent.class);


    StudentDto entityMapToDto(Student student);

    Student dtoMapToEntity(AddStudentDto addTeacherDto);


}
