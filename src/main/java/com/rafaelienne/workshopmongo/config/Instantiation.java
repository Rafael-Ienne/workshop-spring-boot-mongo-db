package com.rafaelienne.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rafaelienne.workshopmongo.domain.User;
import com.rafaelienne.workshopmongo.repositories.UserRepository;

/*A classe Instantiation é responsável por realizar a carga inicial da base de dados*/
/*o @Configuration indica que a classe é uma configuração para o Spring*/
@Configuration
public class Instantiation implements CommandLineRunner{

	/*O @Autowired instancia automaticamente um objeto na classe Instatiation, ou seja,
	 é um mecanismo de injeção de dependência automático*/
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		/*Limpa a coleção no MongoDB*/
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		/*Salva os dados no MongoDB*/
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
	}

}
