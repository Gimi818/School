package com.example.schoolproject.restApi.controller;

import com.example.schoolproject.restApi.dto.AddTeacherDto;
import com.example.schoolproject.restApi.dto.TeacherDto;
import com.example.schoolproject.restApi.entity.Teacher;
import com.example.schoolproject.restApi.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Teacher> saveTeacher(@RequestBody AddTeacherDto addTeacherDto) {
        return new ResponseEntity<>(teacherService.saveTeacher(addTeacherDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> findAll() {
        List<TeacherDto> allTeachers = teacherService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allTeachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> findTeacherById(@PathVariable Long id) {
        TeacherDto teacherDto = teacherService.findTeacherById(id);
        return ResponseEntity.status(HttpStatus.OK).body(teacherDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody AddTeacherDto addTeacherDto) {
        teacherService.updateTeacher(id, addTeacherDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
