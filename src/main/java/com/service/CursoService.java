package com.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.cursos.api_cursos.entities.CursoEntity;
import com.plataforma.cursos.api_cursos.exceptions.CourseAlreadyExistsException;
import com.plataforma.cursos.api_cursos.exceptions.CourseNotFoundException;
import com.plataforma.cursos.api_cursos.repositories.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    
    public CursoEntity executeCreate(CursoEntity cursoEntity) {
        if (this.cursoRepository.existsByName(cursoEntity.getName())) {
            throw new CourseAlreadyExistsException();
        }
        return this.cursoRepository.save(cursoEntity);
    }

  
    public List<CursoEntity> executeList(String name, String category) {
        if (name != null) {
            return this.cursoRepository.findByNameContainingIgnoreCase(name);
        }
        if (category != null) {
            return this.cursoRepository.findByCategory(category);
        }
        return this.cursoRepository.findAll();
    }


    public CursoEntity executeUpdate(UUID id, CursoEntity cursoEntity) {
        
        if (this.cursoRepository.existsByNameAndIdNot(cursoEntity.getName(), id)) {
            throw new CourseAlreadyExistsException();
        }

        return this.cursoRepository.findById(id).map(cursoExistente -> {
            cursoExistente.setName(cursoEntity.getName());
            cursoExistente.setCategory(cursoEntity.getCategory());
            return this.cursoRepository.save(cursoExistente);
        }).orElseThrow(CourseNotFoundException::new);
    }
    
    // 4. Método para Alternar Status (Toggle)
    public CursoEntity executeToggleActive(UUID id) {
        return this.cursoRepository.findById(id).map(curso -> {
            curso.setActivate(!curso.isActivate());
            return this.cursoRepository.save(curso);
        }).orElseThrow(CourseNotFoundException::new);
    }

    public void executeDelete(UUID id){
       if (!this.cursoRepository.existsById(id)) {
        throw new CourseNotFoundException();
    }
        this.cursoRepository.deleteById(id);
    }
}