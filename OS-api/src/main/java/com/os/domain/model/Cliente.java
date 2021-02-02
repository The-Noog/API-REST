package com.os.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
//@Entity = Define que a classe é um entidade/model
public class Cliente {

	@Id
	// @Id = Define que o atributo é um id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue = Define que a geração do valor do id da entidade será gerenciada pelo provedor de persistência (JPA)
	private Long id;
	
	@NotBlank
	// Não aceita null, nem vazio e nem espaços.
	@Size (max = 60)
	private String nome;
	
	@NotBlank
	@Email	
	//Anotação para saber se tem as caracteristicas de um email
	@Size (max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	//Define o tamanho minimo, ou máximo de caractere
	private String telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
