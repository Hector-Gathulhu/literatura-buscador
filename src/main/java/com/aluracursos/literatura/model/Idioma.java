package com.aluracursos.literatura.model;

public enum Idioma {

    ESPAÑOL ("es","Español"),
    INGLES ("en","Ingles");

    private String idiomaSeleccion;
    private String idiomaSeleccionEspanol;



    Idioma(String idioma,String idiomaSeleccionEspanol) {
        this.idiomaSeleccion = idioma;
        this.idiomaSeleccionEspanol = idiomaSeleccionEspanol;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaSeleccion.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Libros no encontrados en" + text);
    }

    public static Idioma fromStringEspanol(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaSeleccionEspanol.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Libros no encontrados en " + text);
    }
}
