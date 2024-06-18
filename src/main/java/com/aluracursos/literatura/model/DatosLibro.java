package com.aluracursos.literatura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosPersona> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") int descargas){


@Override
public String toString() {
    return "---------DATOS DEL LIBRO--------\n" +
            "\nTitulo: " + titulo +
            "\nAutor: " + autor.toString() +
            "\nIdioma: " + idioma + "\n" +
            "Descargas: "+descargas +"\n";
}
}
