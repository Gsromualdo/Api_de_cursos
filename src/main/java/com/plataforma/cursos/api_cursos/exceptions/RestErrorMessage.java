package com.plataforma.cursos.api_cursos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestErrorMessage {
    private int status;
    private String message;
}
