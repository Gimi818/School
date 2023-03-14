package com.example.schoolproject.restApi.service;

import com.example.schoolproject.restApi.dto.AddSchoolClassDto;
import com.example.schoolproject.restApi.dto.SchoolClassDto;
import com.example.schoolproject.restApi.entity.SchoolClass;
import com.example.schoolproject.restApi.mapper.MapperSchoolClass;
import com.example.schoolproject.restApi.repository.SchoolClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SchoolClassService {

    private static final Logger logger = LoggerFactory.getLogger(SchoolClassService.class);
    private final SchoolClassRepository schoolClassRepository;
    private final MapperSchoolClass mapper;


    public SchoolClassService(SchoolClassRepository schoolClassRepository, MapperSchoolClass mapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.mapper = mapper;
    }

    public SchoolClass updateSchoolClass(Long id, AddSchoolClassDto addSchoolClassDto) {

        log.info("Update SchoolClass");
        log.debug("Update SchoolClass id:" + id);
        logger.debug("addSchoolClassDto" + addSchoolClassDto);

        SchoolClass schoolClass = schoolClassRepository.findById(id).orElseThrow();
        schoolClass.setName(addSchoolClassDto.getName());
        schoolClass.setNumberOfStudents(addSchoolClassDto.getNumberOfStudents());

        SchoolClass updateSchoolClass = schoolClassRepository.save(schoolClass);
        log.info("School updated");
        return mapper.dtoToEntity(addSchoolClassDto);


    }

    public void deleteSchoolClass(Long id) {
        log.info("Delete SchoolClass");
        log.debug("SchoolClass id: " + id);
        SchoolClass schoolClass = schoolClassRepository.findById(id).orElseThrow();
        schoolClassRepository.delete(schoolClass);
        log.info("SchoolClass deleted");
    }

    public List<SchoolClassDto> findAllSchoolClass() {
        log.info("Get all schoolClass");
        return schoolClassRepository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    public SchoolClass saveSchoolClass(AddSchoolClassDto addSchoolClassDto) {
        log.info("Save SchoolClass");
        log.debug("AddSchoolClassDto" + addSchoolClassDto);
        SchoolClass schoolClass = schoolClassRepository
                .save(mapper.dtoToEntity(addSchoolClassDto));
        log.info("SchoolClass saved");
        return schoolClass;
    }

    public SchoolClassDto findSchoolClassById(Long id) {
        log.info("Find SchoolClass By id");
        log.debug("SchoolClass id:" + id);
        SchoolClass schoolClass = schoolClassRepository.findById(id).orElseThrow();
        return mapper.entityToDto(schoolClass);

    }

}
