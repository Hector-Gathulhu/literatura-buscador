package com.aluracursos.literatura.main;

import com.aluracursos.literatura.model.*;
import com.aluracursos.literatura.repository.LibroRepository;
import com.aluracursos.literatura.repository.PersonaRepository;
import com.aluracursos.literatura.service.ConsumirApi;
import com.aluracursos.literatura.service.ConvertirJason;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";
    ConsumirApi consumirApi = new ConsumirApi();
    ConvertirJason convertir = new ConvertirJason();
    private LibroRepository repository;
    private PersonaRepository repositoryPersona;
    private List<Libro> libros;


    public Principal(LibroRepository repository, PersonaRepository repositoryPersona) {
        this.repository = repository;
        this.repositoryPersona = repositoryPersona;
    }


    public void mostrarMenu() {
        var json = consumirApi.obtenJson(URL_BASE);
        System.out.println(json);
        var opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                    ----------------------------------------------
                    Elija la opcion a traves de su numero.
                    1. Buscar libro por titulo
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    0. Salir
                    -----------------------------------------------
                    """);

            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    busquedaLibroPorTitulo();
                    break;
                case 2:
                    mostrarListaLibros();
                    break;
                case 3:
                    mostarListaAutores();
                    break;
                case 4:
                    mostrarAutoresPorAno();
                    break;
                case 5:
                    mostarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando aplicacion, Gracias or consultar :) ");
                    opcion =0;
                    break;

                default:
                    System.out.println("Opcion invalida!!");

            }
        }
    }


    private DatosLibro getLibrosPorTitulo() {
        System.out.println("Ingrese el titulo del libro que quiere consultar: ");
        var libro = teclado.nextLine();

        var json = consumirApi.obtenJson(URL_BASE + "?search=" + libro.replace(" ", "+"));
        //System.out.println("JSON: "+json);
        var datos = convertir.obternerDatos(json, Resultados.class);
        Optional<DatosLibro> busquedaLibro = datos.resultado().stream()
                .filter(l -> l.titulo().toUpperCase().contains(libro.toUpperCase()))
                .findFirst();

        if (busquedaLibro.isPresent()) {
            return busquedaLibro.get();
        } else {
            return null;
        }
    }

    private void busquedaLibroPorTitulo() {
        DatosLibro datosLibro = getLibrosPorTitulo();
        DatosPersona autor = datosLibro.autor().get(0);
        System.out.println(datosLibro);

        Persona datosDelAutor = new Persona(autor);
        repositoryPersona.save(datosDelAutor);
        Libro libro = new Libro(datosLibro,datosDelAutor);
        repository.save(libro);

    }

    private void mostrarListaLibros() {
        libros = repository.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    private void mostarListaAutores() {
        libros = repository.findAll();
        libros.stream()
                .forEach(a -> System.out.println("Autor: " + a.getAutor()));
    }

    private void mostrarAutoresPorAno(){
        System.out.println("Escriba el año en el que desea consultar los Autores disponible: ");
        int anoDeBusqueda = teclado.nextInt();

        List<Persona> autoresEncontrados = repositoryPersona.mostrarAutoresVivos(anoDeBusqueda);
        autoresEncontrados.forEach(a-> System.out.println("Autor: "+ a.getNombre()));
    }

    private void mostarLibrosPorIdioma(){
        System.out.println("Escriba el idioma para mostar los libros disponible: ");
        var idiomaBusqueda = teclado.nextLine();
        var idioma = Idioma.fromStringEspanol(idiomaBusqueda);
        List<Libro> libroPorIdioma = repository.findByIdioma(idioma);
        System.out.println("Los libros en "+idiomaBusqueda+" son: ");

        libroPorIdioma.forEach(System.out::println);

    }


}
