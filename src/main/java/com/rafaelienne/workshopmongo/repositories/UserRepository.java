package com.rafaelienne.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rafaelienne.workshopmongo.domain.User;

/*O UserRepository é uma interface que extende a interface MongoRepository e faz váris 
 operações básicas com User (salvar, recuperar, deletar...)*/
/*O MongoRepository precisa do tipo da classe de domínio que ele irá gerenciar (User)
 e o tipo do id da classe (String)*/
@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
