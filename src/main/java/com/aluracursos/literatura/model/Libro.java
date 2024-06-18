package com.aluracursos.literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private int descargas;
    @ManyToOne
    private Persona autor;


    public Libro() {
    }

    public Libro(DatosLibro datosLibro, Persona datosDelAutor) {
        this.titulo = datosLibro.titulo();
        this.idioma = Idioma.fromString(datosLibro.idioma().get(0));
        this.autor = datosDelAutor;
        this.descargas = datosLibro.descargas();
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public String getAutor() {
        return autor.toString();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Persona autor) {
        this.autor = autor;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "Datos del Libro: " +
                "Titulo: " + titulo +
                ", Autor:" + autor +
                ", Idioma: " + idioma +
                " Descargas: " + descargas;
    }
}
