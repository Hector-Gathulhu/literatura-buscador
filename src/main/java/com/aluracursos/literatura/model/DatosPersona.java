package com.aluracursos.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPersona (
        @JsonAlias("birth_year") int nacimiento,
        @JsonAlias("death_year") int muerte,
        @JsonAlias("name") String nombre
){

    @Override
    public String toString() {
        return nombre;
    }
}
