package com.plataforma.cursos.api_cursos.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plataforma.cursos.api_cursos.entities.CursoEntity;

public interface CursoRepository extends JpaRepository<CursoEntity, UUID> {
     
    public  List<CursoEntity> findByNameContainingIgnoreCase (String name); 

    public List<CursoEntity> findByCategory (String category); 

    public boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);
    
}
