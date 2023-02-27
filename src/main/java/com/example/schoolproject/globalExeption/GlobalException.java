package com.example.schoolproject.globalExeption;

import com.example.schoolproject.exeption.StudentNotFoundException;

import java.util.function.Supplier;

public class GlobalException {
    public static Supplier<StudentNotFoundException> studentNotFound(Long id) {
        return () -> new StudentNotFoundException(id);
    }
}
