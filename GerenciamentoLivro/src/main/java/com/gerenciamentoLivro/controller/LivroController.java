package com.gerenciamentoLivro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamentoLivro.entities.Livro;
import com.gerenciamentoLivro.service.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Clientes", description = "API REST DE GERENCIAMENTO DE LIVROS")
@RestController
@RequestMapping("/livro")
public class LivroController {
	
	private final LivroService livroService;
	
	@Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }
	
	@GetMapping("/{id}")
	@Operation(summary = "Localiza livro por ID")
    public ResponseEntity<Livro> buscaLivroControlId(@PathVariable Long id) {
		Livro livro = livroService.buscaLivroId(id); 
        if (livro != null) {
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
        
    @GetMapping("/")
    @Operation(summary = "Apresenta todos os livros")
    public ResponseEntity<List<Livro>> buscaTodosLivroControl() { 
        List<Livro> livro = livroService.buscaTodosLivros();
        return ResponseEntity.ok(livro);
    }
        
    @PostMapping("/")
    public ResponseEntity<Livro> salvaLivroControl(@RequestBody Livro livro) { 
        Livro salvaLivro = livroService.salvaLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaLivro);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera um livro")
    public ResponseEntity<Livro> alteraLivroControl(@PathVariable Long id, @RequestBody Livro livro) { 
        Livro alteraLivro = livroService.alterarLivro(id, livro);
        if (alteraLivro != null) {
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um livro")
    public ResponseEntity<String> apagaLivroControl(@PathVariable Long id) { 
        boolean apagar = livroService.apagarLivro(id);
        if (apagar) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
