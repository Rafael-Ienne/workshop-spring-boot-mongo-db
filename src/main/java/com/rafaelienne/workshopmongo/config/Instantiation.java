package com.rafaelienne.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rafaelienne.workshopmongo.domain.Post;
import com.rafaelienne.workshopmongo.domain.User;
import com.rafaelienne.workshopmongo.dto.AuthorDTO;
import com.rafaelienne.workshopmongo.dto.CommentDTO;
import com.rafaelienne.workshopmongo.repositories.PostRepository;
import com.rafaelienne.workshopmongo.repositories.UserRepository;

/*A classe Instantiation é responsável por realizar a carga inicial da base de dados*/
/*o @Configuration indica que a classe é uma configuração para o Spring*/
@Configuration
public class Instantiation implements CommandLineRunner{

	/*O @Autowired instancia automaticamente um objeto na classe Instatiation, ou seja,
	 é um mecanismo de injeção de dependência automático*/
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		/*Limpa a coleção de usuários e posts no MongoDB*/
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		/*Salva os usuários no MongoDB*/
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2023"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("22/06/2023"),  "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		/*Estabelece comentários com seus autores aos posts*/
		post1.getComments().add(new CommentDTO("Aproveite!", sdf.parse("21/03/2023"), new AuthorDTO(alex)));
		post1.getComments().add(new CommentDTO("Boa viagem, mano!", sdf.parse("22/03/2023"), new AuthorDTO(bob)));
		post2.getComments().add(new CommentDTO("Tenha um ótimo dia!", sdf.parse("22/03/2023"), new AuthorDTO(alex)));
		
		/*Salva os posts no MongoDB*/
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		/*Estabelece a referência aos posts criados por maria*/
		maria.getPosts().add(post1);
		maria.getPosts().add(post2);
		
		/*Salva o User maria após fzer referências aos seus posts*/
		userRepository.save(maria);
		
	}

}
