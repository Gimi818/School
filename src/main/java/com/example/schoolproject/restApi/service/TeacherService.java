package com.example.schoolproject.restApi.service;

import com.example.schoolproject.restApi.dto.AddTeacherDto;
import com.example.schoolproject.restApi.dto.TeacherDto;
import com.example.schoolproject.restApi.entity.Teacher;
import com.example.schoolproject.restApi.mapper.TeacherMapper;
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

    public final TeacherMapper mapper;


    public TeacherService(TeacherRepository teacherRepository, TeacherMapper mapper) {
        this.teacherRepository = teacherRepository;
        this.mapper = mapper;
    }

    public List<TeacherDto> findAll() {
        log.info("Get all students");
        log.debug("Get all students");
        return teacherRepository.findAll().stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    public TeacherDto findTeacherById(Long id) {
        log.info("Get teacher by id");
        log.debug("Teacher id: " + id);
        Teacher teacher = teacherRepository.findById(id).orElseThrow();

        return mapper.entityToDto(teacher);
    }

    public Teacher saveTeacher(AddTeacherDto addTeacherDto) {
        log.info("Save teacher");
        log.debug("AddTeacherDto" + addTeacherDto);
        Teacher teacher = teacherRepository.save(mapper.dtoToEntity(addTeacherDto));
        log.info("Teacher saved");
        return teacher;


    }

    public void updateTeacher(Long id, AddTeacherDto addTeacherDto) {
        log.info("Update Teacher");
        log.debug("Teacher id: " + id);
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        teacherRepository.save(mapper.dtoToEntity(teacher, addTeacherDto));
        log.info("Teacher updated");
    }

    public void deleteTeacher(Long id) {
        log.info("Delete teacher");
        log.debug("Teacher id: " + id);
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        teacherRepository.delete(teacher);
        log.info("Teacher deleted");

    }


}





