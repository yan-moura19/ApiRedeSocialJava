package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.Usuario;
import com.example.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> findById(Long id){
		return usuarioRepository.findById(id);
	}
	
	public Usuario findUserByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}
