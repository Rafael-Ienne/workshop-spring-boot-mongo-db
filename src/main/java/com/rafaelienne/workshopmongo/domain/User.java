package com.rafaelienne.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/*O Serializable perite que objetos sejam convertidos em
 bytes para serem trafegados em rede e gravados em arquivo*/
/*O @Document indica que a classe User é uma coleção do MongoDB*/
@Document(collection = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*O @Id indica que o atributo id é uma chave*/
	@Id
	private String id;
	private String name;
	private String email;
	/*A annotatio @DBRef indica que o atributo posts está referenciando outra coleção
	 do MongoDB*/
	/*O atributo posts faz referência aos id's dos posts feitos pelo autor*/
	/*O parâmetro o (lazy = true) evita que todos os posts do usuário sejam carregados
	 automaticamente ao se fazer a consulta de um usuário. Assim, os posts serão
	 carregados apenas se eles forem acessados*/
	@DBRef (lazy = true)
	private List<Post> posts = new ArrayList<>();
	
	public User() {
		
	}
	
	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	/*Os objetos são comparados apenas com base no id*/
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
