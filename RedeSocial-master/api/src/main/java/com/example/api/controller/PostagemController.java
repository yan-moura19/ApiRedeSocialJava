package com.example.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Postagem;
import com.example.api.service.PostagemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/postagem", produces = "application/json")
public class PostagemController {
	
	@Autowired
	private PostagemService postagemService;
	
	@ApiOperation(value = "Retorna todas as postagens")
	@GetMapping()
	public ResponseEntity<List<Postagem>> listarTodos(){
		try
		{
			return new ResponseEntity<List<Postagem>>(postagemService.findAll(), HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<List<Postagem>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna todas as postagens de um usuário pelo id_Usuario")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping("/user/{id_Usuario}")
	public ResponseEntity<List<Postagem>> obterPorId(@PathVariable Long id_Usuario){
		try {
			return new ResponseEntity<List<Postagem>>(postagemService.findByUserId(id_Usuario), HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<List<Postagem>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Cria uma nova postagem")
	@PostMapping()
	public ResponseEntity<String> cadastrar(@RequestBody @Valid Postagem postagem){
		try {
			postagemService.cadastrar(postagem);
			return new ResponseEntity<String>("Postagem realizada com sucesso", HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
