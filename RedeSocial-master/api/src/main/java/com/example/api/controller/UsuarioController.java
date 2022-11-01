package com.example.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Usuario;
import com.example.api.service.UsuarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/usuario", produces = "application/json")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation(value = "Retorna todos os usuários")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping()
	public ResponseEntity<List<Usuario>> listarTodos(){
		try
		{
			return new ResponseEntity<List<Usuario>>(usuarioService.findAll(), HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<List<Usuario>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna um usuário por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> obterPorId(@PathVariable Long id){
		try {
			return new ResponseEntity<Optional<Usuario>>(usuarioService.findById(id), HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<Optional<Usuario>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Cadastra um usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 201, message = "Criado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PostMapping()
	public ResponseEntity<String> cadastrar(@RequestBody @Valid Usuario usuario){
		try {
			usuarioService.save(usuario);
			return new ResponseEntity<String>("Usuário cadastrado com sucesso", HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Realiza um login com email e senha do usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PostMapping("/login")
	public ResponseEntity<String> logar(@RequestBody @Valid Usuario usuario){
		
		Usuario usuarioEmailExiste = usuarioService.findUserByEmail(usuario.getEmail());
		String usuarioSenhaExiste = usuarioEmailExiste.getSenha();
		String senhaString = usuario.getSenha();
		
		if (usuarioEmailExiste.getEmail().isEmpty()) {
			return new ResponseEntity<String>("Email não encontrado!", HttpStatus.BAD_REQUEST);
		}else if (usuarioSenhaExiste.equals(senhaString)) {
			return new ResponseEntity<String>("Login efetuado com sucesso!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Senha incorreta!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Edita um usuário por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 201, message = "Criado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PutMapping("/{id}")
	public ResponseEntity<String> editar(@PathVariable(value="id") Long id, @RequestBody @Valid Usuario usuario) {
        Optional<Usuario> u = usuarioService.findById(id);
        try
		{
        	usuario.setId(u.get().getId());
        	usuarioService.save(usuario);
            return new ResponseEntity<String>("Usuário editado com sucesso", HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
