package com.rafaelienne.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelienne.workshopmongo.domain.Post;
import com.rafaelienne.workshopmongo.repositories.PostRepository;
import com.rafaelienne.workshopmongo.services.exception.ObjectNotFoundException;

/*O @Service indica que a classe PostService é um serviço que pode ser injetado em
 outras classes*/
@Service
public class PostService {
	
	/*O @Autowired instancia automaticamente um objeto na camada de serviço, ou seja,
	 é um mecanismo de injeção de dependência automático*/
	@Autowired
	private PostRepository rep;
	
	/*Operação que retorna um Post com base no id*/
	public Post findById(String id) {
		Optional<Post> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

}
