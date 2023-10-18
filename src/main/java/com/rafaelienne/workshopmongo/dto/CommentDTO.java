package com.rafaelienne.workshopmongo.dto;

import java.io.Serializable;
import java.util.Date;

/*A classe CommentDTO permite projetar apenas alguns atributos de uma
 entidade pré-existente*/
/*O Serializable perite que objetos sejam convertidos em
bytes para serem trafegados em rede e gravados em arquivo*/
public class CommentDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String text;
	private Date date;
	private AuthorDTO author;
	
	public CommentDTO() {
		
	}

	public CommentDTO(String text, Date date, AuthorDTO author) {
		super();
		this.text = text;
		this.date = date;
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	
}
