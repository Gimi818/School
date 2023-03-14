package com.example.schoolproject.restApi.controller;

import com.example.schoolproject.restApi.dto.AddSchoolClassDto;
import com.example.schoolproject.restApi.dto.SchoolClassDto;
import com.example.schoolproject.restApi.entity.SchoolClass;
import com.example.schoolproject.restApi.service.SchoolClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchoolClassController {

    private final SchoolClassService schoolClassService;


    @PostMapping("/schoolClass")
    public ResponseEntity<SchoolClass> saveSchoolClass(@PathVariable AddSchoolClassDto addSchoolClassDto) {
        return new ResponseEntity<>(schoolClassService.saveSchoolClass(addSchoolClassDto), HttpStatus.CREATED);

    }


    @GetMapping
    public ResponseEntity<List<SchoolClassDto>> getAllSchoolClass() {
        List<SchoolClassDto> schoolClasses = schoolClassService.findAllSchoolClass();
        return ResponseEntity.status(HttpStatus.OK).body(schoolClasses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolClassDto> getSchoolClassById(@PathVariable Long id) {
        SchoolClassDto schoolClassDto = schoolClassService.findSchoolClassById(id);
        return ResponseEntity.status(HttpStatus.OK).body(schoolClassDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolClass> updateSchoolClass(@PathVariable Long id, @RequestBody AddSchoolClassDto addSchoolClassDto) {
        schoolClassService.updateSchoolClass(id, addSchoolClassDto);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchoolClass(@PathVariable Long id) {
        schoolClassService.deleteSchoolClass(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
