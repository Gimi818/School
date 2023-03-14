package com.example.schoolproject.restApi.controller;

import com.example.schoolproject.restApi.dto.AddStudentDto;
import com.example.schoolproject.restApi.dto.StudentDto;
import com.example.schoolproject.restApi.entity.Student;
import com.example.schoolproject.restApi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {


    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<Student> saveStudent(@RequestBody AddStudentDto addStudentDto) {
        return new ResponseEntity<>(studentService.saveStudent(addStudentDto), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<StudentDto>> findAll() {
        List<StudentDto> allStudents = studentService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allStudents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudent(@PathVariable Long id) {
        StudentDto studentDto = studentService.findStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable Long id, @RequestBody AddStudentDto addStudentDto) {
        studentService.updateStudent(id, addStudentDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
