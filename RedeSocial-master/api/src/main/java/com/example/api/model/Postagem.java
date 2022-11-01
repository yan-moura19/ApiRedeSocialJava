package com.example.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "postagem")
public class Postagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4235868489274346240L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column()
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_Usuario", nullable = false)
	@JsonIgnore
	private Usuario usuario;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "texto", nullable = false)
	private String texto;
}
