package com.rafaelienne.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelienne.workshopmongo.domain.User;
import com.rafaelienne.workshopmongo.dto.UserDTO;
import com.rafaelienne.workshopmongo.repositories.UserRepository;
import com.rafaelienne.workshopmongo.services.exception.ObjectNotFoundException;

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
	
	/*Operação que retorna um User com base no id*/
	public User findById(String id) {
		Optional<User> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	/*Método que insere um objeto User*/
	public User insert(User obj) {
		return rep.insert(obj);
	}
	
	/*Método que deleta um objeto User*/
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
	/*Método que transforma um objeto UserDTO em um User normal*/
	public User fromDto(UserDTO u) {
		return new User(u.getId(), u.getName(), u.getEmail());
	}

}
