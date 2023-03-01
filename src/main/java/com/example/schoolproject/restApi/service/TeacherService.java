package com.example.schoolproject.restApi.service;

import com.example.schoolproject.restApi.dto.AddTeacherDto;
import com.example.schoolproject.restApi.dto.TeacherDto;
import com.example.schoolproject.restApi.entity.Teacher;
import com.example.schoolproject.restApi.mapper.MapStructTeacher;
import com.example.schoolproject.restApi.mapper.TeacherMapper;
import com.example.schoolproject.restApi.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j

public class TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);
    private final TeacherRepository teacherRepository;

    // public final TeacherMapper mapper;


    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherDto> findAll() {
        log.info("Get all students");
        log.debug("Get all students");
        return teacherRepository.findAll().stream()
                //   .map(mapper::entityToDto)
                .map(MapStructTeacher.MAPPER::entityMapToDto)
                .collect(Collectors.toList());
    }

    public TeacherDto findTeacherById(Long id) {
        log.info("Get teacher by id");
        log.debug("Teacher id: " + id);
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        return MapStructTeacher.MAPPER.entityMapToDto(teacher);
        //   return mapper.entityToDto(teacher);
    }

    public Teacher saveTeacher(AddTeacherDto addTeacherDto) {
        log.info("Save teacher");
        log.debug("AddTeacherDto" + addTeacherDto);
        //Teacher teacher = teacherRepository.save(mapper.dtoToEntity(addTeacherDto));
        Teacher teacher = teacherRepository.save(MapStructTeacher.MAPPER.dtoMapToEntity(addTeacherDto));
        log.info("Teacher saved");
        return teacher;


    }

    public Teacher updateTeacher(Long id, AddTeacherDto addTeacherDto) {
        log.info("Update Teacher");
        log.debug("Teacher id: " + id);
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        teacher.setFirstName(addTeacherDto.getFirstName());
        teacher.setLastName(addTeacherDto.getLastName());
        teacher.setAge(addTeacherDto.getAge());
        teacher.setYearsOfExperience(addTeacherDto.getYearsOfExperience());
        Teacher updatedTeacher = teacherRepository.save(teacher);
        // teacherRepository.save(MapStructTeacher.MAPPER.dtoMapToEntity( addTeacherDto));
        log.info("Teacher updated");
        return MapStructTeacher.MAPPER.dtoMapToEntity(addTeacherDto);
    }

    public void deleteTeacher(Long id) {
        log.info("Delete teacher");
        log.debug("Teacher id: " + id);
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        teacherRepository.delete(teacher);
        log.info("Teacher deleted");

    }


}





