package com.example.schoolproject.restApi.service;

import com.example.schoolproject.globalExeption.GlobalException;
import com.example.schoolproject.restApi.dto.AddStudentDto;
import com.example.schoolproject.restApi.dto.StudentDto;
import com.example.schoolproject.restApi.entity.Student;
import com.example.schoolproject.restApi.mapper.MapperStudent;
import com.example.schoolproject.restApi.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    private final MapperStudent mapperStudent;

    public StudentService(StudentRepository studentRepository, MapperStudent mapperStudent) {
        this.studentRepository = studentRepository;
        this.mapperStudent = mapperStudent;

    }

    public List<StudentDto> findAll() {

        log.debug("Get all students");
        return studentRepository.findAll().stream()
                .map(mapperStudent::entityMapToDto)
                .collect(Collectors.toList());
    }

    public StudentDto findStudentById(Long id) {
        log.info("Get student by id");
        log.debug("Student id: " + id);
        Student student = studentRepository.findById(id).orElseThrow(GlobalException.studentNotFound(id));


        return mapperStudent.entityMapToDto(student);
    }

    public Student saveStudent(AddStudentDto addStudentDto) {

        log.info("Save Student");
        log.debug("AddStudentDto " + addStudentDto);
        Student student = studentRepository.save(mapperStudent.dtoMapToEntity(addStudentDto));
        log.info("Student Saved");
        return student;

    }


    public Student updateStudent(Long id, AddStudentDto addStudentDto) {

        log.info("Update Student");
        log.debug("AddStudentDto " + addStudentDto);
        Student student = studentRepository.findById(id).orElseThrow(GlobalException.studentNotFound(id));
        student.setFirstName(addStudentDto.getFirstName());
        student.setLastName(addStudentDto.getLastName());
        student.setAge(addStudentDto.getAge());
        Student updatedStudent = studentRepository.save(student);
        log.info("Student updated");
        return mapperStudent.dtoMapToEntity(addStudentDto);
    }

    public void deleteStudent(Long id) {
        log.info("Delete Student");
        log.debug("Student id: " + id);
        Student student = studentRepository.findById(id).orElseThrow(GlobalException.studentNotFound(id));
        studentRepository.delete(student);
        log.info("Student deleted");
    }


}




