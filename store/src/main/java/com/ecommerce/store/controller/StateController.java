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
import com.ecommerce.store.record.StateRecord;
import com.ecommerce.store.service.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@GetMapping
	public ResponseEntity<List<StateRecord>> findall(){
		List<StateRecord> response = stateService.findAll();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{stateId}")
	public ResponseEntity<StateRecord> findById(@PathVariable Long stateId){
		StateRecord response = stateService.findById(stateId);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<StateRecord> insertState(@RequestBody StateDTO dto){
		StateRecord response = stateService.insertState(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{stateId}").buildAndExpand(response.id()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	@DeleteMapping(value = "/{stateId}")
	public ResponseEntity<Object> deleteState(@PathVariable Long stateId){
		stateService.deleteStateById(stateId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{stateId}")
	public ResponseEntity<StateRecord> updateState(@PathVariable Long stateId, @RequestBody StateDTO dto){
		StateRecord response = stateService.updateState(stateId, dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{stateId}").buildAndExpand(response.id()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
}
