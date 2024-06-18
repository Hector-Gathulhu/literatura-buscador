package com.aluracursos.literatura.repository;

import com.aluracursos.literatura.model.Idioma;
import com.aluracursos.literatura.model.Libro;
import com.aluracursos.literatura.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {

    //@Query("SELECT b FROM Libro b WHERE b.idioma = :idiomaBusqueda")
    List<Libro> findByIdioma(Idioma idiomaBusqueda);

}
