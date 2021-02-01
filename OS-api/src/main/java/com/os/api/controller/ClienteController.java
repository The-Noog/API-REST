package com.os.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.os.domain.model.Cliente;

//@RestController = Define uma classe que contém métodos para uma API RESTful
@RestController
public class ClienteController {

	// @GetMapping = anotação usada para mapear solicitações HTTP GET em métodos
	// manipuladores específicos
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Weslley");
		cliente1.setTelefone("11 955664422");
		cliente1.setEmail("weslleynoooog@hotmail.com");
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Aline");
		cliente2.setTelefone("11 47728922");
		cliente2.setEmail("Alinedasilva@hotmail.com");
		
		return Arrays.asList(cliente1, cliente2);
		
	}

}
