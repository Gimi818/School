package com.example.schoolproject.teacher.service;

import com.example.schoolproject.restApi.dto.AddTeacherDto;
import com.example.schoolproject.restApi.dto.TeacherDto;
import com.example.schoolproject.restApi.entity.Teacher;
import com.example.schoolproject.restApi.mapper.MapStructTeacher;
import com.example.schoolproject.restApi.mapper.TeacherMapper;
import com.example.schoolproject.restApi.repository.TeacherRepository;
import com.example.schoolproject.restApi.service.TeacherService;
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

import java.util.*;
import java.util.Optional;

/*
Klasa Service przymuje w konstruktorze repository daltego musimy zastosować
adnotacje @Mock na repository
@Mock tworzy makiete repository która jest nam potrzebna do prawidowego działania service
@InjectMocks tworzy instancję klasy i wstrzykuje do niej klasy opatrzone adnotacją @Mock.
- wywołujemy na service poniewaz bedziemy testowac ta klase/metody
 */
@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    TeacherMapper teacherMapper;
    MapStructTeacher mapStructTeacher;
    @InjectMocks
    private TeacherService teacherService;
    private AddTeacherDto addTeacherDto;
    private Teacher teacher;
    private TeacherDto teacherDto;

    @BeforeEach
    void setUp() {

        addTeacherDto = new AddTeacherDto("Adam", "Nowak", 54, 10);
        teacher = new Teacher(1L, "Adam", " Nowak", 54, 10);

    }

    @Test
    @DisplayName("Should save Teacher")
    void testSave() {
        BDDMockito.given(teacherRepository.save(teacherMapper.dtoToEntity(addTeacherDto)))
                .willReturn(teacher);
        Assertions.assertThat(teacherService.saveTeacher(addTeacherDto))
                .isEqualTo(teacher);
    }

    @Test
    @DisplayName("Should delete teacher")
    void deleteTeacher() {
        BDDMockito.given(teacherRepository.findById(1L)).willReturn(Optional.of(teacher));
        teacherService.deleteTeacher(1L);
        Mockito.verify(teacherRepository, Mockito.times(1)).delete(teacher);
    }


    @Test
    @DisplayName("Should find teacher by id")
    void findTeacher() {
        BDDMockito.given(teacherRepository.findById(1L)).willReturn(Optional.of(teacher));
        BDDMockito.given(teacherMapper.entityToDto(teacher))
                .willReturn(teacherDto);
        Assertions.assertThat(teacherService.findTeacherById(1L)).isEqualTo(teacherDto);

    }

    @Test
    @DisplayName("Should find all teachers")
    void allTeachers() {
        List<Teacher> teacherList = List.of(teacher);
        BDDMockito.given(teacherRepository.findAll()).willReturn(teacherList);
        teacherService.findAll();

        Mockito.verify(teacherMapper, Mockito.times(1)).entityToDto(teacher);
    }


    @Test
    @DisplayName("Should update teacher")
    void updateTeacher() {
        BDDMockito.given(teacherRepository.findById(1L)).willReturn(Optional.of(teacher));
        BDDMockito.given(teacherMapper.dtoToEntity(teacher, addTeacherDto)).willReturn(teacher);
        teacherService.updateTeacher(1L, addTeacherDto);
        Mockito.verify(teacherRepository, Mockito.times(1))
                .save(teacherMapper.dtoToEntity(teacher, addTeacherDto));
    }
}
