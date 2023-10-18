package com.rafaelienne.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rafaelienne.workshopmongo.domain.Post;
import java.util.List;


/*O PostRepository é uma interface que extende a interface MongoRepository e faz váris 
 operações básicas com Post (salvar, recuperar, deletar...)*/
/*O MongoRepository precisa do tipo da classe de domínio que ele irá gerenciar (Post)
 e o tipo do id da classe (String)*/
@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	/*Método que permite entrar com a consulta do MongoDB na forma de Json e fazer
	 alguma pesquisa*/
	@Query("{ 'Title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	/*Método para buscar posts contendo um dado string no título*/
	List<Post> findByTitleContainingIgnoreCase(String text);
	
}
