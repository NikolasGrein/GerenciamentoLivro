package com.gerenciamentoLivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamentoLivro.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
