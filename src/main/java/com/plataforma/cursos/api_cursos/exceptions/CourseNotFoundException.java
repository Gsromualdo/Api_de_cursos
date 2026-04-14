package com.plataforma.cursos.api_cursos.exceptions;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException() {
        super("Curso não encontrado");
    }
}
