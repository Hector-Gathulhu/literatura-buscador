package com.aluracursos.literatura.repository;

import com.aluracursos.literatura.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    @Query("SELECT p FROM Persona p WHERE :anoDeBusqueda BETWEEN p.nacimiento AND p.muerte ")
    List<Persona> mostrarAutoresVivos(int anoDeBusqueda);
}
