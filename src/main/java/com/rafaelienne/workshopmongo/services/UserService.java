package com.rafaelienne.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelienne.workshopmongo.domain.User;
import com.rafaelienne.workshopmongo.repositories.UserRepository;

/*O @Service indica que a classe UserService é um serviço que pode ser injetado em
 outras classes*/
@Service
public class UserService {
	
	/*O @Autowired instancia automaticamente um objeto na camada de serviço, ou seja,
	 é um mecanismo de injeção de dependência automático*/
	@Autowired
	private UserRepository rep;
	
	/*Operação que retorna todos os usuários do banco de dados*/
	public List<User> findAll(){
		return rep.findAll();
	}

}
