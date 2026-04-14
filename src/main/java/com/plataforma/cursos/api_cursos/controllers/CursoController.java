package com.plataforma.cursos.api_cursos.controllers;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plataforma.cursos.api_cursos.entities.CursoEntity;
import com.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {


    @Autowired
    private CursoService cursoService;

    @PostMapping("/")

    public CursoEntity create(@Valid @RequestBody CursoEntity cursoEntity) {
        return this.cursoService.executeCreate(cursoEntity);
    }

    @GetMapping("/")
    public List<CursoEntity> find(@RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
                return this.cursoService.executeList(name, category);

    }

    @PutMapping("/{id}")
    public CursoEntity update(@PathVariable UUID id,@Valid @RequestBody CursoEntity cursoEntity) {

        return this.cursoService.executeUpdate(id, cursoEntity);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.cursoService.executeDelete(id);
    }

    @PatchMapping("/{id}/active")
    public CursoEntity toggleActive(@PathVariable UUID id) {
        return this.cursoService.executeToggleActive(id);

    }
}
