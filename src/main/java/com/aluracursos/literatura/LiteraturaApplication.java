package com.aluracursos.literatura;

import com.aluracursos.literatura.main.Principal;
import com.aluracursos.literatura.repository.LibroRepository;
import com.aluracursos.literatura.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;
	@Autowired
	private PersonaRepository repositoryPersona;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,repositoryPersona);
		principal.mostrarMenu();
	}
}
