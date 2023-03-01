package com.example.schoolproject.restApi.mapper;

import com.example.schoolproject.restApi.dto.AddTeacherDto;
import com.example.schoolproject.restApi.dto.TeacherDto;
import com.example.schoolproject.restApi.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructTeacher {


    MapStructTeacher MAPPER = Mappers.getMapper(MapStructTeacher.class);

    TeacherDto entityMapToDto(Teacher teacher);



    Teacher dtoMapToEntity(AddTeacherDto addTeacherDto);

}
