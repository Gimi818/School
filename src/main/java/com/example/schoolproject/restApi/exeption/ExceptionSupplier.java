package com.example.schoolproject.restApi.exeption;

import java.util.function.Supplier;

public class ExceptionSupplier {
    public static Supplier<StudentNotFoundException> employeeNotFound(Long id) {
        return () -> new StudentNotFoundException(id);
    }
}
