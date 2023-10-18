package com.rafaelienne.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rafaelienne.workshopmongo.dto.AuthorDTO;
import com.rafaelienne.workshopmongo.dto.CommentDTO;

/*O Serializable perite que objetos sejam convertidos em
bytes para serem trafegados em rede e gravados em arquivo*/
/*O @Document indica que a classe Post é uma coleção do MongoDB*/
@Document(collection = "post")
public class Post implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*O @Id indica que o atributo id é uma chave*/
	@Id
	private String id;
	private Date date;
	private String title;
	private String body;
	private AuthorDTO author;
	/*A list de comments indica que um post terá comentários aninhados a ele*/
	private List<CommentDTO> comments = new ArrayList<>();
	
	public Post() {
		
	}

	public Post(String id, Date date, String title, String body, AuthorDTO author) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
	
	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}
}
