package com.plataforma.cursos.api_cursos.exceptions;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException() {
        super("Curso já cadastrado com esse nome");
    }
    
}
