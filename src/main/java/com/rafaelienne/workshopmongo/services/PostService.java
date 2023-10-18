package com.rafaelienne.workshopmongo.services;

import java.util.Date;
import java.util.List;
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
	
	/*Método que encontra um post com base em um texto que deve aparecer no título*/
	public List<Post> findByTitle(String title){
		return rep.searchTitle(title);
	}
	
	/*Método que encontra posts contendo um dado string em qualquer lugar (no título,
	 corpo ou comentários) e em um dado intervalo de datas*/
	public List<Post> fullSearch(String title, Date minDate, Date maxDate){
		/*Transformação que permite comparar até 24 horas depois do maxDate, porque o
		maxDate originalmente corresponde a meia-noite do dia especificado*/
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return rep.fullSearch(title, minDate, maxDate);
	}

}
