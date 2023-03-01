package com.example.schoolproject.student.controller;

import com.example.schoolproject.restApi.controller.StudentController;
import com.example.schoolproject.restApi.dto.AddStudentDto;
import com.example.schoolproject.restApi.dto.StudentDto;
import com.example.schoolproject.restApi.entity.Student;
import com.example.schoolproject.restApi.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = StudentController.class
)

public class StudentControllerTest {
    @MockBean
    private StudentService studentService;
    @Autowired
    private MockMvc mockMvc;

    private static AddStudentDto addStudentDto;
    private static String addStudentDtoJson;
    private static Student student;
    private static StudentDto studentDto;
    private static StudentDto secondStudentDto;

    @BeforeAll
    static void setUp() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        addStudentDto = new AddStudentDto("Wojtek", "Nowak", 30);
        addStudentDtoJson = objectMapper.writeValueAsString(addStudentDto);
        studentDto = new StudentDto(1L, "Wojtek", "Nowak", 30);
        secondStudentDto = new StudentDto(2L, "Adam", "Stary", 20);
    }


    @Test
    void shouldFindStudent() throws Exception {
        BDDMockito.given(studentService.findStudentById(1L)).willReturn(studentDto);

        mockMvc.perform(get("/students/1"))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.firstName", Matchers.is("Wojtek")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Nowak")))
                .andExpect(jsonPath("$.age", Matchers.is(30)))
                .andExpect(status().is(200));
    }

    @Test
    void shouldSaveStudent() throws Exception {
        BDDMockito.given(studentService.saveStudent(addStudentDto))
                .willReturn(student);

        mockMvc.perform(post("/students/add")
                        .content(addStudentDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_Find_All_Students() throws Exception {
        List<StudentDto> students = List.of(studentDto, secondStudentDto);
        BDDMockito.given(studentService.findAll())
                .willReturn(students);

        MvcResult mvcResult = mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andReturn();

        String studentDtoJson = mvcResult.getResponse().getContentAsString();
        List<Student> studentsResult = new ObjectMapper()
                .readValue(studentDtoJson, new TypeReference<>() {
                });
        Assertions.assertThat(studentsResult).hasSize(2);
    }

    @Test
    void should_Delete_Student() throws Exception {
        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(studentService, Mockito.times(1))
                .deleteStudent(1L);
    }

    @Test
    void should_Update_Student() throws Exception {

        mockMvc.perform(put("/students/1")
                        .content(addStudentDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

/* mockMvc.perform - wykonuje kontretne wywołania (post(/students/add))
    przez tą metode musimy dodać w teście "throws Execption"
    - .andExpect() - (jsonPath) - metoda która sprawdza nam metode
    $ - oznacza nasz obiekt który chemy sprawdzic
    Machers.is oczekująca wartość
    ObjectMapper - zmienia nam Jsona na obiekt
    objectMapper.writeValueAsString
 */


//    @Test
//    void should_Not_Find_Student_When_Student_Does_Not_Exist() throws  Exception{
//        BDDMockito.given(studentService.findStudentById(100L)).willThrow(new StudentNotFoundException(100L));
//
//        mockMvc.perform(get("/students/100"))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message", Matchers.is("Student with id 100 not found")));
//    }

}
