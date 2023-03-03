package com.example.schoolproject.restApi.service;

import com.example.schoolproject.globalExeption.GlobalException;
import com.example.schoolproject.restApi.dto.AddTeacherDto;
import com.example.schoolproject.restApi.dto.TeacherDto;
import com.example.schoolproject.restApi.entity.Teacher;
import com.example.schoolproject.restApi.mapper.MapStructTeacher;
import com.example.schoolproject.restApi.repository.TeacherRepository;
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
    private  final MapStructTeacher mapStructTeacher;


    public TeacherService(TeacherRepository teacherRepository, MapStructTeacher mapStructTeacher) {
        this.teacherRepository = teacherRepository;
        this.mapStructTeacher = mapStructTeacher;
    }

    public List<TeacherDto> findAll() {
        log.info("Get all students");
        log.debug("Get all students");
        return teacherRepository.findAll().stream()
                .map(mapStructTeacher::entityMapToDto)
                .collect(Collectors.toList());
    }

    public TeacherDto findTeacherById(Long id) {
        log.info("Get teacher by id");
        log.debug("Teacher id: " + id);
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(GlobalException.teacherNotFound(id));
        return mapStructTeacher.entityMapToDto(teacher);
    }

    public Teacher saveTeacher(AddTeacherDto addTeacherDto) {
        log.info("Save teacher");
        log.debug("AddTeacherDto" + addTeacherDto);
        Teacher teacher = teacherRepository.save(mapStructTeacher.dtoMapToEntity(addTeacherDto));
        log.info("Teacher saved");
        return teacher;


    }

    public Teacher updateTeacher(Long id, AddTeacherDto addTeacherDto) {
        log.info("Update Teacher");
        log.debug("Teacher id: " + id);
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(GlobalException.teacherNotFound(id));
        teacher.setFirstName(addTeacherDto.getFirstName());
        teacher.setLastName(addTeacherDto.getLastName());
        teacher.setAge(addTeacherDto.getAge());
        teacher.setYearsOfExperience(addTeacherDto.getYearsOfExperience());
        Teacher updatedTeacher = teacherRepository.save(teacher);
        log.info("Teacher updated");
        return mapStructTeacher.dtoMapToEntity(addTeacherDto);
    }

    public void deleteTeacher(Long id) {
        log.info("Delete teacher");
        log.debug("Teacher id: " + id);
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(GlobalException.teacherNotFound(id));
        teacherRepository.delete(teacher);
        log.info("Teacher deleted");

    }


}





