package com.example.schoolproject.teacher.service;

import com.example.schoolproject.student.mapper.Mapper;
import com.example.schoolproject.teacher.dto.TeacherDto;
import com.example.schoolproject.teacher.mapper.TeacherMapper;
import com.example.schoolproject.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService {

    private static Logger logger = LoggerFactory.getLogger(TeacherService.class);
    private final TeacherRepository teacherRepository;

    public final TeacherMapper mapper;

    public List<TeacherDto> findAll(){
        log.info("Get all students");
        log.debug("Get all students");
        return teacherRepository.findAll().stream().map()

    }




}





