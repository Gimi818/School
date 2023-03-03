package com.example.schoolproject.teacher.controller;


import com.example.schoolproject.restApi.controller.TeacherController;
import com.example.schoolproject.restApi.dto.AddTeacherDto;
import com.example.schoolproject.restApi.dto.TeacherDto;
import com.example.schoolproject.restApi.entity.Teacher;
import com.example.schoolproject.restApi.service.TeacherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.assertj.core.api.Assertions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TeacherController.class)
public class TeacherControllerTest {

    @MockBean
    private TeacherService teacherService;
    @Autowired
    private MockMvc mockMvc;


    private static AddTeacherDto addTeacherDto;
    private static String addTeacherDtoJson;
    private static Teacher teacher;
    private static TeacherDto teacherDto;
    private static TeacherDto secondTeacherDto;

    @BeforeAll
    static void setUp() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        addTeacherDto = new AddTeacherDto("Adam", "Stary", 50, 5);
        addTeacherDtoJson = objectMapper.writeValueAsString(addTeacherDto);
        teacherDto = new TeacherDto(1L, "Adam", "Stary", 50, 5);
        secondTeacherDto = new TeacherDto(2L, "Kamil", "test", 77, 10);

    }


    @Test
    @DisplayName("Should find all teachers")
    void findAllTeachers() throws Exception {
        java.util.List<TeacherDto> teacherDtoList = java.util.List.of(teacherDto, secondTeacherDto);
        BDDMockito.given(teacherService.findAll())
                .willReturn(teacherDtoList);

        MvcResult mvcResult = mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andReturn();

        String teacherDtoJson = mvcResult.getResponse().getContentAsString();
        java.util.List<Teacher> teacherResult = new ObjectMapper()
                .readValue(teacherDtoJson, new TypeReference<>() {
                });
        Assertions.assertThat(teacherResult).hasSize(2);

    }


    @Test
    @DisplayName("Should save teacher")
    void saveTeacher() throws Exception {
        BDDMockito.given(teacherService.saveTeacher(addTeacherDto)).willReturn(teacher);

        mockMvc.perform(post("/teachers")
                        .content(addTeacherDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void should_Find_Teacher() throws Exception {
        BDDMockito.given(teacherService.findTeacherById(1L)).willReturn(teacherDto);

        mockMvc.perform(get("/teachers/1"))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.firstName", Matchers.is("Adam")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Stary")))
                .andExpect(jsonPath("$.age", Matchers.is(50)))
                .andExpect(jsonPath("$.yearsOfExperience", Matchers.is(5)))
                .andExpect(status().is(200));

    }


    @Test
    void should_Delete_Teacher() throws Exception {
        mockMvc.perform(delete("/teachers/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(teacherService, Mockito.times(1))
                .deleteTeacher(1L);
    }

    @Test
    void should_Update_Teacher() throws Exception {

        mockMvc.perform(put("/teachers/1")
                        .content(addTeacherDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
