package com.example.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.api.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	@Query(value = "select * from postagem where id_Usuario = ?", nativeQuery = true)
	public List<Postagem> findByUserId(Long id_Usuario);
	
}
