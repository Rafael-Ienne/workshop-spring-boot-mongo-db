package com.rafaelienne.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelienne.workshopmongo.domain.User;
import com.rafaelienne.workshopmongo.services.UserService;

/*O @RestController indica que a classe será um recurso REST*/
@RestController
/*O @RequestMapping(value = "/users") indica o caminho do end point*/
@RequestMapping(value = "/users")
public class UserResource {

	/*O @Autowired instancia automaticamente um objeto na camada de resource, ou seja,
	 é um mecanismo de injeção de dependência automático*/
	@Autowired
	private UserService service;
	
	/*O findAll é um método que retorna todos os usuários*/
	/*O @GetMapping indica o método http que será usado para esse end point*/
	@GetMapping
	/*O ResponseEntity vai encapsular toda estrutura necessária para retornar respostas
	http já com cabeçalhos, erros, etc*/
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		/*O ok é um método que instancia o ResponseEntity já com o código de resposta http
		 que a resposta aconteceu com sucesso. O body define o corpo da resposta*/
		return ResponseEntity.ok().body(list);
	}
	
}
