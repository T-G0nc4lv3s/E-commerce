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

import com.ecommerce.store.dto.StateDTO;
import com.ecommerce.store.service.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@GetMapping
	public ResponseEntity<List<StateDTO>> findall(){
		List<StateDTO> response = stateService.findAll();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{stateId}")
	public ResponseEntity<StateDTO> findById(@PathVariable Long stateId){
		StateDTO response = stateService.findById(stateId);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<StateDTO> insertState(@RequestBody StateDTO dto){
		StateDTO response = stateService.insertState(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{stateId}").buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	@DeleteMapping(value = "/{stateId}")
	public ResponseEntity<Object> deleteState(@PathVariable Long stateId){
		stateService.deleteStateById(stateId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{stateId}")
	public ResponseEntity<StateDTO> updateState(@PathVariable Long stateId, @RequestBody StateDTO dto){
		StateDTO response = stateService.updateState(stateId, dto);
		return ResponseEntity.ok(response);
	}
}
