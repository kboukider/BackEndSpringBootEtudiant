package com.example.demo;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class BackEndAppApplication implements CommandLineRunner {

	@Autowired
	private EtudiantRepository  etudiantRepository;
	
	@Autowired
	private FormationtRepository formationRepository;
	
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BackEndAppApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.setReturnBodyOnCreate(true);
		repositoryRestConfiguration.setReturnBodyOnUpdate(true);
		repositoryRestConfiguration.exposeIdsFor(Formation.class, Etudiant.class);
		repositoryRestConfiguration.getCorsRegistry()
		.addMapping("/**")
		.allowedOrigins("*")
		.allowedHeaders("*")
		.allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
		
		Formation f1 = formationRepository.save(new Formation(null, "php", 20, null ) );
		Formation f2 = formationRepository.save(new Formation(null, "java", 20, null));
		Formation f3 = formationRepository.save(new Formation(null, "HTML", 20, null));
				
		
		etudiantRepository.save(new Etudiant(null, "koussaila", "koussaila", new Date(), f1));
		etudiantRepository.save(new Etudiant(null, "koko", "koko", new Date(), f1));
		etudiantRepository.save(new Etudiant(null, "david", "david", new Date(), f2));
		etudiantRepository.save(new Etudiant(null, "axel", "axel", new Date(), f2));
		etudiantRepository.save(new Etudiant(null, "ferhat", "ferhat", new Date(), f3));
	}

}
