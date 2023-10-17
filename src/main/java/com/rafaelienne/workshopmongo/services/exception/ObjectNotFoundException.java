package com.rafaelienne.workshopmongo.services.exception;

/*A classe ObjectNotFoundException é uma exceção personalizada que será disparda
 quando houver a procura por um usuário pelo id e ele não ser encontrado*/
/*Usa-se o RuntimeException porque haverá o uso de uma classe auxiliar para
 tratar exceção*/
public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
}
