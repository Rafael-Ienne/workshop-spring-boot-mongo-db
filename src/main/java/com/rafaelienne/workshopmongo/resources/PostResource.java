package com.rafaelienne.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelienne.workshopmongo.domain.Post;
import com.rafaelienne.workshopmongo.resources.util.URL;
import com.rafaelienne.workshopmongo.services.PostService;

/*O @RestController indica que a classe será um recurso REST*/
@RestController
/*O @RequestMapping(value = "/posts") indica o caminho do end point*/
@RequestMapping(value = "/posts")
public class PostResource {

	/*O @Autowired instancia automaticamente um objeto na camada de resource, ou seja,
	 é um mecanismo de injeção de dependência automático*/
	@Autowired
	private PostService service;
	
	/*O findById é um método que retorna um post com base no id*/
	/*O @GetMapping indica o método http que será usado para esse end point (get)*/
	@GetMapping(value = "/{id}")
	/*O @PathVariable mostra que o id passado na url deve "casar" com o id como parâmetro*/
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = service.findById(id);
		/*O ok é um método que instancia o ResponseEntity já com o código de resposta http
		 que a resposta aconteceu com sucesso. O body define o corpo da resposta*/
		return ResponseEntity.ok().body(post);
	}
	
	/*O findById é um método que retorna um post com base no id*/
	/*O @GetMapping indica o método http que será usado para esse end point (get)*/
	@GetMapping(value = "/titlesearch")
	/*O @RequestParam(value = "text", defaultValue = "" mostra o nome do parâmetro que será passado na URI*/
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		/*Decodificação do parâmetro*/
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		/*O ok é um método que instancia o ResponseEntity já com o código de resposta http
		 que a resposta aconteceu com sucesso. O body define o corpo da resposta*/
		return ResponseEntity.ok().body(list);
	}
}
