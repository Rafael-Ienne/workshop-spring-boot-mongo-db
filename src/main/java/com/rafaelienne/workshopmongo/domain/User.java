package com.rafaelienne.workshopmongo.domain;

import java.io.Serializable;
import java.util.Objects;

/*O Serializable perite que objetos sejam convertidos em
 bytes para serem trafegados em rede e gravados em arquivo*/
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
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
