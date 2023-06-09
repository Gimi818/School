package com.example.schoolproject.student.service;

import com.example.schoolproject.restApi.dto.AddStudentDto;
import com.example.schoolproject.restApi.dto.StudentDto;
import com.example.schoolproject.restApi.entity.Student;
import com.example.schoolproject.restApi.mapper.Mapper;
import com.example.schoolproject.restApi.repository.StudentRepository;
import com.example.schoolproject.restApi.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;




@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private Mapper mapper;

    @InjectMocks
    private StudentService studentService ;
    private AddStudentDto addStudentDto;
    private Student student ;
    private StudentDto studentDto;

    @BeforeEach
    void setUp() {
        addStudentDto = new AddStudentDto("Wojtek ", "Nowy", 28);
        student = new Student(1L, "Wojtek ", "Nowy", 28,null);
    }

    @Test
    void ShouldSaveStudent() {
        BDDMockito.given(studentRepository.save(mapper.dtoToStudent(addStudentDto)))
                .willReturn(student);
        Assertions.assertThat(studentService.saveStudent(addStudentDto))
                .isEqualTo(student);
    }
    @Test
    @DisplayName("Should  update student")
    void shouldUpdateStudent() {
        BDDMockito.given(studentRepository.findById(1L)).willReturn(Optional.of(student));
        BDDMockito.given(mapper.dtoToStudent2(student, addStudentDto)).willReturn(student);
        studentService.updateStudent(1L, addStudentDto);
        Mockito.verify(studentRepository, Mockito.times(1)).save(mapper.dtoToStudent2(student, addStudentDto));
    }
    @Test
    void shouldDeleteStudent() {
        BDDMockito.given(studentRepository.findById(1L)).willReturn(Optional.of(student));
        studentService.deleteStudent(1L);
        Mockito.verify(studentRepository, Mockito.times(1)).delete(student);

    }


    @Test
    void shouldFindStudentById() {
        BDDMockito.given(studentRepository.findById(1L)).willReturn(Optional.of(student));
        BDDMockito.given(mapper.entityToStudentDto(student))
                .willReturn(studentDto);

        Assertions.assertThat(studentService.findStudentById(1L)).isEqualTo(studentDto);
    }
//    @Test
//    void shouldFindStudentById2(){
//        StudentDto studentById = studentService.findStudentById(1L);
//        assertThat(studentById).isNotNull();
//        assertThat(studentById.getId()).isEqualTo(1L);
//    }


    @Test
    void ShouldFindAllStudents() {
        List<Student> students = List.of(student);
        BDDMockito.given(studentRepository.findAll()).willReturn(students);
        studentService.findAll();

        Mockito.verify(mapper, Mockito.times(1)).entityToStudentDto(student);
    }

}
