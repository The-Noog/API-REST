package com.os.api.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.os.domain.model.Cliente;
import com.os.domain.repository.ClienteRepository;

@RestController
//@RestController = Define uma classe que contém métodos para uma API RESTful
@RequestMapping("/clientes")
// utilizada tradicionalmente para implementar URL handler, ela suporta os métodos Post, Get, Put, 
//Delete e Pacth.
public class ClienteController {

	@Autowired
	// Define pontos de injeção de dependencias dentro de uma classe
	private ClienteRepository clienteRepository;

	@GetMapping
	// anotação usada para mapear solicitações HTTP GET em métodos
	// manipuladores específicos
	public List<Cliente> listar() {

		// retorna tudo
		return clienteRepository.findAll();

		// Retorna por nome
		// return clienteRepository.findByNome("Aline");

		// Retorna o que contém no nome
		// return clienteRepository.findByNomeContaining("da");
	}

	@GetMapping("/{clienteId}")
	// anotação usada para mapear solicitações HTTP GET em métodos
	// manipuladores específicos
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId)
	// Permite receber parâmetros via {} em uma requisição
	{
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	// Ultilizado para fazer postagem

	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);		
			
		return ResponseEntity.ok(cliente);
		}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(clienteId);
		return ResponseEntity.noContent().build();
		
		
	}
}
