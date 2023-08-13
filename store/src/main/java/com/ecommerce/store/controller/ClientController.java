package com.ecommerce.store.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.store.dto.ClientInsertDTO;
import com.ecommerce.store.dto.ClientMinDTO;
import com.ecommerce.store.dto.ClientUpdateDTO;
import com.ecommerce.store.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<ClientMinDTO>> findAll(){
		List<ClientMinDTO> response = clientService.findAll();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{clientId}")
	public ResponseEntity<ClientMinDTO> findById(@PathVariable Long clientId){
		ClientMinDTO response = clientService.findById(clientId);
		return ResponseEntity.ok(response);
	}

	
	@PostMapping
	public ResponseEntity<ClientMinDTO> insertClient(@RequestBody @Valid ClientInsertDTO clientDTO){
		ClientMinDTO response = clientService.insertNewClient(clientDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/clientId}").buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping(value = "/{clientId}")
	public ResponseEntity<ClientMinDTO> updateClient(@PathVariable Long clientId, @RequestBody @Valid ClientUpdateDTO clientUpdateDTO){
		ClientMinDTO response = clientService.updateClient(clientId, clientUpdateDTO);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{clientId}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long clientId){
		clientService.deleteClientById(clientId);
		return ResponseEntity.noContent().build();
	}
}
