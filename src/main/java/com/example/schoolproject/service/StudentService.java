package com.example.schoolproject.service;

import com.example.schoolproject.dto.AddStudentDto;
import com.example.schoolproject.dto.StudentDto;
import com.example.schoolproject.entity.Student;
import com.example.schoolproject.globalExeption.GlobalException;
import com.example.schoolproject.mapper.Mapper;
import com.example.schoolproject.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentService {

    private static Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    private final Mapper mapper;

    public StudentService(StudentRepository studentRepository, Mapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    public List<StudentDto> findAll() {

        log.debug("Get all students");
        return studentRepository.findAll().stream()
                .map(mapper::entityToStudentDto)
                .collect(Collectors.toList());
    }

    public StudentDto findStudentById(Long id) {
        log.info("Get student by id");
        log.debug("Student id: " + id);
        Student student = studentRepository.findById(id).orElseThrow(GlobalException.studentNotFound(id));

        return mapper.entityToStudentDto(student);
    }

    public Student saveStudent(AddStudentDto addStudentDto) {

        log.info("Save Student");
        log.debug("AddStudentDto " + addStudentDto);
        Student student = studentRepository.save(mapper.dtoToStudent(addStudentDto));
        log.info("Student Saved");
        return student;

    }


    public void updateStudent(Long id, AddStudentDto addStudentDto) {

        log.info("Update Student");
        log.debug("AddStudentDto " + addStudentDto);
        Student student = studentRepository.findById(id).orElseThrow(GlobalException.studentNotFound(id));
        studentRepository.save(mapper.dtoToStudent2(student, addStudentDto));
        log.info("Student updated");
    }

    public void deleteStudent(Long id) {
        log.info("Delete Student");
        log.debug("Student id: " + id);
        Student student = studentRepository.findById(id).orElseThrow(GlobalException.studentNotFound(id));
        studentRepository.delete(student);
        log.info("Student deleted");
    }


}


//    public List<StudentDto> getAllStudents() {
//        List<Student> students = studentRepository.findAll();
//        return students.stream()
//                .map(StudentMapperStruct.INSTANCE::toDto)
//                .collect(Collectors.toList());
//    }
//
//    public Student addStudent(StudentDto studentDto) {
//        Student student = StudentMapperStruct.INSTANCE.toEntity(studentDto);
//        Student createdStudent = studentRepository.save(student);
//        return createdStudent;
//    }
//
//    public StudentDto getProductById(Long id) {
//        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
//        return StudentMapperStruct.INSTANCE.toDto(student);
//    }

//    public List<StudentDto> getAllStudents() {
//        List<Student> students = studentRepository.findAll();
//        return students.stream()
//                .map(StudentMapper.INSTANCE::entityToDto)
//                .collect(Collectors.toList());
//    }
//
//    public Student addStudent(StudentDto studentDto) {
//        Student student = StudentMapper.INSTANCE.dtoToEntity(studentDto);
//        Student createdStudent = studentRepository.save(student);
//        return createdStudent;
//    }


//    public StudentDto createDto(StudentDto studentDto) {
//        Student student = mapToEntity(studentDto);
//        Student newStudent = studentRepository.save(student);
//        StudentDto studentResponse = mapToDTO(newStudent);
//        return studentResponse;
//
//    }


// convert Entity into DTO

//    private StudentDto mapToDTO(Student student) {
//        StudentDto studentDto = mapper.map(student, StudentDto.class);
////        StudentDto studentDto1 = new StudentDto();
////        studentDto1.setId(student.getId());
////        studentDto1.setFirstName(student.getFirstName());
////        studentDto1.setLastName(student.getLastName());
////        studentDto1.setAge(student.getAge());
//        return studentDto;
//    }
//
//
//    // convert DTO to entity
//    private Student mapToEntity(StudentDto studentDto) {
//        Student student = mapper.map(studentDto, Student.class);
//        return student;
//    }
//
//
//
//    public StudentDto creteStudent(StudentDto studentDto) {
//        Student student = mapToEntity(studentDto);
//        Student newStudent = studentRepository.save(student);
//        StudentDto studentResponse = mapToDTO(newStudent);
//        return studentResponse;
//
//    }


