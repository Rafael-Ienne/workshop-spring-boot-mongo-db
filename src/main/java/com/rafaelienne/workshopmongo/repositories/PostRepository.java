package com.rafaelienne.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rafaelienne.workshopmongo.domain.Post;

import java.util.Date;
import java.util.List;


/*O PostRepository é uma interface que extende a interface MongoRepository e faz váris 
 operações básicas com Post (salvar, recuperar, deletar...)*/
/*O MongoRepository precisa do tipo da classe de domínio que ele irá gerenciar (Post)
 e o tipo do id da classe (String)*/
@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	/*Método que permite entrar com a consulta personalizada do MongoDB na forma de Json*/
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	/*Método para buscar posts contendo um dado string no título*/
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	/*Método que faz uma busca com mais critérios*/
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
}
