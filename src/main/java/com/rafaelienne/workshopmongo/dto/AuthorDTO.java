package com.rafaelienne.workshopmongo.dto;

import java.io.Serializable;

import com.rafaelienne.workshopmongo.domain.User;

/*A classe AuthorDTO permite projetar apenas alguns atributos de uma
 entidade pré-existente*/
/*O Serializable perite que objetos sejam convertidos em
bytes para serem trafegados em rede e gravados em arquivo*/
public class AuthorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	public AuthorDTO() {
		
	}
	
	public AuthorDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
