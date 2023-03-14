package com.example.schoolproject.restApi.mapper;

import com.example.schoolproject.restApi.dto.AddSchoolClassDto;
import com.example.schoolproject.restApi.dto.SchoolClassDto;
import com.example.schoolproject.restApi.entity.SchoolClass;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapperSchoolClass {


    MapperSchoolClass MAPPER = Mappers.getMapper(MapperSchoolClass.class);


    SchoolClassDto entityToDto(SchoolClass schoolClass);

    SchoolClass dtoToEntity(AddSchoolClassDto addSchoolClassDto);

}
