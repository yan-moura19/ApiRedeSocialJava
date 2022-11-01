package com.example.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -267905641099980125L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column()
	@ApiModelProperty(value = "Identificador único para o Usuário")
	private long id;
	
	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@JsonIgnoreProperties("usuario")
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<Postagem> postagens;
	
}
