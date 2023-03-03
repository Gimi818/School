package com.example.schoolproject.globalExeption;

import com.example.schoolproject.restApi.exeption.StudentNotFoundException;
import com.example.schoolproject.restApi.exeption.TeacherNotFoundException;

import java.util.function.Supplier;

public class GlobalException {
    public static Supplier<StudentNotFoundException> studentNotFound(Long id) {
        return () -> new StudentNotFoundException(id);
    }

    public static Supplier<TeacherNotFoundException> teacherNotFound(Long id) {
        return () -> new TeacherNotFoundException(id);
    }

}
