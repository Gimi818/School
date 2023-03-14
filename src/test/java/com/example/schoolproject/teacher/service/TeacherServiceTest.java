package com.example.schoolproject.teacher.service;

import com.example.schoolproject.restApi.dto.AddTeacherDto;
import com.example.schoolproject.restApi.dto.TeacherDto;
import com.example.schoolproject.restApi.entity.Teacher;
import com.example.schoolproject.restApi.mapper.MapStructTeacher;
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

import java.util.List;
import java.util.Optional;





@ExtendWith({MockitoExtension.class})
public class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;
   @Mock

   MapStructTeacher MAPPER;
       //    = Mockito.mock(MapStructTeacher.class);
    @InjectMocks

    private TeacherService teacherService;
    private AddTeacherDto addTeacherDto;

    private Teacher teacher;
    private TeacherDto teacherDto;


    @BeforeEach
    void setUp() {

        addTeacherDto = new AddTeacherDto("John", "New", 54, 10);
        teacher = new Teacher(1L, "John", " New", 54, 10);
    }


    @Test
    @DisplayName("Should find teacher by id")
    void findTeacher() {

        BDDMockito.given(teacherRepository.findById(1L)).willReturn(Optional.of(teacher));
        BDDMockito.given(MAPPER.entityMapToDto(teacher))
                .willReturn(teacherDto);
        Assertions.assertThat(teacherService.findTeacherById(1L)).isEqualTo(teacherDto);

    }


    @Test

    @DisplayName("Should save Teacher")
    void testSave() {
        BDDMockito.given(teacherRepository.save(MAPPER.dtoMapToEntity(addTeacherDto)))
                .willReturn(teacher);
        Assertions.assertThat(teacherService.saveTeacher(addTeacherDto)).isEqualTo(teacher);
    }

    @Test
    @DisplayName("Should delete teacher")
    void deleteTeacher() {
        BDDMockito.given(teacherRepository.findById(1L)).willReturn(Optional.of(teacher));
        teacherService.deleteTeacher(1L);
        Mockito.verify(teacherRepository, Mockito.times(1)).delete(teacher);
    }




    @Test
    @DisplayName("Should find all teachers")
    void allTeachers() {
        List<Teacher> teacherList = List.of(teacher);
        BDDMockito.given(teacherRepository.findAll()).willReturn(teacherList);
        teacherService.findAll();

        Mockito.verify(MAPPER, Mockito.times(1)).entityMapToDto(teacher);
    }


    @Test
    @DisplayName("Should update teacher")
    void updateTeacher() {
        BDDMockito.given(teacherRepository.findById(1L)).willReturn(Optional.of(teacher));
        BDDMockito.given(MAPPER.dtoMapToEntity( addTeacherDto)).willReturn(teacher);
        teacherService.updateTeacher(1L, addTeacherDto);
        Mockito.verify(teacherRepository, Mockito.times(1))
                .save(MAPPER.dtoMapToEntity( addTeacherDto));
    }
}
