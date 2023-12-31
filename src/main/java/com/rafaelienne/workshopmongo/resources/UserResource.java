package com.rafaelienne.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaelienne.workshopmongo.domain.Post;
import com.rafaelienne.workshopmongo.domain.User;
import com.rafaelienne.workshopmongo.dto.UserDTO;
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
	/*O @GetMapping indica o método http que será usado para esse end point (get)*/
	@GetMapping
	/*O ResponseEntity vai encapsular toda estrutura necessária para retornar respostas
	http já com cabeçalhos, erros, etc*/
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		/*Conversão de uma lista do tipo User para UserDTO*/
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		/*O ok é um método que instancia o ResponseEntity já com o código de resposta http
		 que a resposta aconteceu com sucesso. O body define o corpo da resposta*/
		return ResponseEntity.ok().body(listDto);
	}
	
	/*O findById é um método que retorna um usuário com base no id*/
	/*O @GetMapping indica o método http que será usado para esse end point (get)*/
	@GetMapping(value = "/{id}")
	/*O @PathVariable mostra que o id passado na url deve "casar" com o id como parâmetro*/
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = service.findById(id);
		/*O ok é um método que instancia o ResponseEntity já com o código de resposta http
		 que a resposta aconteceu com sucesso. O body define o corpo da resposta*/
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	/*O @PostMapping indica que será usado o método http post para adicionar um usuário*/
	@PostMapping
	/*O método insert insere um user no banco de dados*/
	/*O @RequestBody mostra que o objeto User vai chegar na forma de json e será desserializado na forma de objeto
	 User no Java*/
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
		User obj = service.fromDto(objDto);
		obj = service.insert(obj);
		/*Esse objeto do tipo URI é criado porque, ao se inserir um novo dado, é mais adequado retornar o código de
		 resposta 201 (código específico do http que significa que um novo recurso foi criado*/
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	/*O @DeleteMapping indica que será usado o método http delete para deletar um usuário*/
	/*O delete é um end point para deletar um usuário com base no id*/
	/*O (value = "/{id}") indica que o componente final do endereço passado na ferramenta de teste de solicitações
	 será usado como id para fazer a requisição*/
	@DeleteMapping(value = "/{id}")
	/*Usa-se void em ResponseEntity porque a resposta não vai retornar nenhum corpo*/
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		/*O .noContent() retorna o código 204 (deleção deu certo)*/
		return ResponseEntity.noContent().build();
	}
	
	/*O update é um end-point para atualizar um usuário com base no id. O @PutMapping indica que será 
	 usado o método http put para atualizar dados*/
	/*O (value = "/{id}") indica que o componente final do endereço passado na ferramenta de teste de solicitações
	 será usado como id para fazer a requisição*/
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody User obj){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/*O findPosts é um método que retorna os posts de um usuário com base no id*/
	/*O @GetMapping indica o método http que será usado para esse end point (get)*/
	@GetMapping(value = "/{id}/posts")
	/*O @PathVariable mostra que o id passado na url deve "casar" com o id como parâmetro*/
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User user = service.findById(id);
		/*O ok é um método que instancia o ResponseEntity já com o código de resposta http
		 que a resposta aconteceu com sucesso. O body define o corpo da resposta*/
		return ResponseEntity.ok().body(user.getPosts());
	}
	
}
