package com.example.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.Postagem;
import com.example.api.repository.PostagemRepository;

@Service
public class PostagemService {
	
	@Autowired
	PostagemRepository postagemRepository;
	
	public void cadastrar(Postagem postagem) {
		postagemRepository.save(postagem);
	}

	public List<Postagem> findAll() {
		return postagemRepository.findAll();
	}
	
	public List<Postagem> findByUserId(Long id_Usuario){
		return postagemRepository.findByUserId(id_Usuario);
	}
}
